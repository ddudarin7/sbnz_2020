package com.ftn.sbnz_2020.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.dto.DiagnoseResultDTO;
import com.ftn.sbnz_2020.dto.DiseaseDTO;
import com.ftn.sbnz_2020.event.DiagnoseEvent;
import com.ftn.sbnz_2020.event.PandemicEvent;
import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Vaccination;
import com.ftn.sbnz_2020.facts.Vaccine;
import com.ftn.sbnz_2020.facts.Vet;
import com.ftn.sbnz_2020.repository.DiagnoseRepository;

@Service
public class DiagnoseService {

	@Autowired
	DiagnoseRepository diagnoseRepository;
	
	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	VaccinationService vaccinationsService;
	
	@Autowired
	VaccineService vaccineService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public Diagnose findById(Long id){ return diagnoseRepository.getOne(id); }
	
	public List<Diagnose> findByPatientId(Long patientId){
		return diagnoseRepository.findByPatientId(patientId);
	}
	
	public List<Diagnose> findByDiseaseId(Long diseaseId){
		return diagnoseRepository.findByDiseaseId(diseaseId);
	}
	
	public List<Diagnose> findByVetId(Long vetId){
		return diagnoseRepository.findByVetId(vetId);
	}
	

	public List<Diagnose> findAllByDiseaseId(Long diseaseId) { 
		return diagnoseRepository.findAllByDiseaseId(diseaseId); }
	
	public Page<Diagnose> findAllByDiseaseId(Long diseaseId, Pageable pageable) { 
		return diagnoseRepository.findAllByDiseaseId(diseaseId, pageable); 
	}
	
	public List<Diagnose> findAll() { return diagnoseRepository.findAll(); }
	
	public Page<Diagnose> findAll(Pageable pageable) { return diagnoseRepository.findAll(pageable); }
	
	public Diagnose update(Diagnose updatedDiagnose){
		Diagnose diagnose = diagnoseRepository.getOne(updatedDiagnose.getId());
		if (diagnose == null)
			return null;
		
		diagnose.setDisease(updatedDiagnose.getDisease());
		diagnose.setPatient(updatedDiagnose.getPatient());
		diagnose.setVet(updatedDiagnose.getVet());
		diagnose.setDate(updatedDiagnose.getDate());
		diagnose.setSpecificSymptomsMatched(updatedDiagnose.getSpecificSymptomsMatched());
		diagnose.setNonSpecificSymptomsMatched(updatedDiagnose.getNonSpecificSymptomsMatched());
		diagnose.setSpecificSymptomsMatchedNum(updatedDiagnose.getSpecificSymptomsMatchedNum());
		diagnose.setNonSpecificSymptomsMatchedNum(updatedDiagnose.getNonSpecificSymptomsMatchedNum());
		diagnose.setTherapies(updatedDiagnose.getTherapies());
		
		return diagnoseRepository.save(diagnose);
	}
	
	public void delete(Long id){ diagnoseRepository.deleteById(id); }
	
	public void deleteAll() { diagnoseRepository.deleteAll(); }
	
	@SuppressWarnings("unchecked")
	public ArrayList<Diagnose> diagnose(KieSession kieSession,List<Symptom> symptoms, Patient patient, KieSession eventSession) {	
		ArrayList<Diagnose> results=new ArrayList<>();
		// inserting symptoms
		for(Symptom s:symptoms) {
			kieSession.insert(s);
		}
		
		// inserting all diseases
		List<Disease> diseases = diseaseService.findAll();
		for(Disease d:diseases){
			d.initializeSupportFields();
			kieSession.insert(d);
		}
		
		// inserting diagnose
		Diagnose makingDiagnose = new Diagnose();
		makingDiagnose.setPatient(patient);
		kieSession.insert(makingDiagnose);
		
		// inserting diagnoses of other owner's dogs
		
		for (Diagnose d : this.findByOwnerId(patient.getOwner().getId()))
			kieSession.insert(d);
		
		kieSession.insert(DiseaseCategory.INFECTIOUS);
		
		//inserting patient
		kieSession.insert(patient);
		
		//inserting vaccination
		for(Vaccination v: vaccinationsService.findAll()) {
			kieSession.insert(v);
		}
		
		//inserting vaccines
		for(Vaccine v: vaccineService.findAll()) {
			kieSession.insert(v);
		}
		
		// firing rules
		kieSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kieSession.fireAllRules();
		
		eventSession.getAgenda().getAgendaGroup("event").setFocus();
		eventSession.fireAllRules();
		
		Collection<PandemicEvent> pandemicEvents = (Collection<PandemicEvent>) eventSession.getObjects(new ClassObjectFilter(PandemicEvent.class));
		
		for (PandemicEvent pe : pandemicEvents){
			sendWarning("Warning: Potential pandemic of " + pe.getDisease().getName()
					+ ": \n" + pe.getCount().toString() + " cases in last 7 days");
		}
		
		Collection<Boolean> flags=(Collection<Boolean>) kieSession.getObjects(new ClassObjectFilter(Boolean.class));
		
		//collect error message informations
		if(flags.contains(true)) {
			Collection<Disease> diseasesLeft=(Collection<Disease>) kieSession.getObjects(new ClassObjectFilter(Disease.class));
			
			
			
			for(Disease d:diseasesLeft) {
				Diagnose diagnose=new Diagnose(null, d, patient, makingDiagnose.getVet(), makingDiagnose.getSpecificSymptomsMatched(), 
						makingDiagnose.getNonSpecificSymptomsMatched(), makingDiagnose.getSpecificSymptomsMatchedNum(), 
						makingDiagnose.getNonSpecificSymptomsMatchedNum(), d.getTherapies(), makingDiagnose.getDate());
				kieSession.insert(diagnose);
				results.add(diagnose);
			}
			kieSession.getAgenda().getAgendaGroup("allergy checking").setFocus();
			kieSession.fireAllRules();
			this.releaseObjectsFromSession(kieSession);
			return results;
		}
		else {
			kieSession.getAgenda().getAgendaGroup("allergy checking").setFocus();
			kieSession.fireAllRules();
			this.releaseObjectsFromSession(kieSession);
			
			results.add(makingDiagnose);
			return results;
		}
		
	}
	
    private void releaseObjectsFromSession(KieSession kieSession){
        kieSession.getObjects();

        for( Object object: kieSession.getObjects() ){
            kieSession.delete( kieSession.getFactHandle( object ) );
        }
    }
    
    public Diagnose confirmDiagnose(Diagnose diagnose, Vet vet, KieSession eventSession){
    	diagnose.setId(null);
    	diagnose.setDate(new Date());
    	diagnose.setVet(vet);
    
    	diagnose = this.diagnoseRepository.save(diagnose);
		
		eventSession.getAgenda().getAgendaGroup("event").setFocus();

		eventSession.insert(new DiagnoseEvent(diagnose.getDisease(), diagnose.getDate()));
		eventSession.fireAllRules();
		Collection<PandemicEvent> pandemicEvents = (Collection<PandemicEvent>) eventSession.getObjects(new ClassObjectFilter(PandemicEvent.class));
		
		for (PandemicEvent pe : pandemicEvents){
			sendWarning("Warning: Potential pandemic of " + pe.getDisease().getName()
					+ ": \n" + pe.getCount().toString() + " cases in last 7 days");
		}
    	
    	return diagnose;
    }
    
    private List<Diagnose> findByOwnerId(Long ownerId){
    	List<Patient> ownerPatients = patientService.findByOwnerId(ownerId);
    	
    	List<Diagnose> diagnoses = new ArrayList<Diagnose>();
    	
    	for (Patient patient : ownerPatients){
    		diagnoses.addAll(diagnoseRepository.findByPatientId(patient.getId()));
    	}
    	
    	return diagnoses;
    }
    
    public void sendWarning(String message) {
        this.messagingTemplate.convertAndSend("/notifications", message);
    }
    
}

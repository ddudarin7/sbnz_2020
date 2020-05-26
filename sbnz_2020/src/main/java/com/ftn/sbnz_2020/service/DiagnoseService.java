package com.ftn.sbnz_2020.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Vet;
import com.ftn.sbnz_2020.repository.DiagnoseRepository;

@Service
public class DiagnoseService {

	@Autowired
	DiagnoseRepository diagnoseRepository;
	
	@Autowired
	DiseaseService diseaseService;
	
	public Diagnose findById(Long id){ return diagnoseRepository.getOne(id); }
	
	/*
	 * Add find by patient and vet
	 */

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
	
	public Diagnose diagnose(KieSession kieSession,List<Symptom> symptoms, Patient patient) {
		
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
		
		// firing rules
		
		kieSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("allergy checking").setFocus();
		kieSession.fireAllRules();
		
		this.releaseObjectsFromSession(kieSession);
		
		return makingDiagnose;
	}
	
    private void releaseObjectsFromSession(KieSession kieSession){
        kieSession.getObjects();

        for( Object object: kieSession.getObjects() ){
            kieSession.delete( kieSession.getFactHandle( object ) );
        }
    }
    
    public Diagnose confirmDiagnose(Diagnose diagnose, Vet vet){
    	diagnose.setId(null);
    	diagnose.setDate(new Date());
    	diagnose.setVet(vet);
    	
    	return this.diagnoseRepository.save(diagnose);
    }
	
}

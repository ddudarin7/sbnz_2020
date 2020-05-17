package com.ftn.sbnz_2020.service;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.repository.DiagnoseRepository;
import com.ftn.sbnz_2020.repository.DiseaseRepository;
import com.ftn.sbnz_2020.repository.SymptomRepository;

@Service
public class DiagnoseService {

	@Autowired
	DiagnoseRepository diagnoseRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
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
	
	public Diagnose add(Diagnose diagnose){
		diagnose.setId(null);
		return diagnoseRepository.save(diagnose); 
	}
	
	public Diagnose update(Diagnose updatedDiagnose){
		Diagnose diagnose = diagnoseRepository.getOne(updatedDiagnose.getId());
		if (diagnose == null)
			return null;
		
		diagnose.setDisease(updatedDiagnose.getDisease());
		diagnose.setPatient(updatedDiagnose.getPatient());
		diagnose.setVet(updatedDiagnose.getVet());
		diagnose.setDate(updatedDiagnose.getDate());
		diagnose.setSpecificSymptoms(updatedDiagnose.getSpecificSymptoms());
		diagnose.setNonSpecificSymptoms(updatedDiagnose.getNonSpecificSymptoms());
		diagnose.setSpecificSymptomsMatched(updatedDiagnose.getSpecificSymptomsMatched());
		diagnose.setNonSpecificSymptomsMatched(updatedDiagnose.getNonSpecificSymptomsMatched());
		diagnose.setTherapies(updatedDiagnose.getTherapies());
		
		return diagnoseRepository.save(diagnose);
	}
	
	public void delete(Long id){ diagnoseRepository.deleteById(id); }
	
	public void deleteAll() { diagnoseRepository.deleteAll(); }
	
	public Diagnose diagnose(KieSession kieSession,List<Symptom> symptoms) {
		Diagnose diagnose=new Diagnose();
		kieSession.insert(diagnose);
		
		for(Symptom s:symptoms) {
			kieSession.insert(s);
		}
		
		List<Disease> diseases=diseaseRepository.findAll();
		for(Disease d:diseases){
			kieSession.insert(d);
		}
		
        kieSession.getAgenda().getAgendaGroup("matching symptoms").setFocus();
        kieSession.fireAllRules();
		
        kieSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kieSession.fireAllRules();
		
		this.releaseObjectsFromSession(kieSession);
		return diagnose;
	}
	
    private void releaseObjectsFromSession(KieSession kieSession){
        kieSession.getObjects();

        for( Object object: kieSession.getObjects() ){
            kieSession.delete( kieSession.getFactHandle( object ) );
        }
    }
	
}

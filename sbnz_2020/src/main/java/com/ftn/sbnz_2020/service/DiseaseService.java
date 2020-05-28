package com.ftn.sbnz_2020.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.repository.DiseaseRepository;

@Service
public class DiseaseService {

	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
	DiagnoseService diagnoseService;
	
	public Disease findById(Long id){ return diseaseRepository.getOne(id); }
	
	public Disease findByName(String name){ return diseaseRepository.findByName(name); }

	public List<Disease> findAllByDiseaseCategory(DiseaseCategory diseaseCategory) { 
		return diseaseRepository.findAllByDiseaseCategory(diseaseCategory); }
	
	public Page<Disease> findAllByDiseaseCategory(DiseaseCategory diseaseCategory, 
			Pageable pageable) { 
		return diseaseRepository.findAllByDiseaseCategory(diseaseCategory, pageable); 
	}
	
	public List<Disease> findAll() { return diseaseRepository.findAll(); }
	
	public Page<Disease> findAll(Pageable pageable) { return diseaseRepository.findAll(pageable); }
	
	public Disease add(Disease disease){
		disease.setId(null);
		return diseaseRepository.save(disease); 
	}
	
	public Disease update(Disease updatedDisease){
		Disease disease = diseaseRepository.getOne(updatedDisease.getId());
		if (disease == null)
			return null;
		
		disease.setName(updatedDisease.getName());
		disease.setDiseaseCategory(updatedDisease.getDiseaseCategory());
		disease.setSpecificSymptoms(updatedDisease.getSpecificSymptoms());
		disease.setNonSpecificSymptoms(updatedDisease.getNonSpecificSymptoms());
		disease.setTherapies(updatedDisease.getTherapies());
		
		return diseaseRepository.save(disease);
	}
	
	public void delete(Long id){ 
		for (Diagnose diagnose : this.diagnoseService.findByDiseaseId(id))
			this.diagnoseService.delete(diagnose.getId());
		diseaseRepository.deleteById(id); 
	}
	
	public void deleteAll() { 
		this.diagnoseService.deleteAll();
		diseaseRepository.deleteAll(); 
	}
	
	public List<Disease> findAllWithSymptom(KieSession kieSession,List<Symptom> symptoms){
		List<Disease> matching=new ArrayList<>();
		
		for(Symptom s:symptoms) {
			kieSession.insert(s);
		}
		
		List<Disease> diseases=diseaseRepository.findAll();
		for(Disease d:diseases){
			kieSession.insert(d);
		}
		//set focus on agenda
		kieSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kieSession.fireAllRules();
		QueryResults results=kieSession.getQueryResults("Get all diseases that satisfy one or more symptoms");
		for (QueryResultsRow row: results) {
            System.out.println(row.get("$d"));
            Disease disease = (Disease) row.get("$d");
            matching.add(disease);
        }
		
		releaseObjectsFromSession(kieSession);
		
		return matching;
	}
	
	 private void releaseObjectsFromSession(KieSession kieSession){
	        kieSession.getObjects();

	        for( Object object: kieSession.getObjects() ){
	            kieSession.delete( kieSession.getFactHandle( object ) );
	        }
	 }
	
}

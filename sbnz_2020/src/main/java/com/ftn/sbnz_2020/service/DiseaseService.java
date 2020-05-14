package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.repository.DiseaseRepository;

@Service
public class DiseaseService {

	@Autowired
	DiseaseRepository diseaseRepository;
	
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
	
	public void delete(Long id){ diseaseRepository.deleteById(id); }
	
	public void deleteAll() { diseaseRepository.deleteAll(); }
	
}

package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.repository.SymptomRepository;

@Service
public class SymptomService {

	@Autowired
	SymptomRepository symptomRepository;
	
	public Symptom findById(Long id){ return symptomRepository.getOne(id); }
	
	public Symptom findByName(String name){ return symptomRepository.findByName(name); }
	
	public List<Symptom> findAll() { 	
		return symptomRepository.findAll(); 
	}
	
	public Page<Symptom> findAll(Pageable pageable) { return symptomRepository.findAll(pageable); }
	
	public Symptom add(Symptom symptom){
		symptom.setId(null);
		return symptomRepository.save(symptom); 
	}
	
	public Symptom update(Symptom updatedSymptom){
		Symptom symptom = symptomRepository.getOne(updatedSymptom.getId());
		if (symptom == null)
			return null;
		
		symptom.setName(updatedSymptom.getName());
		
		return symptomRepository.save(symptom);
	}
	
	public void delete(Long id){ symptomRepository.deleteById(id); }
	
	public void deleteAll() { symptomRepository.deleteAll(); }
	
}

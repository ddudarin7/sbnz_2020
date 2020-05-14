package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Therapy;
import com.ftn.sbnz_2020.repository.TherapyRepository;

@Service
public class TherapyService {
	
	@Autowired
	TherapyRepository therapyRepository;

	public Therapy findById(Long id){ return therapyRepository.getOne(id); }
	
	public Therapy findByDescription(String description){ 
		return therapyRepository.findByDescription(description); 
	}
	
	public List<Therapy> findAll() { return therapyRepository.findAll(); }
	
	public Page<Therapy> findAll(Pageable pageable) { 
		return therapyRepository.findAll(pageable); 
	}
	
	public Therapy add(Therapy therapy){
		therapy.setId(null);
		return therapyRepository.save(therapy); 
	}
	
	public Therapy update(Therapy updatedTherapy){
		Therapy therapy = therapyRepository.getOne(updatedTherapy.getId());
		if (therapy == null)
			return null;
		
		therapy.setDescription(updatedTherapy.getDescription());
		therapy.setMedicines(updatedTherapy.getMedicines());
		
		return therapyRepository.save(therapy);
	}
	
	public void delete(Long id){ therapyRepository.deleteById(id); }
	
	public void deleteAll() { therapyRepository.deleteAll(); }
	
}

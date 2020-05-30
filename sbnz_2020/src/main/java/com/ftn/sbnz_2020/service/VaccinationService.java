package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Vaccination;
import com.ftn.sbnz_2020.repository.VaccinationRepository;

@Service
public class VaccinationService {
	
	@Autowired
	VaccinationRepository vaccinationRepository;
	
	public Vaccination findById(Long id) {
        return vaccinationRepository.findById(id).get();
    }

    public List<Vaccination> findAll() {
        return vaccinationRepository.findAll();
    }

    public Page<Vaccination> findAll(Pageable pageable) {
        return vaccinationRepository.findAll(pageable);
    }

    public Vaccination save(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }
    
    public Vaccination add(Vaccination vaccination){
    	vaccination.setId(null);
		return vaccinationRepository.save(vaccination); 
	}
    
    public Vaccination update(Vaccination vaccinationUpdate) {
    	Vaccination vaccination = this.findById(vaccinationUpdate.getId());
    	vaccination.setVaccine(vaccinationUpdate.getVaccine());
    	vaccination.setDate(vaccinationUpdate.getDate());
        return vaccinationRepository.save(vaccination);
    }

    public void delete(Long id) {
    	vaccinationRepository.delete(this.findById(id));
    }

    public void deleteAll() {
    	vaccinationRepository.deleteAllInBatch();
    }
}

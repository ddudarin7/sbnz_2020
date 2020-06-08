package com.ftn.sbnz_2020.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Vaccination;
import com.ftn.sbnz_2020.facts.Vaccine;
import com.ftn.sbnz_2020.repository.VaccineRepository;

@Service
public class VaccineService {

	@Autowired
	VaccineRepository vaccineRepository;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	VaccinationService vaccinationService;
	
	public Vaccine findById(Long id) {
        return vaccineRepository.findById(id).get();
    }

    public List<Vaccine> findAll() {
        return vaccineRepository.findAll();
    }

    public Page<Vaccine> findAll(Pageable pageable) {
        return vaccineRepository.findAll(pageable);
    }
    
    public Vaccine add(Vaccine vaccine){
    	vaccine.setId(null);
		return vaccineRepository.save(vaccine); 
	}
    
    public Vaccine update(Vaccine vaccineUpdate) {
    	Vaccine vaccine = this.findById(vaccineUpdate.getId());
    	vaccine.setName(vaccineUpdate.getName());
    	vaccine.setDescription(vaccineUpdate.getDescription());
        return vaccineRepository.save(vaccine);
    }

    public void delete(Long id) {
    	
    	// remove all patient vaccinations with this vaccine
    	
    	for (Patient patient : patientService.findAll()){
    		List<Vaccination> vaccinations = new ArrayList<>();
    		for (Vaccination vaccination : patient.getVaccinations())
    			if (vaccination.getVaccine().getId() != id)
    				vaccinations.add(vaccination);
    		patient.setVaccinations(vaccinations);
    		patientService.update(patient);	
    	}
    	
    	vaccineRepository.delete(this.findById(id));
    }

    public void deleteAll() {
    	for (Patient patient : patientService.findAll()){
    		patient.setVaccinations(new ArrayList<>());
    		patientService.update(patient);
    	}
    	vaccineRepository.deleteAllInBatch();
    }
	
}

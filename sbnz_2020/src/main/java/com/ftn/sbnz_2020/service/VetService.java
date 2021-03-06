package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Role;
import com.ftn.sbnz_2020.facts.Vet;
import com.ftn.sbnz_2020.repository.VetRepository;

@Service
public class VetService {
	@Autowired
    VetRepository vetRepository;
	
	@Autowired
	DiagnoseService diagnoseService;

    public Vet findById(Long id) {
        return vetRepository.findById(id).get();
    }

    public Vet findByUsername(String username) {
        return vetRepository.findByUsername(username);
    }

    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    public Page<Vet> findAll(Pageable pageable) {
        return vetRepository.findAll(pageable);
    }

    public Vet save(Vet vet) {
        vet.setId(null);
        vet.setPassword("vet");
        vet.setRole(Role.VET);
        return vetRepository.save(vet);
    }

    public Vet update(Vet vetUpdate) {
        Vet vet = this.findById(vetUpdate.getId());
        vet.setFirstName(vetUpdate.getFirstName());
        vet.setLastName(vetUpdate.getLastName());
        return vetRepository.save(vet);
    }

    public void delete(Long id) {
    	for (Diagnose diagnose : this.diagnoseService.findByVetId(id))
    		this.diagnoseService.delete(diagnose.getId());
        vetRepository.delete(this.findById(id));
    }

    public void deleteAll() {
    	this.diagnoseService.deleteAll();
        vetRepository.deleteAllInBatch();
    }
}

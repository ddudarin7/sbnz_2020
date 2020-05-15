package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Owner;
import com.ftn.sbnz_2020.repository.OwnerRepository;


@Service
public class OwnerService {
	
	@Autowired
	OwnerRepository ownerRepository;
	
	public Owner findById(Long id) {
        return ownerRepository.findById(id).get();
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Page<Owner> findAll(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    public Owner save(Owner owner) {
        //admin.setId(null);
        return ownerRepository.save(owner);
    }

    public Owner update(Owner ownerUpdate) {
    	Owner owner = this.findById(ownerUpdate.getId());
    	owner.setFirstName(ownerUpdate.getFirstName());
    	owner.setLastName(ownerUpdate.getLastName());
    	owner.setAddress(ownerUpdate.getAddress());
    	owner.setPhoneNum(ownerUpdate.getPhoneNum());
        return ownerRepository.save(owner);
    }

    public void delete(Long id) {
    	ownerRepository.delete(this.findById(id));
    }

    public void deleteAll() {
    	ownerRepository.deleteAllInBatch();
    }
	
	
}

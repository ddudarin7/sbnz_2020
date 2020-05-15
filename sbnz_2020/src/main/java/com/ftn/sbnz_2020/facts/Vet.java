package com.ftn.sbnz_2020.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ftn.sbnz_2020.dto.VetDTO;

@Entity
@DiscriminatorValue("Vet")
public class Vet extends User {

    public Vet() {}

    public Vet(String username, String password, String firstName, String lastName, Role role) {
        super(username, password, firstName, lastName, role);
    }
    
    public Vet(VetDTO vetDTO) {
    	this.setId(vetDTO.getId());
        this.setUsername(vetDTO.getUsername());
        this.setPassword(vetDTO.getPassword());
        this.setFirstName(vetDTO.getFirstName());
        this.setLastName(vetDTO.getLastName());
        this.setRole(vetDTO.getRole());
    }
	
}

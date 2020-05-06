package com.ftn.sbnz_2020.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Vet")
public class Vet extends User {

    public Vet() {}

    public Vet(String username, String password, String firstName, String lastName, Role role) {
        super(username, password, firstName, lastName, role);
    }
	
}

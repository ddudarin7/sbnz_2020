package com.ftn.sbnz_2020.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

    public Admin() {}

    public Admin(String username, String password, String firstName, String lastName, Role role) {
        super(username, password, firstName, lastName, role);
    }

}

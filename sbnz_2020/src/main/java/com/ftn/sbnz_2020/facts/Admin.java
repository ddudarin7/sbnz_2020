package com.ftn.sbnz_2020.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ftn.sbnz_2020.dto.AdminDTO;



@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

    public Admin() {}

    public Admin(String username, String password, String firstName, String lastName, Role role) {
        super(username, password, firstName, lastName, role);
    }
    
    public Admin(AdminDTO adminDTO) {
        this.setId(adminDTO.getId());
        this.setUsername(adminDTO.getUsername());
        this.setPassword(adminDTO.getPassword());
        this.setFirstName(adminDTO.getFirstName());
        this.setLastName(adminDTO.getLastName());
        this.setRole(adminDTO.getRole());
    }

}

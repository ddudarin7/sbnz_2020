package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Admin;
import com.ftn.sbnz_2020.facts.Role;

public class AdminDTO {
	private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public AdminDTO() {}

    public AdminDTO(Long id, String username, String password, String firstName, String lastName, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.password = "";
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.role = admin.getRole();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

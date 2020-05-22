package com.ftn.sbnz_2020.facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ftn.sbnz_2020.dto.OwnerDTO;

@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNum;
	
	private Address address;

	public Owner() {
		super();
	}

	public Owner(Long id, String firstName, String lastName, String phoneNum, Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.address = address;
	}
	
	public Owner(OwnerDTO ownerDTO) {
		if (ownerDTO.getId() != null)
			this.id = ownerDTO.getId();
		if (ownerDTO.getFirstName() != null)
			this.firstName = ownerDTO.getFirstName();
		if (ownerDTO.getLastName() != null)
			this.lastName = ownerDTO.getLastName();
		if (ownerDTO.getPhoneNum() != null)
			this.phoneNum = ownerDTO.getPhoneNum();
		if (ownerDTO.getAddress() != null)
			this.address = ownerDTO.getAddress();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}

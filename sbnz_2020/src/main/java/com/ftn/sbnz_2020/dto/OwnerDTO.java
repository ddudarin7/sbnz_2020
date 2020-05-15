package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Address;
import com.ftn.sbnz_2020.facts.Owner;

public class OwnerDTO {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNum;
	
	private Address address;

	public OwnerDTO() {
		super();
	}

	public OwnerDTO(Long id, String firstName, String lastName, String phoneNum, Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.address = address;
	}
	
	public OwnerDTO(Owner owner) {
		this.id = owner.getId();
		this.firstName = owner.getFirstName();
		this.lastName = owner.getLastName();
		this.phoneNum = owner.getPhoneNum();
		this.address = owner.getAddress();
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

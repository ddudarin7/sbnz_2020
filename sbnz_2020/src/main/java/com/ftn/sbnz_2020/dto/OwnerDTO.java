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
		if (owner.getId() != null)
			this.id = owner.getId();
		if (owner.getFirstName() != null)
			this.firstName = owner.getFirstName();
		if (owner.getLastName() != null)
			this.lastName = owner.getLastName();
		if (owner.getPhoneNum() != null)
			this.phoneNum = owner.getPhoneNum();
		if (owner.getAddress() != null)
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

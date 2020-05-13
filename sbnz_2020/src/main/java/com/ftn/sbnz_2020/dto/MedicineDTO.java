package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Medicine;

public class MedicineDTO {
	
	private Long id;
	private String name;
	/*
	 * TODO
	 */
	
	public MedicineDTO(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public MedicineDTO(Medicine medicine){
		this.id = medicine.getId();
		this.name = medicine.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

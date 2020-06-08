package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Vaccine;

public class VaccineDTO {
	
	private Long id;
	private String name;
	private String description;
	
	public VaccineDTO(){
		
	}
	
	public VaccineDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public VaccineDTO(Vaccine vaccine){
		if (vaccine.getId() != null)
			this.id = vaccine.getId();
		if (vaccine.getName() != null)
			this.name = vaccine.getName();
		if (vaccine.getDescription() != null)
			this.description = vaccine.getDescription();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package com.ftn.sbnz_2020.dto;

import java.util.Date;

import com.ftn.sbnz_2020.facts.Vaccination;

public class VaccinationDTO {
	private Long id;
	
	private String name;
	
	private String description;
	
	private Date date;

	public VaccinationDTO() {
		super();
	}

	public VaccinationDTO(Long id, String name, String description, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
	}
	
	public VaccinationDTO(Vaccination vaccination) {
		super();
		this.id = vaccination.getId();
		this.name = vaccination.getName();
		this.description = vaccination.getDescription();
		this.date = vaccination.getDate();
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

package com.ftn.sbnz_2020.dto;

import java.util.Date;

import com.ftn.sbnz_2020.facts.Vaccination;

public class VaccinationDTO {
	
	private Long id;
	private VaccineDTO vaccine;
	private Date date;

	public VaccinationDTO() {
		super();
	}

	public VaccinationDTO(Long id, VaccineDTO vaccine, Date date) {
		super();
		this.id = id;
		this.vaccine = vaccine;
		this.date = date;
	}
	
	public VaccinationDTO(Vaccination vaccination) {
		super();
		if (vaccination.getId() != null)
			this.id = vaccination.getId();
		if (vaccination.getVaccine() != null)
			this.vaccine = new VaccineDTO(vaccination.getVaccine());
		if (vaccination.getDate() != null)
			this.date = vaccination.getDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public VaccineDTO getVaccine() {
		return vaccine;
	}

	public void setVaccine(VaccineDTO vaccine) {
		this.vaccine = vaccine;
	}
	
}

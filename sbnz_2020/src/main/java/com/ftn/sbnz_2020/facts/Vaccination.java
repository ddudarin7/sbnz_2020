package com.ftn.sbnz_2020.facts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ftn.sbnz_2020.dto.VaccinationDTO;

@Entity
@Table(name = "vaccination")
public class Vaccination {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "vaccine_id", referencedColumnName = "id")
	private Vaccine vaccine;
	
	@Column(name = "date")
    @Temporal(TemporalType.DATE)
	private Date date;
	
	public Vaccination() {
		super();
	}
	
	public Vaccination(Long id, Vaccine vaccine, Date date) {
		this.id = id;
		this.vaccine = vaccine;
		this.date = date;
	}
	
	public Vaccination(VaccinationDTO vaccination) {
		if (vaccination.getId() != null)
			this.id = vaccination.getId();
		if (vaccination.getVaccine() != null)
			this.vaccine = new Vaccine(vaccination.getVaccine());
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

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	
}

package com.ftn.sbnz_2020.facts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	private String name;
	
	private String description;
	
	@Column(name = "date")
    @Temporal(TemporalType.DATE)
	private Date date;
	
	public Vaccination() {
		super();
	}
	
	public Vaccination(Long id, String name, String description, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
	}
	
	public Vaccination(VaccinationDTO vaccination) {
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

package com.ftn.sbnz_2020.facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ftn.sbnz_2020.dto.VaccineDTO;

@Entity
@Table(name = "vaccine")
public class Vaccine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	private String name;
	
	private String description;

	public Vaccine(){}
	
	public Vaccine(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Vaccine(VaccineDTO vaccineDTO){
		if (vaccineDTO.getId() != null)
			this.id = vaccineDTO.getId();
		if (vaccineDTO.getName() != null)
			this.name = vaccineDTO.getName();
		if (vaccineDTO.getDescription() != null)
			this.description = vaccineDTO.getDescription();
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

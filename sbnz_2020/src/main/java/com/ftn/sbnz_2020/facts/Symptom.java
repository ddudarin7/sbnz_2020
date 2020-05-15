package com.ftn.sbnz_2020.facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ftn.sbnz_2020.dto.SymptomDTO;

@Entity
@Table(name = "Symptom")
public class Symptom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
    private String name;

	public Symptom(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Symptom(SymptomDTO symptomDTO){
		if (symptomDTO.getId() != null)
			this.id = symptomDTO.getId();
		this.name = symptomDTO.getName();
	}
	
	public Symptom() {
		super();
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

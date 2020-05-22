package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Symptom;

public class SymptomDTO {

	private Long id;
	private String name;
	public SymptomDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public SymptomDTO() {
		super();
	}
	
	public SymptomDTO(Symptom symptom){
		if (symptom.getId() != null)
			this.id = symptom.getId();
		if (symptom.getName() != null)
			this.name = symptom.getName();
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

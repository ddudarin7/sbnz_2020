package com.ftn.sbnz_2020.event;

import java.io.Serializable;

import org.kie.api.definition.type.Role;

import com.ftn.sbnz_2020.facts.Patient;

@Role(Role.Type.EVENT)
public class VaccinationEvent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Patient patient;
	
	public VaccinationEvent() {
		super();
	}

}

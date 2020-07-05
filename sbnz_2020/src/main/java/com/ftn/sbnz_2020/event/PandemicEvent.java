package com.ftn.sbnz_2020.event;

import java.io.Serializable;

import org.kie.api.definition.type.Role;

import com.ftn.sbnz_2020.facts.Disease;

@Role(Role.Type.EVENT)
public class PandemicEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private Disease disease;
	private Long count;
	
	public PandemicEvent(){}

	public PandemicEvent(Disease disease, Long count){
		this.disease = disease;
		this.count = count;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}

package com.ftn.sbnz_2020.event;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.sbnz_2020.facts.Disease;

@Role(Role.Type.EVENT)
@Timestamp("date")
public class DiagnoseEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private Disease diagnosedDisease;
	private Date date;
	
	public DiagnoseEvent(Disease disease, Date date){
		this.diagnosedDisease = disease;
		this.date = date;
	}

	public Disease getDiagnosedDisease() {
		return diagnosedDisease;
	}

	public void setDiagnosedDisease(Disease diagnosedDisease) {
		this.diagnosedDisease = diagnosedDisease;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Patient;

public class ReportChronicDiseasesDTO {

	private Patient p;
	private Disease d;
	
	public ReportChronicDiseasesDTO() {}

	public ReportChronicDiseasesDTO(Patient p,Disease d) {
		this.p=p;
		this.d=d;
	}

	public Patient getP() {
		return p;
	}

	public void setP(Patient p) {
		this.p = p;
	}

	public Disease getD() {
		return d;
	}

	public void setD(Disease d) {
		this.d = d;
	}
	
}

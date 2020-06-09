package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Patient;

public class ReportWeakenedImmuneSystemDTO {

	private Patient p;
	private Disease d;

	public ReportWeakenedImmuneSystemDTO() {
		super();
	}

	public ReportWeakenedImmuneSystemDTO(Patient p, Disease d) {
		super();
		this.p = p;
		this.d = d;
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

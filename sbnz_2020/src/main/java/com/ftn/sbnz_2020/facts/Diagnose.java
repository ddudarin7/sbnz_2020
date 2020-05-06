package com.ftn.sbnz_2020.facts;

import java.util.Date;
import java.util.List;

public class Diagnose {

	private Long id;
	
	private Disease disease;
	
	private Patient patient;
	
	private Vet vet;
	
	private List<Symptom> symptoms;
	
	private List<Therapy> therapies;
	
	private Date date;

	public Diagnose() {
		super();
	}

	public Diagnose(Long id, Disease disease, Patient patient, Vet vet, List<Symptom> symptoms, List<Therapy> therapies,
			Date date) {
		super();
		this.id = id;
		this.disease = disease;
		this.patient = patient;
		this.vet = vet;
		this.symptoms = symptoms;
		this.therapies = therapies;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public List<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<Therapy> therapies) {
		this.therapies = therapies;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

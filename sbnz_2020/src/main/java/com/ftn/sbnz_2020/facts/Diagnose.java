package com.ftn.sbnz_2020.facts;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "diagnose")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disease_id", referencedColumnName = "id")
	private Disease disease;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
	private Vet vet;
	
    @ElementCollection(targetClass = Symptom.class)
    @Column(name = "specificSymptoms", nullable = false)
    @Enumerated(EnumType.STRING)
	private List<Symptom> symptoms;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
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

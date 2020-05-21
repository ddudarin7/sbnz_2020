package com.ftn.sbnz_2020.facts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.ftn.sbnz_2020.dto.DiagnoseDTO;
import com.ftn.sbnz_2020.dto.SymptomDTO;
import com.ftn.sbnz_2020.dto.TherapyDTO;


@Entity
@Table(name = "diagnose")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disease_id", referencedColumnName = "id")
	private Disease disease;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
	private Vet vet;
	
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE})
    @Column(name = "specificSymptoms", nullable = false)
	private List<Symptom> specificSymptoms;
    
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE})
    @Column(name = "nonSpecificSymptoms", nullable = false)
	private List<Symptom> nonSpecificSymptoms;
    
    @Column(name = "specificSymptomsMatched")
    private Long specificSymptomsMatched;
    
    @Column(name = "nonSpecificSymptomsMatched")
    private Long nonSpecificSymptomsMatched;
    
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
	@Column(name = "therapies", nullable = false)
	private List<Therapy> therapies;
	
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
	private Date date;

	public Diagnose() {
		super();
		this.therapies = new ArrayList<Therapy>();
	}

	

	public Diagnose(Long id, Disease disease, Patient patient, Vet vet, List<Symptom> specificSymptoms,
			List<Symptom> nonSpecificSymptoms, Long specificSymptomsMatched, Long nonSpecificSymptomsMatched,
			List<Therapy> therapies, Date date) {
		super();
		this.id = id;
		this.disease = disease;
		this.patient = patient;
		this.vet = vet;
		this.specificSymptoms = specificSymptoms;
		this.nonSpecificSymptoms = nonSpecificSymptoms;
		this.specificSymptomsMatched = specificSymptomsMatched;
		this.nonSpecificSymptomsMatched = nonSpecificSymptomsMatched;
		this.therapies = therapies;
		this.date = date;
	}

	public Diagnose(DiagnoseDTO diagnoseDTO){
		if (diagnoseDTO.getId() != null)
			this.id = diagnoseDTO.getId();
		if (diagnoseDTO.getDiseaseDTO() != null)
			this.disease = new Disease(diagnoseDTO.getDiseaseDTO());
		if (diagnoseDTO.getPatientDTO() != null)
			this.patient = new Patient(diagnoseDTO.getPatientDTO());
		if (diagnoseDTO.getVetDTO() != null)
			this.vet = new Vet(diagnoseDTO.getVetDTO());
		this.specificSymptoms = new ArrayList<Symptom>();
		for (SymptomDTO symptomDTO : diagnoseDTO.getSpecificSymptomDTOs())
			this.specificSymptoms.add(new Symptom(symptomDTO));
		this.nonSpecificSymptoms = new ArrayList<Symptom>();
		for (SymptomDTO symptomDTO : diagnoseDTO.getNonSpecificSymptomDTOs())
			this.nonSpecificSymptoms.add(new Symptom(symptomDTO));
		if (diagnoseDTO.getSpecificSymptomsMatched() != null)
			this.specificSymptomsMatched = diagnoseDTO.getSpecificSymptomsMatched();
		if (diagnoseDTO.getNonSpecificSymptomsMatched() != null)
			this.nonSpecificSymptomsMatched = diagnoseDTO.getNonSpecificSymptomsMatched();
		this.therapies = new ArrayList<Therapy>();
		for (TherapyDTO therapyDTO : diagnoseDTO.getTherapyDTOs())
			this.therapies.add(new Therapy(therapyDTO));
		if (diagnoseDTO.getDate() != null)
			this.date = diagnoseDTO.getDate();
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

	public List<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<Therapy> therapies) {
		this.therapies = new ArrayList<Therapy>();
		if (therapies == null)
			return;
		for (Therapy therapy : therapies)
			this.therapies.add(therapy);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public List<Symptom> getSpecificSymptoms() {
		return specificSymptoms;
	}



	public void setSpecificSymptoms(List<Symptom> specificSymptoms) {
		this.specificSymptoms = specificSymptoms;
	}



	public List<Symptom> getNonSpecificSymptoms() {
		return nonSpecificSymptoms;
	}



	public void setNonSpecificSymptoms(List<Symptom> nonSpecificSymptoms) {
		this.nonSpecificSymptoms = nonSpecificSymptoms;
	}



	public Long getSpecificSymptomsMatched() {
		return specificSymptomsMatched;
	}



	public void setSpecificSymptomsMatched(Long specificSymptomsMatched) {
		this.specificSymptomsMatched = specificSymptomsMatched;
	}



	public Long getNonSpecificSymptomsMatched() {
		return nonSpecificSymptomsMatched;
	}



	public void setNonSpecificSymptomsMatched(Long nonSpecificSymptomsMatched) {
		this.nonSpecificSymptomsMatched = nonSpecificSymptomsMatched;
	}
	
	
	
}

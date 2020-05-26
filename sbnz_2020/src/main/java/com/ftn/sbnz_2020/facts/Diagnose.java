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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "id")
	private Disease disease;
	
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
    @ManyToOne
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
	private Vet vet;
	
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE})
    @Column(name = "specific_symptoms_matched", nullable = false)
	private List<Symptom> specificSymptomsMatched;
    
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE})
    @Column(name = "non_specific_symptoms_matched", nullable = false)
	private List<Symptom> nonSpecificSymptomsMatched;
    
    @Column(name = "specific_symptoms_matched_num")
    private Long specificSymptomsMatchedNum;
    
    @Column(name = "non_specific_symptoms_matched_num")
    private Long nonSpecificSymptomsMatchedNum;
    
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany//(cascade={CascadeType.MERGE}) // unidirectional
	@Column(name = "therapies", nullable = false)
	private List<Therapy> therapies;
	
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
	private Date date;

	public Diagnose() {
		super();
		this.therapies = new ArrayList<Therapy>();
	}

	

	public Diagnose(Long id, Disease disease, Patient patient, Vet vet, List<Symptom> specificSymptomsMatched,
			List<Symptom> nonSpecificSymptomsMatched, Long specificSymptomsMatchedNum, Long nonSpecificSymptomsMatchedNum,
			List<Therapy> therapies, Date date) {
		super();
		this.id = id;
		this.disease = disease;
		this.patient = patient;
		this.vet = vet;
		this.specificSymptomsMatched = specificSymptomsMatched;
		this.nonSpecificSymptomsMatched = nonSpecificSymptomsMatched;
		this.specificSymptomsMatchedNum = specificSymptomsMatchedNum;
		this.nonSpecificSymptomsMatchedNum = nonSpecificSymptomsMatchedNum;
		this.therapies = therapies;
		this.date = date;
	}

	public Diagnose(DiagnoseDTO diagnoseDTO){
		if (diagnoseDTO.getId() != null)
			this.id = diagnoseDTO.getId();
		if (diagnoseDTO.getDisease() != null)
			this.disease = new Disease(diagnoseDTO.getDisease());
		if (diagnoseDTO.getPatient() != null)
			this.patient = new Patient(diagnoseDTO.getPatient());
		if (diagnoseDTO.getVet() != null)
			this.vet = new Vet(diagnoseDTO.getVet());
		this.specificSymptomsMatched = new ArrayList<Symptom>();
		for (SymptomDTO symptomDTO : diagnoseDTO.getSpecificSymptomsMatched())
			this.specificSymptomsMatched.add(new Symptom(symptomDTO));
		this.nonSpecificSymptomsMatched = new ArrayList<Symptom>();
		for (SymptomDTO symptomDTO : diagnoseDTO.getNonSpecificSymptomsMatched())
			this.nonSpecificSymptomsMatched.add(new Symptom(symptomDTO));
		if (diagnoseDTO.getSpecificSymptomsMatched() != null)
			this.specificSymptomsMatchedNum = diagnoseDTO.getSpecificSymptomsMatchedNum();
		if (diagnoseDTO.getNonSpecificSymptomsMatched() != null)
			this.nonSpecificSymptomsMatchedNum = diagnoseDTO.getNonSpecificSymptomsMatchedNum();
		this.therapies = new ArrayList<Therapy>();
		for (TherapyDTO therapyDTO : diagnoseDTO.getTherapies())
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
		this.therapies = therapies;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public List<Symptom> getSpecificSymptomsMatched() {
		return specificSymptomsMatched;
	}



	public void setSpecificSymptomsMatched(List<Symptom> specificSymptomsMatched) {
		this.specificSymptomsMatched = specificSymptomsMatched;
	}



	public List<Symptom> getNonSpecificSymptomsMatched() {
		return nonSpecificSymptomsMatched;
	}



	public void setNonSpecificSymptomsMatched(List<Symptom> nonSpecificSymptomsMatched) {
		this.nonSpecificSymptomsMatched = nonSpecificSymptomsMatched;
	}



	public Long getSpecificSymptomsMatchedNum() {
		return specificSymptomsMatchedNum;
	}



	public void setSpecificSymptomsMatchedNum(Long specificSymptomsMatchedNum) {
		this.specificSymptomsMatchedNum = specificSymptomsMatchedNum;
	}



	public Long getNonSpecificSymptomsMatchedNum() {
		return nonSpecificSymptomsMatchedNum;
	}



	public void setNonSpecificSymptomsMatchedNum(Long nonSpecificSymptomsMatchedNum) {
		this.nonSpecificSymptomsMatchedNum = nonSpecificSymptomsMatchedNum;
	}
	
	
}

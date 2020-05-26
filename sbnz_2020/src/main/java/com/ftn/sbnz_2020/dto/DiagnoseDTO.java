package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;

public class DiagnoseDTO {
	
	private Long id;
	private DiseaseDTO disease;
	private PatientDTO patient;
	private VetDTO vet;
	private List<SymptomDTO> specificSymptomsMatched;
	private List<SymptomDTO> nonSpecificSymptomsMatched;
	private Long specificSymptomsMatchedNum;
	private Long nonSpecificSymptomsMatchedNum;
	private List<TherapyDTO> therapies;
	private Date date;
	
	public DiagnoseDTO(){
	}
	
	public DiagnoseDTO(Diagnose diagnose){
		if (diagnose.getId() != null)
			this.id = diagnose.getId();
		if (diagnose.getDisease() != null)
			this.disease = new DiseaseDTO(diagnose.getDisease());
		if (diagnose.getPatient() != null)
			this.patient = new PatientDTO(diagnose.getPatient());
		if (diagnose.getVet() != null)
			this.vet = new VetDTO(diagnose.getVet());
		this.specificSymptomsMatched = new ArrayList<SymptomDTO>();
		if (diagnose.getSpecificSymptomsMatched() != null)
			for (Symptom symptom : diagnose.getSpecificSymptomsMatched())
				this.specificSymptomsMatched.add(new SymptomDTO(symptom));
		this.nonSpecificSymptomsMatched = new ArrayList<SymptomDTO>();
		if (diagnose.getNonSpecificSymptomsMatched() != null)
			for (Symptom symptom : diagnose.getNonSpecificSymptomsMatched())
				this.nonSpecificSymptomsMatched.add(new SymptomDTO(symptom));
		if (diagnose.getSpecificSymptomsMatched() != null)
			this.specificSymptomsMatchedNum = diagnose.getSpecificSymptomsMatchedNum();
		if (diagnose.getNonSpecificSymptomsMatched() != null)
			this.nonSpecificSymptomsMatchedNum = diagnose.getNonSpecificSymptomsMatchedNum();
		this.therapies = new ArrayList<TherapyDTO>();
		if (diagnose.getTherapies() != null)
			for (Therapy therapy : diagnose.getTherapies())
				this.therapies.add(new TherapyDTO(therapy));
		if (diagnose.getDate() != null)
			this.date = diagnose.getDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiseaseDTO getDisease() {
		return disease;
	}

	public void setDisease(DiseaseDTO disease) {
		this.disease = disease;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public VetDTO getVet() {
		return vet;
	}

	public void setVet(VetDTO vet) {
		this.vet = vet;
	}

	public List<SymptomDTO> getSpecificSymptomsMatched() {
		return specificSymptomsMatched;
	}

	public void setSpecificSymptomsMatched(List<SymptomDTO> specificSymptomsMatched) {
		this.specificSymptomsMatched = specificSymptomsMatched;
	}

	public List<SymptomDTO> getNonSpecificSymptomsMatched() {
		return nonSpecificSymptomsMatched;
	}

	public void setNonSpecificSymptomsMatched(List<SymptomDTO> nonSpecificSymptomsMatched) {
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

	public List<TherapyDTO> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<TherapyDTO> therapies) {
		this.therapies = therapies;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

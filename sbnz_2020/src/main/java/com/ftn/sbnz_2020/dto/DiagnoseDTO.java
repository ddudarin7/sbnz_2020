package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;

public class DiagnoseDTO {
	
	private Long id;
	private DiseaseDTO diseaseDTO;
	private PatientDTO patientDTO;
	private VetDTO vetDTO;
	private List<SymptomDTO> specificSymptomDTOs;
	private List<SymptomDTO> nonSpecificSymptomDTOs;
	private Long specificSymptomsMatched;
	private Long nonSpecificSymptomsMatched;
	private List<TherapyDTO> therapyDTOs;
	private Date date;
	
	public DiagnoseDTO(){
		
	}
	
	public DiagnoseDTO(Diagnose diagnose){
		if (diagnose.getId() != null)
			this.id = diagnose.getId();
		if (diagnose.getDisease() != null)
			this.diseaseDTO = new DiseaseDTO(diagnose.getDisease());
		if (diagnose.getPatient() != null)
			this.patientDTO = new PatientDTO(diagnose.getPatient());
		if (diagnose.getVet() != null)
			this.vetDTO = new VetDTO(diagnose.getVet());
		this.specificSymptomDTOs = new ArrayList<SymptomDTO>();
		for (Symptom symptom : diagnose.getSpecificSymptoms())
			this.specificSymptomDTOs.add(new SymptomDTO(symptom));
		this.nonSpecificSymptomDTOs = new ArrayList<SymptomDTO>();
		for (Symptom symptom : diagnose.getNonSpecificSymptoms())
			this.nonSpecificSymptomDTOs.add(new SymptomDTO(symptom));
		if (diagnose.getSpecificSymptomsMatched() != null)
			this.specificSymptomsMatched = diagnose.getSpecificSymptomsMatched();
		if (diagnose.getNonSpecificSymptomsMatched() != null)
		this.nonSpecificSymptomsMatched = diagnose.getNonSpecificSymptomsMatched();
		this.therapyDTOs = new ArrayList<TherapyDTO>();
		for (Therapy therapy : diagnose.getTherapies())
			this.therapyDTOs.add(new TherapyDTO(therapy));
		if (diagnose.getDate() != null)
			this.date = diagnose.getDate();
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DiseaseDTO getDiseaseDTO() {
		return diseaseDTO;
	}
	public void setDiseaseDTO(DiseaseDTO diseaseDTO) {
		this.diseaseDTO = diseaseDTO;
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
	public List<TherapyDTO> getTherapyDTOs() {
		return therapyDTOs;
	}
	public void setTherapyDTOs(List<TherapyDTO> therapyDTOs) {
		this.therapyDTOs = therapyDTOs;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public List<SymptomDTO> getSpecificSymptomDTOs() {
		return specificSymptomDTOs;
	}

	public void setSpecificSymptomDTOs(List<SymptomDTO> specificSymptomDTOs) {
		this.specificSymptomDTOs = specificSymptomDTOs;
	}

	public List<SymptomDTO> getNonSpecificSymptomDTOs() {
		return nonSpecificSymptomDTOs;
	}

	public void setNonSpecificSymptomDTOs(List<SymptomDTO> nonSpecificSymptomDTOs) {
		this.nonSpecificSymptomDTOs = nonSpecificSymptomDTOs;
	}

	public PatientDTO getPatientDTO() {
		return patientDTO;
	}

	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}

	public VetDTO getVetDTO() {
		return vetDTO;
	}

	public void setVetDTO(VetDTO vetDTO) {
		this.vetDTO = vetDTO;
	}
	
	
	
}

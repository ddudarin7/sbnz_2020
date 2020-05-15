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
	/*
	 * Add patient and vet
	 */
	private List<SymptomDTO> specificSymptomDTOs;
	private List<SymptomDTO> nonSpecificSymptomDTOs;
	private Long specificSymptomsMatched;
	private Long nonSpecificSymptomsMatched;
	private List<TherapyDTO> therapyDTOs;
	private Date date;
	
	public DiagnoseDTO(){
		
	}
	
	public DiagnoseDTO(Diagnose diagnose){
		this.id = diagnose.getId();
		this.diseaseDTO = new DiseaseDTO(diagnose.getDisease());
		/*
		 * Add patient and vet
		 */
		this.specificSymptomDTOs = new ArrayList<SymptomDTO>();
		for (Symptom symptom : diagnose.getSpecificSymptoms())
			this.specificSymptomDTOs.add(new SymptomDTO(symptom));
		this.nonSpecificSymptomDTOs = new ArrayList<SymptomDTO>();
		for (Symptom symptom : diagnose.getNonSpecificSymptoms())
			this.nonSpecificSymptomDTOs.add(new SymptomDTO(symptom));
		this.specificSymptomsMatched = diagnose.getSpecificSymptomsMatched();
		this.nonSpecificSymptomsMatched = diagnose.getNonSpecificSymptomsMatched();
		this.therapyDTOs = new ArrayList<TherapyDTO>();
		for (Therapy therapy : diagnose.getTherapies())
			this.therapyDTOs.add(new TherapyDTO(therapy));
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
	
	
	
}

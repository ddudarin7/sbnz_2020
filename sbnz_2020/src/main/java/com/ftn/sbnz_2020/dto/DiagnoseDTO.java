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
	private List<String> specificSymptoms;
	private List<String> nonSpecificSymptoms;
	private Long specificSymptomsMatched;
	private Long nonSpecificSymptomsMatched;
	private List<TherapyDTO> therapyDTOs;
	private Date date;
	
	public DiagnoseDTO(Diagnose diagnose){
		this.id = diagnose.getId();
		this.diseaseDTO = new DiseaseDTO(diagnose.getDisease());
		/*
		 * Add patient and vet
		 */
		this.specificSymptoms = new ArrayList<String>();
		for (Symptom symptom : diagnose.getSpecificSymptoms())
			this.specificSymptoms.add(symptom.toString());
		this.nonSpecificSymptoms = new ArrayList<String>();
		for (Symptom symptom : diagnose.getNonSpecificSymptoms())
			this.nonSpecificSymptoms.add(symptom.toString());
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
	public List<String> getSpecificSymptoms() {
		return specificSymptoms;
	}
	public void setSpecificSymptoms(List<String> specificSymptoms) {
		this.specificSymptoms = specificSymptoms;
	}
	public List<String> getNonSpecificSymptoms() {
		return nonSpecificSymptoms;
	}
	public void setNonSpecificSymptoms(List<String> nonSpecificSymptoms) {
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
	
	
	
}

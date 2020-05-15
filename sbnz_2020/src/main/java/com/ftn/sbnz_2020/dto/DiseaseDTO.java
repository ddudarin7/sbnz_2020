package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;

import com.ftn.sbnz_2020.dto.SymptomDTO;

public class DiseaseDTO {

	private Long id;
	private String name;
	private String diseaseCategory;
	private List<SymptomDTO> specificSymptomDTOs;
	private List<SymptomDTO> nonSpecificSymptomDTOs;
	private List<TherapyDTO> therapyDTOs;
	
	public DiseaseDTO(){
		
	}
	
	public DiseaseDTO(Long id, String name, String diseaseCategory, List<SymptomDTO> specificSymptoms,
			List<SymptomDTO> nonSpecificSymptoms, List<TherapyDTO> therapyDTOs) {
		this.id = id;
		this.name = name;
		this.diseaseCategory = diseaseCategory;
		this.specificSymptomDTOs = specificSymptoms;
		this.nonSpecificSymptomDTOs = nonSpecificSymptoms;
		this.therapyDTOs = therapyDTOs;
	}

	public DiseaseDTO(Long id, String name, String diseaseCategory) {
		this.id = id;
		this.name = name;
		this.diseaseCategory = diseaseCategory;
		this.specificSymptomDTOs = new ArrayList<SymptomDTO>();
		this.nonSpecificSymptomDTOs = new ArrayList<SymptomDTO>();
		this.therapyDTOs = new ArrayList<TherapyDTO>();
	}
	
	public DiseaseDTO(Disease disease){
		this.id = disease.getId();
		this.name = disease.getName();
		this.diseaseCategory = disease.getDiseaseCategory().toString();
		this.specificSymptomDTOs = new ArrayList<SymptomDTO>();
		for (Symptom symptom : disease.getSpecificSymptoms())
			this.specificSymptomDTOs.add(new SymptomDTO(symptom));
		this.nonSpecificSymptomDTOs = new ArrayList<SymptomDTO>();
		for (Symptom symptom : disease.getNonSpecificSymptoms())
			this.nonSpecificSymptomDTOs.add(new SymptomDTO(symptom));
		this.therapyDTOs = new ArrayList<TherapyDTO>();
		for (Therapy therapy : disease.getTherapies())
			this.therapyDTOs.add(new TherapyDTO(therapy));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiseaseCategory() {
		return diseaseCategory;
	}

	public void setDiseaseCategory(String diseaseCategory) {
		this.diseaseCategory = diseaseCategory;
	}

	public List<TherapyDTO> getTherapyDTOs() {
		return therapyDTOs;
	}

	public void setTherapyDTOs(List<TherapyDTO> therapyDTOs) {
		this.therapyDTOs = therapyDTOs;
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

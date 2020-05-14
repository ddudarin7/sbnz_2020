package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;

public class DiseaseDTO {

	private Long id;
	private String name;
	private String diseaseCategory;
	private List<String> specificSymptoms;
	private List<String> nonSpecificSymptoms;
	private List<TherapyDTO> therapyDTOs;
	
	public DiseaseDTO(Long id, String name, String diseaseCategory, List<String> specificSymptoms,
			List<String> nonSpecificSymptoms, List<TherapyDTO> therapyDTOs) {
		this.id = id;
		this.name = name;
		this.diseaseCategory = diseaseCategory;
		this.specificSymptoms = specificSymptoms;
		this.nonSpecificSymptoms = nonSpecificSymptoms;
		this.therapyDTOs = therapyDTOs;
	}

	public DiseaseDTO(Long id, String name, String diseaseCategory) {
		this.id = id;
		this.name = name;
		this.diseaseCategory = diseaseCategory;
		this.specificSymptoms = new ArrayList<String>();
		this.nonSpecificSymptoms = new ArrayList<String>();
		this.therapyDTOs = new ArrayList<TherapyDTO>();
	}
	
	public DiseaseDTO(Disease disease){
		this.id = disease.getId();
		this.name = disease.getName();
		this.diseaseCategory = disease.getDiseaseCategory().toString();
		this.specificSymptoms = new ArrayList<String>();
		for (Symptom symptom : disease.getSpecificSymptoms())
			this.specificSymptoms.add(symptom.toString());
		this.nonSpecificSymptoms = new ArrayList<String>();
		for (Symptom symptom : disease.getNonSpecificSymptoms())
			this.nonSpecificSymptoms.add(symptom.toString());
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

	public List<TherapyDTO> getTherapyDTOs() {
		return therapyDTOs;
	}

	public void setTherapyDTOs(List<TherapyDTO> therapyDTOs) {
		this.therapyDTOs = therapyDTOs;
	}
	
	
	
}

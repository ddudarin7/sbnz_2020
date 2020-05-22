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
	private List<SymptomDTO> specificSymptoms;
	private List<SymptomDTO> nonSpecificSymptoms;
	private List<TherapyDTO> therapies;
	
	public DiseaseDTO(){
		
	}
	
	public DiseaseDTO(Long id, String name, String diseaseCategory, List<SymptomDTO> specificSymptoms,
			List<SymptomDTO> nonSpecificSymptoms, List<TherapyDTO> therapies) {
		this.id = id;
		this.name = name;
		this.diseaseCategory = diseaseCategory;
		this.specificSymptoms = specificSymptoms;
		this.nonSpecificSymptoms = nonSpecificSymptoms;
		this.therapies = therapies;
	}

	public DiseaseDTO(Long id, String name, String diseaseCategory) {
		this.id = id;
		this.name = name;
		this.diseaseCategory = diseaseCategory;
		this.specificSymptoms = new ArrayList<SymptomDTO>();
		this.nonSpecificSymptoms = new ArrayList<SymptomDTO>();
		this.therapies = new ArrayList<TherapyDTO>();
	}
	
	public DiseaseDTO(Disease disease){
		if (disease.getId() != null)
			this.id = disease.getId();
		if (disease.getName() != null)
			this.name = disease.getName();
		if (disease.getDiseaseCategory() != null)
			this.diseaseCategory = disease.getDiseaseCategory().toString();
		this.specificSymptoms = new ArrayList<SymptomDTO>();
		if (disease.getSpecificSymptoms() != null)
			for (Symptom symptom : disease.getSpecificSymptoms())
				this.specificSymptoms.add(new SymptomDTO(symptom));
		this.nonSpecificSymptoms = new ArrayList<SymptomDTO>();
		if (disease.getNonSpecificSymptoms() != null)
			for (Symptom symptom : disease.getNonSpecificSymptoms())
				this.nonSpecificSymptoms.add(new SymptomDTO(symptom));
		this.therapies = new ArrayList<TherapyDTO>();
		if (disease.getTherapies() != null)
			for (Therapy therapy : disease.getTherapies())
				this.therapies.add(new TherapyDTO(therapy));
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

	public List<SymptomDTO> getSpecificSymptoms() {
		return specificSymptoms;
	}

	public void setSpecificSymptoms(List<SymptomDTO> specificSymptoms) {
		this.specificSymptoms = specificSymptoms;
	}

	public List<SymptomDTO> getNonSpecificSymptoms() {
		return nonSpecificSymptoms;
	}

	public void setNonSpecificSymptoms(List<SymptomDTO> nonSpecificSymptoms) {
		this.nonSpecificSymptoms = nonSpecificSymptoms;
	}

	public List<TherapyDTO> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<TherapyDTO> therapies) {
		this.therapies = therapies;
	}
	
}

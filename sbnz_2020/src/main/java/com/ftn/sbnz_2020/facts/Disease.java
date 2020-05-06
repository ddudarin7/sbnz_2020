package com.ftn.sbnz_2020.facts;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "disease_group")
    private DiseaseCategory diseaseCategory;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
    private List<Symptom> specificSymptoms;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
    private List<Symptom> nonSpecificSymptoms;
	
    private List<Therapy> therapies;

	public Disease() {
		super();
	}

	public Disease(Long id, String name, DiseaseCategory diseaseCategory, List<Symptom> specificSymptoms,
			List<Symptom> nonSpecificSymptoms, List<Therapy> therapies) {
		super();
		this.id = id;
		this.name = name;
		this.diseaseCategory = diseaseCategory;
		this.specificSymptoms = specificSymptoms;
		this.nonSpecificSymptoms = nonSpecificSymptoms;
		this.therapies = therapies;
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

	public DiseaseCategory getDiseaseCategory() {
		return diseaseCategory;
	}

	public void setDiseaseCategory(DiseaseCategory diseaseCategory) {
		this.diseaseCategory = diseaseCategory;
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

	public List<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<Therapy> therapies) {
		this.therapies = therapies;
	}
    
}

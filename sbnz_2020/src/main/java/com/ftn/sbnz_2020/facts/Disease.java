package com.ftn.sbnz_2020.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.ftn.sbnz_2020.dto.DiseaseDTO;
import com.ftn.sbnz_2020.dto.SymptomDTO;
import com.ftn.sbnz_2020.dto.TherapyDTO;

@Entity
@Embeddable
@Table(name = "disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "disease_group")
    @Enumerated(EnumType.STRING)
    private DiseaseCategory diseaseCategory;

    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE})
    @Column(name = "specificSymptoms", nullable = false)
    private List<Symptom> specificSymptoms;

    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE})
    @Column(name = "nonSpecificSymptoms", nullable = false)
    private List<Symptom> nonSpecificSymptoms;
	
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
    private List<Therapy> therapies;

    @Transient
	private List<Symptom> specificSymptomsMatched;
    
    @Transient
	private List<Symptom> nonSpecificSymptomsMatched;
    
    @Transient
    private Long specificSymptomsMatchedNum;
    
    @Transient
    private Long nonSpecificSymptomsMatchedNum;
    
	public Disease() {
		super();
		this.specificSymptomsMatched = new ArrayList<Symptom>();
		this.nonSpecificSymptomsMatched = new ArrayList<Symptom>();
		this.specificSymptomsMatchedNum = 0L;
		this.nonSpecificSymptomsMatchedNum = 0L;

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
		this.specificSymptomsMatched = new ArrayList<Symptom>();
		this.nonSpecificSymptomsMatched = new ArrayList<Symptom>();
		this.specificSymptomsMatchedNum = 0L;
		this.nonSpecificSymptomsMatchedNum = 0L;
	}
	
	public Disease(DiseaseDTO diseaseDTO){
		this.id = diseaseDTO.getId();
		this.name = diseaseDTO.getName();
		this.diseaseCategory = DiseaseCategory.valueOf(diseaseDTO.getDiseaseCategory());
		this.specificSymptoms = new ArrayList<Symptom>();
		for (SymptomDTO symptomDTO : diseaseDTO.getSpecificSymptomDTOs())
			this.specificSymptoms.add(new Symptom(symptomDTO));
		this.nonSpecificSymptoms = new ArrayList<Symptom>();
		for (SymptomDTO symptomDTO : diseaseDTO.getNonSpecificSymptomDTOs())
			this.nonSpecificSymptoms.add(new Symptom(symptomDTO));
		this.therapies = new ArrayList<Therapy>();
		for (TherapyDTO therapyDTO : diseaseDTO.getTherapyDTOs())
			this.therapies.add(new Therapy(therapyDTO));
		this.specificSymptomsMatched = new ArrayList<Symptom>();
		this.nonSpecificSymptomsMatched = new ArrayList<Symptom>();
		this.specificSymptomsMatchedNum = 0L;
		this.nonSpecificSymptomsMatchedNum = 0L;
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

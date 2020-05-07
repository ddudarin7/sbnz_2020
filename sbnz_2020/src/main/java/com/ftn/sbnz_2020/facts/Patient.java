package com.ftn.sbnz_2020.facts;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "patient")
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "record_number", unique = true)
    private String recordNumber;
    
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    private Breed breed;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
    private List<Medicine> medicineAllergies;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
    private List<Ingredient> ingredientAllergies;

	public Patient() {
		super();
	}

	public Patient(Long id, String name, String recordNumber, Date dateOfBirth, Breed breed, Owner owner,
			List<Medicine> medicineAllergies, List<Ingredient> ingredientAllergies) {
		super();
		this.id = id;
		this.name = name;
		this.recordNumber = recordNumber;
		this.dateOfBirth = dateOfBirth;
		this.breed = breed;
		this.owner = owner;
		this.medicineAllergies = medicineAllergies;
		this.ingredientAllergies = ingredientAllergies;
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

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<Medicine> getMedicineAllergies() {
		return medicineAllergies;
	}

	public void setMedicineAllergies(List<Medicine> medicineAllergies) {
		this.medicineAllergies = medicineAllergies;
	}

	public List<Ingredient> getIngredientAllergies() {
		return ingredientAllergies;
	}

	public void setIngredientAllergies(List<Ingredient> ingredientAllergies) {
		this.ingredientAllergies = ingredientAllergies;
	}
    
}

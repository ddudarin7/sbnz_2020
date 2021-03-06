package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ftn.sbnz_2020.facts.Breed;
import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Vaccination;

public class PatientDTO {
	private Long id;
    private String name;
    private String recordNumber;
    private Breed breed;
    private Date dateOfBirth;
    private List<MedicineDTO> medicineAllergies;
    private List<IngredientDTO> ingredientAllergies;
    private List<VaccinationDTO> vaccinations;
    private OwnerDTO owner;
    
	public PatientDTO() {
		super();
	}
	
	public PatientDTO(Patient patient) {
		if (patient.getId() != null)
			this.id = patient.getId();
		if (patient.getName() != null)
			this.name = patient.getName();
		if (patient.getRecordNumber() != null)
			this.recordNumber = patient.getRecordNumber();
		if (patient.getBreed() != null)
			this.breed = patient.getBreed();
		if (patient.getDateOfBirth() != null)
			this.dateOfBirth = patient.getDateOfBirth();
		this.medicineAllergies = new ArrayList<MedicineDTO>();
		if (patient.getMedicineAllergies() != null)
			for (Medicine medicine: patient.getMedicineAllergies())
            this.medicineAllergies.add(new MedicineDTO(medicine));
        this.ingredientAllergies = new ArrayList<IngredientDTO>();
        if (patient.getIngredientAllergies() != null)
        	for (Ingredient ingredient: patient.getIngredientAllergies())
        		this.ingredientAllergies.add(new IngredientDTO(ingredient));
        this.vaccinations = new ArrayList<VaccinationDTO>();
        if (patient.getVaccinations() != null)
        	for (Vaccination vaccination: patient.getVaccinations())
        		this.vaccinations.add(new VaccinationDTO(vaccination));
        if (patient.getOwner() != null)
        	this.owner = new OwnerDTO(patient.getOwner());
        
	}

	public PatientDTO(Long id, String name, String recordNumber, Breed breed, Date dateOfBirth,
			List<MedicineDTO> medicineAllergies, List<IngredientDTO> ingredientAllergies, List<VaccinationDTO> vaccinations, 
			OwnerDTO owner) {
		super();
		this.id = id;
		this.name = name;
		this.recordNumber = recordNumber;
		this.breed = breed;
		this.dateOfBirth = dateOfBirth;
		this.medicineAllergies = medicineAllergies;
		this.ingredientAllergies = ingredientAllergies;
		this.vaccinations = vaccinations;
		this.owner = owner;
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

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<MedicineDTO> getMedicineAllergies() {
		return medicineAllergies;
	}

	public void setMedicineAllergies(List<MedicineDTO> medicineAllergies) {
		this.medicineAllergies = medicineAllergies;
	}

	public List<IngredientDTO> getIngredientAllergies() {
		return ingredientAllergies;
	}

	public void setIngredientAllergies(List<IngredientDTO> ingredientAllergies) {
		this.ingredientAllergies = ingredientAllergies;
	}

	public List<VaccinationDTO> getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(List<VaccinationDTO> vaccinations) {
		this.vaccinations = vaccinations;
	}

	public OwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}
    
}

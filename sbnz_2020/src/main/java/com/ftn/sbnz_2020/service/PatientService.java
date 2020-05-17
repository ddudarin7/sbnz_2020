package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Therapy;
import com.ftn.sbnz_2020.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	PatientRepository patientRepository;

	@Autowired
	MedicineService medicineService;

	@Autowired
	IngredientService ingredientService;

	@Autowired
	TherapyService therapyService;

	@Autowired
	DiseaseService diseaseService;

	public Patient findById(Long id) {
		return patientRepository.findById(id).get();
	}

	public Patient findByRecordNumber(String recordNumber) {
		return patientRepository.findByRecordNumber(recordNumber);
	}

	public List<Patient> findAll() {
		return patientRepository.findAll();
	}

	public Page<Patient> findAll(Pageable pageable) {
		return patientRepository.findAll(pageable);
	}

	@SuppressWarnings("Duplicates")
	public Patient save(Patient patient) {
		patient.setId(null);
		for (int i = 0; i < patient.getMedicineAllergies().size(); i++) {
			Medicine medicine = patient.getMedicineAllergies().get(i);
			Medicine med = medicineService.findByName(medicine.getName());
			if (med == null) {
				//patient.getMedicineAllergies().set(i,
				//		medicineService.save(new Medicine(medicine.getName(), MedicineType.OTHER)));
			} else {
				patient.getMedicineAllergies().set(i, med);
			}
		}
		for (int i = 0; i < patient.getIngredientAllergies().size(); i++) {
			Ingredient ingredient = patient.getIngredientAllergies().get(i);
			Ingredient ingr = ingredientService.findByName(ingredient.getName());
			if (ingr == null) {
				//patient.getIngredientAllergies().set(i, ingredientService.save(new Ingredient(ingredient.getName())));
			} else {
				patient.getIngredientAllergies().set(i, ingr);
			}
		}
		return patientRepository.save(patient);
	}

	@SuppressWarnings("Duplicates")
	public Patient update(Patient patientUpdate) {
		Patient patient = this.findById(patientUpdate.getId());
		patient.setDateOfBirth(patientUpdate.getDateOfBirth());
		patient.setName(patientUpdate.getName());
		patient.setBreed(patientUpdate.getBreed());
		patient.setOwner(patientUpdate.getOwner());
		patient.setRecordNumber(patientUpdate.getRecordNumber());

		for (int i = 0; i < patientUpdate.getMedicineAllergies().size(); i++) {
			Medicine medicine = patientUpdate.getMedicineAllergies().get(i);
			Medicine med = medicineService.findByName(medicine.getName());
			if (med == null) {
				//patientUpdate.getMedicineAllergies().set(i,
				//		medicineService.save(new Medicine(medicine.getName(), MedicineType.OTHER)));
			} else {
				patientUpdate.getMedicineAllergies().set(i, med);
			}
		}
		patient.setMedicineAllergies(patientUpdate.getMedicineAllergies());

		for (int i = 0; i < patientUpdate.getIngredientAllergies().size(); i++) {
			Ingredient ingredient = patientUpdate.getIngredientAllergies().get(i);
			Ingredient ingr = ingredientService.findByName(ingredient.getName());
			if (ingr == null) {
				//patientUpdate.getIngredientAllergies().set(i,
				//		ingredientService.save(new Ingredient(ingredient.getName())));
			} else {
				patientUpdate.getIngredientAllergies().set(i, ingr);
			}
		}
		patient.setIngredientAllergies(patientUpdate.getIngredientAllergies());

		return patientRepository.save(patient);
	}

	public void delete(Long id) {
		patientRepository.delete(this.findById(id));
	}

	public void deleteAll() {
		therapyService.deleteAll();

		patientRepository.deleteAllInBatch();
	}
}
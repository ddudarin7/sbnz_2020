package com.ftn.sbnz_2020.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.dto.BreedDiseases;
import com.ftn.sbnz_2020.dto.BreedDiseasesDTO;
import com.ftn.sbnz_2020.dto.DiseaseDTO;
import com.ftn.sbnz_2020.dto.ReportChronicDiseasesDTO;
import com.ftn.sbnz_2020.dto.ReportWeakenedImmuneSystemDTO;
import com.ftn.sbnz_2020.facts.Breed;
import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Vaccination;
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
	
	@Autowired
	VaccinationService vaccinationService;
	
	@Autowired
	DiagnoseService diagnoseService;

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
		for (int i = 0; i < patient.getVaccinations().size(); i++) {
			Vaccination vaccination = patient.getVaccinations().get(i);
			Vaccination vacc = vaccinationService.findById(vaccination.getId());
			if (vacc == null) {
				//patient.getIngredientAllergies().set(i, ingredientService.save(new Ingredient(ingredient.getName())));
			} else {
				patient.getVaccinations().set(i, vacc);
			}
		}
		patient = patientRepository.save(patient);
		patient.setRecordNumber("REC" + patient.getId());
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

		for (int i = 0; i < patientUpdate.getVaccinations().size(); i++) {
			Vaccination vaccination = patientUpdate.getVaccinations().get(i);
			Vaccination vacc = vaccinationService.findById(vaccination.getId());
			if (vacc == null) {
				//patientUpdate.getIngredientAllergies().set(i,
				//		ingredientService.save(new Ingredient(ingredient.getName())));
			} else {
				patientUpdate.getVaccinations().set(i, vacc);
			}
		}
		
		// remove removed vaccinations
		
		
		List<Vaccination> oldVaccinations = patient.getVaccinations();
		
		patient.setVaccinations(patientUpdate.getVaccinations());
		patient = patientRepository.save(patient);
		
		boolean vaccinationFound;
		for (Vaccination oldVaccination : oldVaccinations){
			vaccinationFound = false;
			for (Vaccination newVaccination : patientUpdate.getVaccinations()){
				if (oldVaccination.getId() == newVaccination.getId()){
					vaccinationFound = true;
					break;
				}
			}
			if (!vaccinationFound)
				vaccinationService.delete(oldVaccination.getId());			
		}
		
		return patient;
	}

	public void delete(Long id) {
		for (Diagnose diagnose : this.diagnoseService.findByPatientId(id))
			this.diagnoseService.delete(diagnose.getId());
		patientRepository.delete(this.findById(id));
	}

	public void deleteAll() {
		this.diagnoseService.deleteAll();
		patientRepository.deleteAll();
	}
	
	public List<ReportChronicDiseasesDTO> chronicDiseaseReport(KieSession kieSession){
		for(Patient p:patientRepository.findAll()) {
			kieSession.insert(p);
		}
		
		for(Diagnose d:diagnoseService.findAll()) {
			kieSession.insert(d);
		}
		
		ArrayList<ReportChronicDiseasesDTO> result=new ArrayList<>();
		kieSession.insert(result);
		
		kieSession.getAgenda().getAgendaGroup("chronic diseases").setFocus();
		kieSession.fireAllRules();
		
		releaseObjectsFromSession(kieSession);
		
		return result;
	}
	
	public List<ReportWeakenedImmuneSystemDTO> weakenedImmuneSystemReport(KieSession kieSession){
		for(Patient p:patientRepository.findAll()) {
			kieSession.insert(p);
		}
		
		for(Diagnose d:diagnoseService.findAll()) {
			kieSession.insert(d);
		}
		
		ArrayList<ReportWeakenedImmuneSystemDTO> result=new ArrayList<>();
		kieSession.insert(result);
		
		kieSession.getAgenda().getAgendaGroup("weakened immunity").setFocus();
		kieSession.fireAllRules();
		
		releaseObjectsFromSession(kieSession);
		
		return result;
	}
	
	 private void releaseObjectsFromSession(KieSession kieSession){
	        kieSession.getObjects();

	        for( Object object: kieSession.getObjects() ){
	            kieSession.delete( kieSession.getFactHandle( object ) );
	        }
	 }
	 
	 public List<Patient> findByOwnerId(Long ownerId){
		 return patientRepository.findAllByOwnerId(ownerId);
	 }
	 
	public BreedDiseasesDTO breedDiseasesReport(KieSession kieSession, Breed breed){
		BreedDiseases bd = new BreedDiseases();
		bd.setBreed(breed);
		
		kieSession.insert(bd);
		
		for(Diagnose diagnose : diagnoseService.findAll())
			kieSession.insert(diagnose);
		
		kieSession.getAgenda().getAgendaGroup("breed diseases").setFocus();
		kieSession.fireAllRules();
		
		releaseObjectsFromSession(kieSession);
		
		BreedDiseasesDTO bdDTO = new BreedDiseasesDTO();
		bdDTO.setBreed(bd.getBreed().toString());
		
		for (Long key : bd.getDiseases().keySet()){
			bdDTO.addToData(new DiseaseDTO(diseaseService.findById(key)), bd.getDiseases().get(key));
		}
		
		bdDTO.sortData();
		bdDTO.calculateDiagnoses();
		
		return bdDTO;
	}
}

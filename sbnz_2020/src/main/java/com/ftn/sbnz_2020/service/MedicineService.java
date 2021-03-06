package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.repository.IngredientRepository;
import com.ftn.sbnz_2020.repository.MedicineRepository;

@Service
public class MedicineService {

	@Autowired
	MedicineRepository medicineRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	public Medicine findById(Long id){ return medicineRepository.getOne(id); }
	
	public Medicine findByName(String name){ return medicineRepository.findByName(name); }
	
	public List<Medicine> findAll() { return medicineRepository.findAll(); }
	
	public Page<Medicine> findAll(Pageable pageable) { return medicineRepository.findAll(pageable); }
	
	public Medicine add(Medicine medicine){
		medicine.setId(null);
		for(Ingredient ingr: medicine.getIngredients()) {
			Ingredient ingredient = ingredientRepository.findByName(ingr.getName());
			if(ingredient == null) {
				ingr.setId(null);
				ingredientRepository.save(ingr);
			}
		}
		return medicineRepository.save(medicine); 
	}
	
	public Medicine update(Medicine updatedMedicine){
		Medicine medicine = medicineRepository.getOne(updatedMedicine.getId());
		if (medicine == null)
			return null;
		
		medicine.setName(updatedMedicine.getName());
		medicine.setIngredients(updatedMedicine.getIngredients());
		
		return medicineRepository.save(medicine);
	}
	
	public void delete(Long id){ medicineRepository.deleteById(id); }
	
	public void deleteAll() { medicineRepository.deleteAll(); }
	
}

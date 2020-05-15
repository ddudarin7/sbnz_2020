package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.repository.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;
	
	public Ingredient findById(Long id){ return ingredientRepository.getOne(id); }
	
	public Ingredient findByName(String name){ return ingredientRepository.findByName(name); }
	
	public List<Ingredient> findAll() { return ingredientRepository.findAll(); }
	
	public Page<Ingredient> findAll(Pageable pageable) { return ingredientRepository.findAll(pageable); }
	
	public Ingredient add(Ingredient ingredient){
		ingredient.setId(null);
		return ingredientRepository.save(ingredient); 
	}
	
	public Ingredient update(Ingredient updatedIngredient){
		Ingredient ingredient = ingredientRepository.getOne(updatedIngredient.getId());
		if (ingredient == null)
			return null;
		
		ingredient.setName(updatedIngredient.getName());
		
		return ingredientRepository.save(ingredient);
	}
	
	public void delete(Long id){ ingredientRepository.deleteById(id); }
	
	public void deleteAll() { ingredientRepository.deleteAll(); }
	
}

package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;

public class MedicineDTO {
	
	private Long id;
	private String name;
	private List<IngredientDTO> ingredients;
	
	public MedicineDTO(){
		
	}
	
	public MedicineDTO(Long id, String name){
		this.id = id;
		this.name = name;
		this.ingredients = new ArrayList<IngredientDTO>();
	}
	
	public MedicineDTO(Medicine medicine){
		if (medicine.getId() != null)
			this.id = medicine.getId();
		if (medicine.getName() != null)
			this.name = medicine.getName();
		this.ingredients = new ArrayList<IngredientDTO>();
		if (medicine.getIngredients() != null)
			for (Ingredient ingredient : medicine.getIngredients())
				this.ingredients.add(new IngredientDTO(ingredient));
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

	public List<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

}

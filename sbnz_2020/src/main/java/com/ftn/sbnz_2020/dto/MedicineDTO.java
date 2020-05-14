package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;

public class MedicineDTO {
	
	private Long id;
	private String name;
	private List<IngredientDTO> ingredientDTOs;
	
	public MedicineDTO(Long id, String name){
		this.id = id;
		this.name = name;
		this.ingredientDTOs = new ArrayList<IngredientDTO>();
	}
	
	public MedicineDTO(Medicine medicine){
		this.id = medicine.getId();
		this.name = medicine.getName();
		this.ingredientDTOs = new ArrayList<IngredientDTO>();
		for (Ingredient ingredient : medicine.getIngredients())
			this.ingredientDTOs.add(new IngredientDTO(ingredient));
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

	public List<IngredientDTO> getIngredientDTOs() {
		return ingredientDTOs;
	}

	public void setIngredientDTOs(List<IngredientDTO> ingredientDTOs) {
		this.ingredientDTOs = ingredientDTOs;
	}
	
	
	
}

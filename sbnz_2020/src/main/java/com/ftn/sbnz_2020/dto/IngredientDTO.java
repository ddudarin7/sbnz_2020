package com.ftn.sbnz_2020.dto;

import com.ftn.sbnz_2020.facts.Ingredient;

public class IngredientDTO {
	
	private Long id;
	private String name;
	
	public IngredientDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public IngredientDTO(Ingredient ingredient){
		this.id = ingredient.getId();
		this.name = ingredient.getName();
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
	
	
}

package com.ftn.sbnz_2020.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.ftn.sbnz_2020.dto.IngredientDTO;
import com.ftn.sbnz_2020.dto.MedicineDTO;

@Entity
@Table(name = "medicine")
public class Medicine {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.MERGE}) // unidirectional
	private List<Ingredient> ingredients;

	public Medicine() {
		super();
		ingredients = new ArrayList<Ingredient>();
	}

	public Medicine(Long id, String name, List<Ingredient> ingredients) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
	}
	
	public Medicine(Long id, String name) {
		this.id = id;
		this.name = name;
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	public Medicine(MedicineDTO medicineDTO){
		this.id = medicineDTO.getId();
		this.name = medicineDTO.getName();
		this.ingredients = new ArrayList<Ingredient>();
		for (IngredientDTO ingredientDTO : medicineDTO.getIngredients())
			this.ingredients.add(new Ingredient(ingredientDTO));
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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}

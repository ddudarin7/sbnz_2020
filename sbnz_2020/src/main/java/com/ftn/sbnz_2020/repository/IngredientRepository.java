package com.ftn.sbnz_2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz_2020.facts.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	Ingredient findByName(String name);
}

package com.ftn.sbnz_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{
	Medicine findByName(String name);
	List<Medicine> findAllByIngredient(Ingredient ingredient);
	List<Medicine> findAllByIngredientId(Long id);
}

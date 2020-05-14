package com.ftn.sbnz_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz_2020.dto.IngredientDTO;
import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.service.IngredientService;

@RestController
public class IngredientController {
	
	@Autowired
	IngredientService ingredientService;

	@GetMapping(value = "/ingredients/{id}", produces = "application/json")
    public ResponseEntity<IngredientDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Ingredient ingredient = ingredientService.findById(id);
        if (ingredient == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new IngredientDTO(ingredient), HttpStatus.OK);
    }

    @GetMapping(value = "/ingredients/name/{name}", produces = "application/json")
    public ResponseEntity<IngredientDTO> findByName(@PathVariable String name, HttpServletRequest request) {

        Ingredient ingredient = ingredientService.findByName(name);
        if (ingredient == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new IngredientDTO(ingredient), HttpStatus.OK);
    }

    @GetMapping(value = "/ingredients", produces = "application/json")
    public ResponseEntity<List<IngredientDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<IngredientDTO> ingredientDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Ingredient> ingredients = ingredientService.findAll(PageRequest.of(pageNum, pageSize));
            ingredientDTOs = new ArrayList<>();
            for (Ingredient ingredient: ingredients.getContent()) {
                ingredientDTOs.add(new IngredientDTO(ingredient));
            }
        } else {
            List<Ingredient> ingredients = ingredientService.findAll();
            ingredientDTOs = new ArrayList<>();
            for (Ingredient ingredient: ingredients) {
                ingredientDTOs.add(new IngredientDTO(ingredient));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(ingredientDTOs.size()));
        return new ResponseEntity<>(ingredientDTOs, headers, HttpStatus.OK);
    }
	
    @PostMapping(value = "/ingredients", consumes = "application/json")
    public ResponseEntity<IngredientDTO> add(@RequestBody IngredientDTO ingredientDTO, 
    		HttpServletRequest request) {

        Ingredient ingredient = new Ingredient(ingredientDTO);
        ingredient = ingredientService.add(ingredient);
        return new ResponseEntity<>(new IngredientDTO(ingredient), HttpStatus.CREATED);
    }

    @PutMapping(value = "/ingredients", consumes = "application/json")
    public ResponseEntity<IngredientDTO> edit(@RequestBody IngredientDTO ingredientDTO,
    		HttpServletRequest request) {

        Ingredient ingredient = ingredientService.findById(ingredientDTO.getId());
        if (ingredient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Ingredient ingredientUpdate = new Ingredient(ingredientDTO);
        ingredient = ingredientService.update(ingredientUpdate);
        return new ResponseEntity<>(new IngredientDTO(ingredient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/ingredients/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Ingredient ingredient = ingredientService.findById(id);
        if (ingredient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ingredientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/ingredients")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        ingredientService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}

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

import com.ftn.sbnz_2020.dto.DiseaseDTO;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.service.DiseaseService;

@RestController
public class DiseaseController {

	@Autowired
	DiseaseService diseaseService;
	
	@GetMapping(value = "/diseases/{id}", produces = "application/json")
    public ResponseEntity<DiseaseDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Disease disease = diseaseService.findById(id);
        if (disease == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new DiseaseDTO(disease), HttpStatus.OK);
    }

    @GetMapping(value = "/diseases/name/{name}", produces = "application/json")
    public ResponseEntity<DiseaseDTO> findByName(@PathVariable String name, HttpServletRequest request) {

        Disease disease = diseaseService.findByName(name);
        if (disease == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new DiseaseDTO(disease), HttpStatus.OK);
    }

    @GetMapping(value = "/diseases", produces = "application/json")
    public ResponseEntity<List<DiseaseDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<DiseaseDTO> diseaseDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Disease> diseases = diseaseService.findAll(PageRequest.of(pageNum, pageSize));
            diseaseDTOs = new ArrayList<>();
            for (Disease disease: diseases.getContent()) {
                diseaseDTOs.add(new DiseaseDTO(disease));
            }
        } else {
            List<Disease> diseases = diseaseService.findAll();
            diseaseDTOs = new ArrayList<>();
            for (Disease disease: diseases) {
                diseaseDTOs.add(new DiseaseDTO(disease));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(diseaseDTOs.size()));
        return new ResponseEntity<>(diseaseDTOs, headers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/diseases/category/{category}", produces = "application/json")
    public ResponseEntity<List<DiseaseDTO>> findAllByDiseaseCategory(
    		@PathVariable String category, @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {
    	/*
    	 * Needs test
    	 */
        List<DiseaseDTO> diseaseDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Disease> diseases = diseaseService.findAllByDiseaseCategory(
            		DiseaseCategory.valueOf(category), PageRequest.of(pageNum, pageSize));
            diseaseDTOs = new ArrayList<>();
            for (Disease disease: diseases.getContent()) {
                diseaseDTOs.add(new DiseaseDTO(disease));
            }
        } else {
            List<Disease> diseases = diseaseService.findAllByDiseaseCategory(
            		DiseaseCategory.valueOf(category));
            diseaseDTOs = new ArrayList<>();
            for (Disease disease: diseases) {
                diseaseDTOs.add(new DiseaseDTO(disease));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(diseaseDTOs.size()));
        return new ResponseEntity<>(diseaseDTOs, headers, HttpStatus.OK);
    }
    
    @PostMapping(value = "/diseases", consumes = "application/json")
    public ResponseEntity<DiseaseDTO> add(@RequestBody DiseaseDTO diseaseDTO, 
    		HttpServletRequest request) {

        Disease disease = new Disease(diseaseDTO);
        disease = diseaseService.add(disease);
        return new ResponseEntity<>(new DiseaseDTO(disease), HttpStatus.CREATED);
    }

    @PutMapping(value = "/diseases", consumes = "application/json")
    public ResponseEntity<DiseaseDTO> update(@RequestBody DiseaseDTO diseaseDTO,
    		HttpServletRequest request) {

        Disease disease = diseaseService.findById(diseaseDTO.getId());
        if (disease == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Disease diseaseUpdated = new Disease(diseaseDTO);
        disease = diseaseService.update(diseaseUpdated);
        return new ResponseEntity<>(new DiseaseDTO(disease), HttpStatus.OK);
    }

    @DeleteMapping(value = "/diseases/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Disease disease = diseaseService.findById(id);
        if (disease == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        diseaseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/diseases")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        diseaseService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}

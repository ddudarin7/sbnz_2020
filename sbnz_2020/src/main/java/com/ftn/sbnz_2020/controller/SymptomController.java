package com.ftn.sbnz_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
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

import com.ftn.sbnz_2020.dto.SymptomDTO;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.service.SymptomService;

@RestController
public class SymptomController {

	@Autowired
	SymptomService symptomService;
	
	@GetMapping(value = "/symptoms/{id}", produces = "application/json")
    public ResponseEntity<SymptomDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Symptom symptom = symptomService.findById(id);
        if (symptom == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new SymptomDTO(symptom), HttpStatus.OK);
    }

    @GetMapping(value = "/symptoms/name/{name}", produces = "application/json")
    public ResponseEntity<SymptomDTO> findByName(@PathVariable String name, HttpServletRequest request) {

        Symptom symptom = symptomService.findByName(name);
        if (symptom == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new SymptomDTO(symptom), HttpStatus.OK);
    }

    @GetMapping(value = "/symptoms", produces = "application/json")
    public ResponseEntity<List<SymptomDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<SymptomDTO> symptomDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Symptom> symptoms = symptomService.findAll(PageRequest.of(pageNum, pageSize));
            symptomDTOs = new ArrayList<>();
            for (Symptom symptom: symptoms.getContent()) {
                symptomDTOs.add(new SymptomDTO(symptom));
            }
        } else {
            List<Symptom> symptoms = symptomService.findAll();
            symptomDTOs = new ArrayList<>();
            for (Symptom symptom: symptoms) {
                symptomDTOs.add(new SymptomDTO(symptom));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(symptomDTOs.size()));
        return new ResponseEntity<>(symptomDTOs, headers, HttpStatus.OK);
    }
	
    @PostMapping(value = "/symptoms", consumes = "application/json")
    public ResponseEntity<SymptomDTO> add(@RequestBody SymptomDTO symptomDTO, 
    		HttpServletRequest request) {

        Symptom symptom = new Symptom(symptomDTO);
        symptom = symptomService.add(symptom);
        return new ResponseEntity<>(new SymptomDTO(symptom), HttpStatus.CREATED);
    }

    @PutMapping(value = "/symptoms", consumes = "application/json")
    public ResponseEntity<SymptomDTO> update(@RequestBody SymptomDTO symptomDTO,
    		HttpServletRequest request) {

        Symptom symptom = symptomService.findById(symptomDTO.getId());
        if (symptom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Symptom symptomUpdated = new Symptom(symptomDTO);
        symptom = symptomService.update(symptomUpdated);
        return new ResponseEntity<>(new SymptomDTO(symptom), HttpStatus.OK);
    }

    @DeleteMapping(value = "/symptoms/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Symptom symptom = symptomService.findById(id);
        if (symptom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        symptomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/symptoms")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        symptomService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}

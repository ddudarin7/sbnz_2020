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

import com.ftn.sbnz_2020.dto.TherapyDTO;
import com.ftn.sbnz_2020.facts.Therapy;
import com.ftn.sbnz_2020.service.TherapyService;

@RestController
public class TherapyController {

	@Autowired
	TherapyService therapyService;
	
	@GetMapping(value = "/therapies/{id}", produces = "application/json")
    public ResponseEntity<TherapyDTO> findById(@PathVariable Long id, 
    		HttpServletRequest request) {

        Therapy therapy = therapyService.findById(id);
        if (therapy == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new TherapyDTO(therapy), HttpStatus.OK);
    }

    @GetMapping(value = "/therapies/name/{description}", produces = "application/json")
    public ResponseEntity<TherapyDTO> findByDescrtiption(@PathVariable String description,
    		HttpServletRequest request) {

        Therapy therapy = therapyService.findByDescription(description);
        if (therapy == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new TherapyDTO(therapy), HttpStatus.OK);
    }

    @GetMapping(value = "/therapies", produces = "application/json")
    public ResponseEntity<List<TherapyDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<TherapyDTO> therapyDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Therapy> therapies = therapyService.findAll(PageRequest.of(pageNum, pageSize));
            therapyDTOs = new ArrayList<>();
            for (Therapy therapy: therapies.getContent()) {
                therapyDTOs.add(new TherapyDTO(therapy));
            }
        } else {
            List<Therapy> therapies = therapyService.findAll();
            therapyDTOs = new ArrayList<>();
            for (Therapy therapy: therapies) {
                therapyDTOs.add(new TherapyDTO(therapy));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(therapyDTOs.size()));
        return new ResponseEntity<>(therapyDTOs, headers, HttpStatus.OK);
    }
	
    @PostMapping(value = "/therapies", consumes = "application/json")
    public ResponseEntity<TherapyDTO> add(@RequestBody TherapyDTO therapyDTO, 
    		HttpServletRequest request) {

        Therapy therapy = new Therapy(therapyDTO);
        therapy = therapyService.add(therapy);
        return new ResponseEntity<>(new TherapyDTO(therapy), HttpStatus.CREATED);
    }

    @PutMapping(value = "/therapies", consumes = "application/json")
    public ResponseEntity<TherapyDTO> update(@RequestBody TherapyDTO therapyDTO,
    		HttpServletRequest request) {

        Therapy therapy = therapyService.findById(therapyDTO.getId());
        if (therapy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Therapy therapyUpdated = new Therapy(therapyDTO);
        therapy = therapyService.update(therapyUpdated);
        return new ResponseEntity<>(new TherapyDTO(therapy), HttpStatus.OK);
    }

    @DeleteMapping(value = "/therapies/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Therapy therapy = therapyService.findById(id);
        if (therapy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        therapyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/therapies")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        therapyService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}

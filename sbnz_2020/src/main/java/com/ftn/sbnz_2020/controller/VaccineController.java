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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz_2020.dto.VaccineDTO;
import com.ftn.sbnz_2020.facts.Vaccine;
import com.ftn.sbnz_2020.service.VaccineService;

@RestController
@RequestMapping("/api")
public class VaccineController {
	
	@Autowired
	VaccineService vaccineService;
	
	@GetMapping(value = "/vaccines/{id}", produces = "application/json")
    public ResponseEntity<VaccineDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Vaccine vaccine = vaccineService.findById(id);
        if (vaccine == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new VaccineDTO(vaccine), HttpStatus.OK);
    }

    @GetMapping(value = "/vaccines", produces = "application/json")
    public ResponseEntity<List<VaccineDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<VaccineDTO> vaccineDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Vaccine> vaccines = vaccineService.findAll(PageRequest.of(pageNum, pageSize));
            vaccineDTOs = new ArrayList<>();
            for (Vaccine vaccine: vaccines.getContent()) {
                vaccineDTOs.add(new VaccineDTO(vaccine));
            }
        } else {
            List<Vaccine> vaccines = vaccineService.findAll();
            vaccineDTOs = new ArrayList<>();
            for (Vaccine vaccine: vaccines) {
                vaccineDTOs.add(new VaccineDTO(vaccine));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(vaccineDTOs.size()));
        return new ResponseEntity<>(vaccineDTOs, headers, HttpStatus.OK);
    }
	
    @PostMapping(value = "/vaccines", consumes = "application/json")
    public ResponseEntity<VaccineDTO> add(@RequestBody VaccineDTO vaccineDTO, 
    		HttpServletRequest request) {

        Vaccine vaccine = new Vaccine(vaccineDTO);
        vaccine = vaccineService.add(vaccine);
        return new ResponseEntity<>(new VaccineDTO(vaccine), HttpStatus.CREATED);
    }

    @PutMapping(value = "/vaccines", consumes = "application/json")
    public ResponseEntity<VaccineDTO> update(@RequestBody VaccineDTO vaccineDTO,
    		HttpServletRequest request) {

        Vaccine vaccine = vaccineService.findById(vaccineDTO.getId());
        if (vaccine == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Vaccine vaccineUpdated = new Vaccine(vaccineDTO);
        vaccine = vaccineService.update(vaccineUpdated);
        return new ResponseEntity<>(new VaccineDTO(vaccine), HttpStatus.OK);
    }

    @DeleteMapping(value = "/vaccines/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Vaccine vaccine = vaccineService.findById(id);
        if (vaccine == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vaccineService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/vaccines")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        vaccineService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

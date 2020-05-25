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

import com.ftn.sbnz_2020.dto.VaccinationDTO;
import com.ftn.sbnz_2020.facts.Vaccination;
import com.ftn.sbnz_2020.service.VaccinationService;

@RestController
@RequestMapping("/api")
public class VaccinationController {
	
	@Autowired
	VaccinationService vaccinationService;
	
	@GetMapping(value = "/vaccinations/{id}", produces = "application/json")
    public ResponseEntity<VaccinationDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Vaccination vaccination = vaccinationService.findById(id);
        if (vaccination == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new VaccinationDTO(vaccination), HttpStatus.OK);
    }

    @GetMapping(value = "/vaccinations/name/{name}", produces = "application/json")
    public ResponseEntity<VaccinationDTO> findByName(@PathVariable String name, HttpServletRequest request) {

        Vaccination vaccination = vaccinationService.findByName(name);
        if (vaccination == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new VaccinationDTO(vaccination), HttpStatus.OK);
    }

    @GetMapping(value = "/vaccinations", produces = "application/json")
    public ResponseEntity<List<VaccinationDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<VaccinationDTO> vaccinationDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Vaccination> vaccinations = vaccinationService.findAll(PageRequest.of(pageNum, pageSize));
            vaccinationDTOs = new ArrayList<>();
            for (Vaccination vaccination: vaccinations.getContent()) {
                vaccinationDTOs.add(new VaccinationDTO(vaccination));
            }
        } else {
            List<Vaccination> vaccinations = vaccinationService.findAll();
            vaccinationDTOs = new ArrayList<>();
            for (Vaccination vaccination: vaccinations) {
                vaccinationDTOs.add(new VaccinationDTO(vaccination));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(vaccinationDTOs.size()));
        return new ResponseEntity<>(vaccinationDTOs, headers, HttpStatus.OK);
    }
	
    @PostMapping(value = "/vaccinations", consumes = "application/json")
    public ResponseEntity<VaccinationDTO> add(@RequestBody VaccinationDTO vaccinationDTO, 
    		HttpServletRequest request) {

        Vaccination vaccination = new Vaccination(vaccinationDTO);
        vaccination = vaccinationService.add(vaccination);
        return new ResponseEntity<>(new VaccinationDTO(vaccination), HttpStatus.CREATED);
    }

    @PutMapping(value = "/vaccinations", consumes = "application/json")
    public ResponseEntity<VaccinationDTO> update(@RequestBody VaccinationDTO vaccinationDTO,
    		HttpServletRequest request) {

        Vaccination vaccination = vaccinationService.findById(vaccinationDTO.getId());
        if (vaccination == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Vaccination vaccinationUpdated = new Vaccination(vaccinationDTO);
        vaccination = vaccinationService.update(vaccinationUpdated);
        return new ResponseEntity<>(new VaccinationDTO(vaccination), HttpStatus.OK);
    }

    @DeleteMapping(value = "/vaccinations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Vaccination vaccination = vaccinationService.findById(id);
        if (vaccination == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vaccinationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/vaccinations")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        vaccinationService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

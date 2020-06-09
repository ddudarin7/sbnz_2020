package com.ftn.sbnz_2020.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ftn.sbnz_2020.dto.PatientDTO;
import com.ftn.sbnz_2020.dto.ReportChronicDiseasesDTO;
import com.ftn.sbnz_2020.dto.ReportWeakenedImmuneSystemDTO;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    PatientService patientService;
    
    @GetMapping(value = "/patients/{id}", produces = "application/json")
    public ResponseEntity<PatientDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Patient patient = patientService.findById(id);
        if (patient == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }

    @GetMapping(value = "/patients/record-number/{recordNumber}", produces = "application/json")
    public ResponseEntity<PatientDTO> findByRecordNumber(@PathVariable String recordNumber, HttpServletRequest request) {
    	
        Patient patient = patientService.findByRecordNumber(recordNumber);
        if (patient == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }

    @GetMapping(value = "/patients", produces = "application/json")
    public ResponseEntity<List<PatientDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                    HttpServletRequest request) {
        logger.debug("Accessing GET /patients");

        List<PatientDTO> patientDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Patient> patients = patientService.findAll(PageRequest.of(pageNum, pageSize));
            patientDTOs = new ArrayList<>();
            for (Patient patient: patients.getContent()) {
                patientDTOs.add(new PatientDTO(patient));
            }
        } else {
            List<Patient> patients = patientService.findAll();
            patientDTOs = new ArrayList<>();
            for (Patient patient: patients) {
                patientDTOs.add(new PatientDTO(patient));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(patientService.findAll().size()));
        return new ResponseEntity<>(patientDTOs, headers, HttpStatus.OK);
    }


    @PostMapping(value = "/patients", consumes = "application/json")
    public ResponseEntity<PatientDTO> add(@RequestBody PatientDTO patientDTO, HttpServletRequest request) {

        Patient patient = new Patient(patientDTO);
        patient = patientService.save(patient);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.CREATED);
    }

    @PutMapping(value = "/patients", consumes = "application/json")
    public ResponseEntity<PatientDTO> edit(@RequestBody PatientDTO patientDTO, HttpServletRequest request) {

        Patient patient = patientService.findById(patientDTO.getId());
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Patient patientUpdate = new Patient(patientDTO);
        patient = patientService.update(patientUpdate);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/patients/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Patient patient = patientService.findById(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/patients")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        logger.debug("Accessing DELETE /patients");

        patientService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(value = "/patients/report/chronic-diseases", produces = "application/json")
    public ResponseEntity<List<ReportChronicDiseasesDTO>> findAll(HttpServletRequest request) {
        logger.debug("Accessing GET /patients");

        KieSession kieSession = (KieSession)request.getSession().getAttribute("kieSession");
        
        List<ReportChronicDiseasesDTO> result=patientService.chronicDiseaseReport(kieSession);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(patientService.findAll().size()));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/patients/report/weak-immunity", produces = "application/json")
    public ResponseEntity<List<ReportWeakenedImmuneSystemDTO>> findAllWithWeakenedImmuneSystem(HttpServletRequest request) {
        logger.debug("Accessing GET /patients");

        KieSession kieSession = (KieSession)request.getSession().getAttribute("kieSession");
        
        List<ReportWeakenedImmuneSystemDTO> result=patientService.weakenedImmuneSystemReport(kieSession);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(patientService.findAll().size()));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

}

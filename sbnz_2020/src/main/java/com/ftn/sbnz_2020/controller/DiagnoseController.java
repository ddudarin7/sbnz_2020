package com.ftn.sbnz_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz_2020.dto.DiagnoseDTO;
import com.ftn.sbnz_2020.dto.SymptomDTO;
import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;
import com.ftn.sbnz_2020.facts.Vet;
import com.ftn.sbnz_2020.service.DiagnoseService;
import com.ftn.sbnz_2020.service.DiseaseService;
import com.ftn.sbnz_2020.service.IngredientService;
import com.ftn.sbnz_2020.service.MedicineService;
import com.ftn.sbnz_2020.service.PatientService;
import com.ftn.sbnz_2020.service.SymptomService;
import com.ftn.sbnz_2020.service.TherapyService;
import com.ftn.sbnz_2020.service.VetService;

@RestController
@RequestMapping("/api")
public class DiagnoseController {

	@Autowired
	DiagnoseService diagnoseService;
	
	@Autowired
	SymptomService symptomService;
	
	@Autowired
	MedicineService medicineService;
	
	@Autowired
	IngredientService ingredientService;
	
	@Autowired
	VetService vetService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	TherapyService therapyService;
	
	@GetMapping(value = "/diagnoses/{id}", produces = "application/json")
    public ResponseEntity<DiagnoseDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Diagnose diagnose = diagnoseService.findById(id);
        if (diagnose == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new DiagnoseDTO(diagnose), HttpStatus.OK);
    }


    @GetMapping(value = "/diagnoses", produces = "application/json")
    public ResponseEntity<List<DiagnoseDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<DiagnoseDTO> diagnoseDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Diagnose> diagnoses = diagnoseService.findAll(PageRequest.of(pageNum, pageSize));
            diagnoseDTOs = new ArrayList<>();
            for (Diagnose diagnose: diagnoses.getContent()) {
                diagnoseDTOs.add(new DiagnoseDTO(diagnose));
            }
        } else {
            List<Diagnose> diagnoses = diagnoseService.findAll();
            diagnoseDTOs = new ArrayList<>();
            for (Diagnose diagnose: diagnoses) {
                diagnoseDTOs.add(new DiagnoseDTO(diagnose));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(diagnoseDTOs.size()));
        return new ResponseEntity<>(diagnoseDTOs, headers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/diagnoses/disease/{diseaseId}", produces = "application/json")
    public ResponseEntity<List<DiagnoseDTO>> findAllByDiagnoseCategory(
    		@PathVariable Long diseaseId, @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {
    	/*
    	 * Needs test
    	 */
        List<DiagnoseDTO> diagnoseDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Diagnose> diagnoses = diagnoseService.findAllByDiseaseId(diseaseId, 
            		PageRequest.of(pageNum, pageSize));
            diagnoseDTOs = new ArrayList<>();
            for (Diagnose diagnose: diagnoses.getContent()) {
                diagnoseDTOs.add(new DiagnoseDTO(diagnose));
            }
        } else {
            List<Diagnose> diagnoses = diagnoseService.findAllByDiseaseId(diseaseId);
            diagnoseDTOs = new ArrayList<>();
            for (Diagnose diagnose: diagnoses) {
                diagnoseDTOs.add(new DiagnoseDTO(diagnose));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(diagnoseDTOs.size()));
        return new ResponseEntity<>(diagnoseDTOs, headers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/diagnoses/patient/{patientId}", produces = "application/json")
    public ResponseEntity<List<DiagnoseDTO>> findByPatientId(
    		@PathVariable Long patientId, HttpServletRequest request) {

        List<DiagnoseDTO> diagnoseDTOs = new ArrayList<>();
        List<Diagnose> diagnoses = diagnoseService.findByPatientId(patientId);
        for (Diagnose diagnose: diagnoses) {
            diagnoseDTOs.add(new DiagnoseDTO(diagnose));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(diagnoseDTOs.size()));
        return new ResponseEntity<>(diagnoseDTOs, headers, HttpStatus.OK);
    }

    @PutMapping(value = "/diagnoses", consumes = "application/json")
    public ResponseEntity<DiagnoseDTO> update(@RequestBody DiagnoseDTO diagnoseDTO,
    		HttpServletRequest request) {

        Diagnose diagnose = diagnoseService.findById(diagnoseDTO.getId());
        if (diagnose == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Diagnose diagnoseUpdated = new Diagnose(diagnoseDTO);
        diagnose = diagnoseService.update(diagnoseUpdated);
        return new ResponseEntity<>(new DiagnoseDTO(diagnose), HttpStatus.OK);
    }

    @DeleteMapping(value = "/diagnoses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Diagnose diagnose = diagnoseService.findById(id);
        if (diagnose == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        diagnoseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/diagnoses")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        diagnoseService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @PostMapping(value="/diagnose/{patientRecordNumber}")
    public ResponseEntity<DiagnoseDTO> diagnose(@RequestBody List<SymptomDTO> symptomDTOs,
    		@PathVariable String patientRecordNumber, HttpServletRequest request){
        
        KieSession kieSession = (KieSession)request.getSession().getAttribute("kieSession");
        
        // collecting symptoms
        
        List<Symptom> symptoms = new ArrayList<Symptom>();
        for (SymptomDTO s : symptomDTOs)
        	symptoms.add(symptomService.findByName(s.getName()));;
        
        // collecting patient
            
    	Patient patient = patientService.findByRecordNumber(patientRecordNumber);
    	if (patient == null)
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    		
    	// diagnose
    	
    	Diagnose diagnose = diagnoseService.diagnose(kieSession, symptoms, patient);
    	return new ResponseEntity<DiagnoseDTO>(new DiagnoseDTO(diagnose), HttpStatus.OK);
    }
    
    @PostMapping(value = "/diagnoses", consumes = "application/json")
    public ResponseEntity<DiagnoseDTO> add(@RequestBody DiagnoseDTO diagnoseDTO, 
    		HttpServletRequest request, @RequestParam String vetUsername) {

    	// collecting vet
    	Vet vet = vetService.findByUsername(vetUsername);
    	if (vet == null)
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	
        Diagnose diagnose = diagnoseService.confirmDiagnose(new Diagnose(diagnoseDTO), vet);
        return new ResponseEntity<>(new DiagnoseDTO(diagnose), HttpStatus.CREATED);
    }
   
}

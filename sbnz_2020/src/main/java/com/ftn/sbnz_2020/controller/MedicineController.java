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

import com.ftn.sbnz_2020.dto.MedicineDTO;
import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.service.MedicineService;

@RestController
public class MedicineController {

	@Autowired
	MedicineService medicineService;
	
    @GetMapping(value = "/medicines/{id}", produces = "application/json")
    public ResponseEntity<MedicineDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Medicine medicine = medicineService.findById(id);
        if (medicine == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new MedicineDTO(medicine), HttpStatus.OK);
    }

    @GetMapping(value = "/medicines/name/{name}", produces = "application/json")
    public ResponseEntity<MedicineDTO> findByName(@PathVariable String name, HttpServletRequest request) {

        Medicine medicine = medicineService.findByName(name);
        if (medicine == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new MedicineDTO(medicine), HttpStatus.OK);
    }

    @GetMapping(value = "/medicines", produces = "application/json")
    public ResponseEntity<List<MedicineDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                     HttpServletRequest request) {

        List<MedicineDTO> medicineDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Medicine> medicines = medicineService.findAll(PageRequest.of(pageNum, pageSize));
            medicineDTOs = new ArrayList<>();
            for (Medicine medicine: medicines.getContent()) {
                medicineDTOs.add(new MedicineDTO(medicine));
            }
        } else {
            List<Medicine> medicines = medicineService.findAll();
            medicineDTOs = new ArrayList<>();
            for (Medicine medicine: medicines) {
                medicineDTOs.add(new MedicineDTO(medicine));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(medicineDTOs.size()));
        return new ResponseEntity<>(medicineDTOs, headers, HttpStatus.OK);
    }
	
    @PostMapping(value = "/medicines", consumes = "application/json")
    public ResponseEntity<MedicineDTO> add(@RequestBody MedicineDTO medicineDTO, 
    		HttpServletRequest request) {

        Medicine medicine = new Medicine(medicineDTO);
        medicine = medicineService.add(medicine);
        return new ResponseEntity<>(new MedicineDTO(medicine), HttpStatus.CREATED);
    }

    @PutMapping(value = "/medicines", consumes = "application/json")
    public ResponseEntity<MedicineDTO> edit(@RequestBody MedicineDTO medicineDTO,
    		HttpServletRequest request) {

        Medicine medicine = medicineService.findById(medicineDTO.getId());
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Medicine medicineUpdate = new Medicine(medicineDTO);
        medicine = medicineService.update(medicineUpdate);
        return new ResponseEntity<>(new MedicineDTO(medicine), HttpStatus.OK);
    }

    @DeleteMapping(value = "/medicines/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        Medicine medicine = medicineService.findById(id);
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        medicineService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/medicines")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {

        medicineService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}

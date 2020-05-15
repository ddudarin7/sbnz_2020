package com.ftn.sbnz_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.ftn.sbnz_2020.dto.VetDTO;
import com.ftn.sbnz_2020.facts.Vet;
import com.ftn.sbnz_2020.service.VetService;

@RestController
@RequestMapping("/api")
public class VetController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VetService vetService;

    @GetMapping(value = "/vets/{id}", produces = "application/json")
    public ResponseEntity<VetDTO> findById(@PathVariable Long id, HttpServletRequest request) {
        logger.debug("Accessing GET /vets/{id}");

        Vet vet = vetService.findById(id);
        if (vet == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new VetDTO(vet), HttpStatus.OK);
    }

    @GetMapping(value = "/vets/username/{username}", produces = "application/json")
    public ResponseEntity<VetDTO> findByUsername(@PathVariable String username, HttpServletRequest request) {
        logger.debug("Accessing GET /vets/username/{username}");

        Vet vet = vetService.findByUsername(username);
        if (vet == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new VetDTO(vet), HttpStatus.OK);
    }

    @GetMapping(value = "/vets", produces = "application/json")
    public ResponseEntity<List<VetDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                  @RequestParam(required = false) Integer pageSize,
                                                   HttpServletRequest request) {
        logger.debug("Accessing GET /vets");

        List<VetDTO> vetDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Vet> vets = vetService.findAll(PageRequest.of(pageNum, pageSize));
            vetDTOs = new ArrayList<>();
            for (Vet vet: vets.getContent()) {
                vetDTOs.add(new VetDTO(vet));
            }
        } else {
            List<Vet> vets = vetService.findAll();
            vetDTOs = new ArrayList<>();
            for (Vet vet: vets) {
                vetDTOs.add(new VetDTO(vet));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(vetService.findAll().size()));
        return new ResponseEntity<>(vetDTOs, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/vets", consumes = "application/json")
    public ResponseEntity<VetDTO> add(@RequestBody VetDTO vetDTO, HttpServletRequest request) {
        logger.debug("Accessing POST /vets");

        Vet vet = new Vet(vetDTO);
        vet = vetService.save(vet);
        return new ResponseEntity<>(new VetDTO(vet), HttpStatus.CREATED);
    }

    @PutMapping(value = "/vets", consumes = "application/json")
    public ResponseEntity<VetDTO> edit(@RequestBody VetDTO vetDTO, HttpServletRequest request) {
        logger.debug("Accessing PUT /vets");

        Vet vet = vetService.findById(vetDTO.getId());
        if (vet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Vet vetUpdate = new Vet(vetDTO);
        vet = vetService.update(vetUpdate);
        return new ResponseEntity<>(new VetDTO(vet), HttpStatus.OK);
    }

    @DeleteMapping(value = "/vets/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        logger.debug("Accessing DELETE /vets/{id}");

        Vet vet = vetService.findById(id);
        if (vet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vetService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/vets")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        logger.debug("Accessing DELETE /vets");

        vetService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

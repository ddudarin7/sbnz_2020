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

import com.ftn.sbnz_2020.dto.OwnerDTO;
import com.ftn.sbnz_2020.facts.Owner;
import com.ftn.sbnz_2020.service.OwnerService;

@RestController
@RequestMapping("/api")
public class OwnerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OwnerService ownerService;

    @GetMapping(value = "/owners/{id}", produces = "application/json")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id, HttpServletRequest request) {
        logger.debug("Accessing GET /owners/{id}");

        Owner owner = ownerService.findById(id);
        if (owner == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new OwnerDTO(owner), HttpStatus.OK);
    }

    @GetMapping(value = "/owners", produces = "application/json")
    public ResponseEntity<List<OwnerDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                  @RequestParam(required = false) Integer pageSize,
                                                  HttpServletRequest request) {
        logger.debug("Accessing GET /owners");

        List<OwnerDTO> ownerDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Owner> owners = ownerService.findAll(PageRequest.of(pageNum, pageSize));
            ownerDTOs = new ArrayList<>();
            for (Owner owner: owners.getContent()) {
                ownerDTOs.add(new OwnerDTO(owner));
            }
        } else {
            List<Owner> owners = ownerService.findAll();
            ownerDTOs = new ArrayList<>();
            for (Owner owner: owners) {
                ownerDTOs.add(new OwnerDTO(owner));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(ownerService.findAll().size()));
        return new ResponseEntity<>(ownerDTOs, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/owners", consumes = "application/json")
    public ResponseEntity<OwnerDTO> add(@RequestBody OwnerDTO ownerDTO, HttpServletRequest request) {
        logger.debug("Accessing POST /owners");

        Owner owner = new Owner(ownerDTO);
        owner = ownerService.save(owner);
        return new ResponseEntity<>(new OwnerDTO(owner), HttpStatus.CREATED);
    }

    @PutMapping(value = "/owners", consumes = "application/json")
    public ResponseEntity<OwnerDTO> edit(@RequestBody OwnerDTO ownerDTO, HttpServletRequest request) {
        logger.debug("Accessing PUT /owners");

        Owner owner = ownerService.findById(ownerDTO.getId());
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Owner ownerUpdate = new Owner(ownerDTO);
        owner = ownerService.update(ownerUpdate);
        return new ResponseEntity<>(new OwnerDTO(owner), HttpStatus.OK);
    }

    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        logger.debug("Accessing DELETE /owners/{id}");

        Owner owner = ownerService.findById(id);
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ownerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/owners")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        logger.debug("Accessing DELETE /owners");

        ownerService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

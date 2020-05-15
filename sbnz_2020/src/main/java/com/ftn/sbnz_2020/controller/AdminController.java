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

import com.ftn.sbnz_2020.dto.AdminDTO;
import com.ftn.sbnz_2020.facts.Admin;
import com.ftn.sbnz_2020.service.AdminService;

@RestController
@RequestMapping("/api")
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/admins/{id}", produces = "application/json")
    public ResponseEntity<AdminDTO> findById(@PathVariable Long id, HttpServletRequest request) {

        Admin admin = adminService.findById(id);
        if (admin == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.OK);
    }

    @GetMapping(value = "/admins/username/{username}", produces = "application/json")
    public ResponseEntity<AdminDTO> findByUsername(@PathVariable String username, HttpServletRequest request) {
        logger.debug("Accessing GET /admins/username/{username}");

        Admin admin = adminService.findByUsername(username);
        if (admin == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.OK);
    }

    @GetMapping(value = "/admins", produces = "application/json")
    public ResponseEntity<List<AdminDTO>> findAll(@RequestParam(required = false) Integer pageNum,
                                                  @RequestParam(required = false) Integer pageSize,
                                                  HttpServletRequest request) {
        logger.debug("Accessing GET /admins");

        List<AdminDTO> adminDTOs;
        if (pageNum != null && pageSize != null) {
            Page<Admin> admins = adminService.findAll(PageRequest.of(pageNum, pageSize));
            adminDTOs = new ArrayList<>();
            for (Admin admin: admins.getContent()) {
                adminDTOs.add(new AdminDTO(admin));
            }
        } else {
            List<Admin> admins = adminService.findAll();
            adminDTOs = new ArrayList<>();
            for (Admin admin: admins) {
                adminDTOs.add(new AdminDTO(admin));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(adminService.findAll().size()));
        return new ResponseEntity<>(adminDTOs, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/admins", consumes = "application/json")
    public ResponseEntity<AdminDTO> add(@RequestBody AdminDTO adminDTO, HttpServletRequest request) {
        logger.debug("Accessing POST /admins");

        Admin admin = new Admin(adminDTO);
        admin = adminService.save(admin);
        return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.CREATED);
    }

    @PutMapping(value = "/admins", consumes = "application/json")
    public ResponseEntity<AdminDTO> edit(@RequestBody AdminDTO adminDTO, HttpServletRequest request) {
        logger.debug("Accessing PUT /admins");

        Admin admin = adminService.findById(adminDTO.getId());
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Admin adminUpdate = new Admin(adminDTO);
        admin = adminService.update(adminUpdate);
        return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admins/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        logger.debug("Accessing DELETE /admins/{id}");

        Admin admin = adminService.findById(id);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adminService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/admins")
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        logger.debug("Accessing DELETE /admins");

        adminService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

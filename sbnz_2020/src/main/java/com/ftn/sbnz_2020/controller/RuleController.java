package com.ftn.sbnz_2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz_2020.dto.RuleDTO;
import com.ftn.sbnz_2020.dto.VetDTO;
import com.ftn.sbnz_2020.facts.Vet;
import com.ftn.sbnz_2020.service.RuleService;

@RestController
@RequestMapping("/api")
public class RuleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RuleService ruleService;
    
    @GetMapping(value = "/rules", produces = "application/json")
    public ResponseEntity<RuleDTO> getRules(HttpServletRequest request) {
        logger.debug("Accessing GET /rules");

        RuleDTO rule = ruleService.getRules();
        if (rule == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(rule, HttpStatus.OK);
    }
    
    @PostMapping(value = "/rules", consumes = "application/json")
    public ResponseEntity<RuleDTO> add(@RequestBody RuleDTO ruleDTO, HttpServletRequest request) {
        logger.debug("Accessing POST /rules");
        KieSession kieSession = (KieSession)request.getSession().getAttribute("kieSession");


        RuleDTO rule = ruleService.save(kieSession, ruleDTO);
        return new ResponseEntity<>(rule, HttpStatus.CREATED);
    }
}

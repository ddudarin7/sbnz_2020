package com.ftn.sbnz_2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz_2020.dto.CurrentUserDTO;
import com.ftn.sbnz_2020.dto.LogInDTO;
import com.ftn.sbnz_2020.event.DiagnoseEvent;
import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Role;
import com.ftn.sbnz_2020.facts.User;
import com.ftn.sbnz_2020.service.DiagnoseService;
import com.ftn.sbnz_2020.service.UserService;

@RestController
@RequestMapping("/api")
public class LogInController {

    @Autowired
    private KieContainer kieContainer;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DiagnoseService diagnoseService;
    
    @PostMapping(value = "/log-in",produces = "application/json")
    public  ResponseEntity<CurrentUserDTO> logIn(@RequestBody LogInDTO logInDto, HttpServletRequest request){
    	User u=userService.findOne(logInDto.getUsername());
    	System.out.println("log-in");
    	if(u!= null) {
    		if(!u.getPassword().equals(logInDto.getPassword())) {
    			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    		}
    		if(u.getRole().equals(Role.VET)) {
            	KieServices ks = KieServices.Factory.get();
                KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
                kbconf.setOption(EventProcessingOption.STREAM);
                KieBase kbase = kieContainer.newKieBase(kbconf);
                KieSession kieSession = kbase.newKieSession();
                request.getSession().setAttribute("kieSession", kieSession);
                System.out.println("Sesija kreirana");
                
                KieSession eventSession = kbase.newKieSession();
                
                for (Diagnose d : diagnoseService.findAll())
                	eventSession.insert(new DiagnoseEvent(d.getDisease(), d.getDate()));
                
                request.getSession().setAttribute("eventSession", eventSession);
    		}
        	return new ResponseEntity<>(new CurrentUserDTO(u.getUsername(), u.getPassword(), u.getRole().toString()),HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
    }
    
    @PostMapping(value = "/log-out",produces = "application/json")
    public  ResponseEntity<LogInDTO> logOut(@RequestBody String username, HttpServletRequest request){
    	try {
    		System.out.println(username);
    		User u=userService.findOne(username);
    		if(u.getRole().equals(Role.VET)) {
    			KieSession kieSession = (KieSession)request.getSession().getAttribute("kieSession");
    			kieSession.dispose();
    			kieSession.destroy();
    		}
            request.getSession().invalidate();
            System.out.println("Sesija unistena");
        	return new ResponseEntity<>(HttpStatus.OK);
    	}catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
	
}

package com.ftn.sbnz_2020.drools.general;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz_2020.facts.Diagnose;

class ZeroOrMoreDiseases {

	private KieSession kSession;
	
	private Diagnose diagnose;
	
	@BeforeEach
	void setUpTest() {
		this.kSession = com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		this.diagnose=new Diagnose();
	}
	
	@Test
	void zeroDiseases() {
		kSession.insert(diagnose);
		kSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kSession.fireAllRules();
		
		assertTrue(diagnose.getDisease()==null);
	}
	
	@Test
	void moreDiseases() {
		kSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kSession.fireAllRules();
	}
	
	@AfterEach
	void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);
	}

}

package com.ftn.sbnz_2020.drools.general;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ftn.sbnz_2020.facts.Diagnose;

@RunWith(SpringJUnit4ClassRunner.class)
public class ZeroOrMoreDiseases {

	private KieSession kSession;
	
	private Diagnose diagnose;
	
	@Before
	public void setUpTest() {
		this.kSession = com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		this.diagnose=new Diagnose();
	}
	
	@Test
	public void zeroDiseases() {
		kSession.insert(diagnose);
		kSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kSession.fireAllRules();
		
		assertTrue(diagnose.getDisease().getId()==null);
	}
	
	@Test
	public void moreDiseases() {
		kSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kSession.fireAllRules();
	}
	
	@After
	public void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);
	}

}

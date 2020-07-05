package com.ftn.sbnz_2020.drools.general;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.drools.core.ClassObjectFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;

@RunWith(SpringJUnit4ClassRunner.class)
public class ZeroOrMoreDiseases {

	private KieSession kSession;
	
	private Diagnose diagnose;
	
	@Before
	public void setUpTest() {
		this.kSession = com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		this.diagnose=new Diagnose();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void zeroDiseases() {
		kSession.insert(diagnose);
		kSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kSession.fireAllRules();
		
		assertTrue(diagnose.getDisease().getId()==null);
		Collection<Boolean> flags=(Collection<Boolean>) kSession.getObjects(new ClassObjectFilter(Boolean.class));
		assertTrue(flags.contains(true));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void moreDiseases() {
		Disease d1=new Disease(1L, "d1", DiseaseCategory.BACTERIAL, new ArrayList<Symptom>(), new ArrayList<Symptom>(), new ArrayList<Therapy>());
		Disease d2=new Disease(2L, "d2", DiseaseCategory.BACTERIAL, new ArrayList<Symptom>(), new ArrayList<Symptom>(), new ArrayList<Therapy>());
		
		kSession.insert(d1);
		kSession.insert(d2);
		kSession.insert(diagnose);
		
		kSession.getAgenda().getAgendaGroup("diagnose failed").setFocus();
		kSession.fireAllRules();
		
		Collection<Boolean> flags=(Collection<Boolean>) kSession.getObjects(new ClassObjectFilter(Boolean.class));
		Collection<Disease> diseasesLeft=(Collection<Disease>) kSession.getObjects(new ClassObjectFilter(Disease.class));
		assertTrue(flags.contains(true));
		assertTrue(diseasesLeft.size()==2);
	}
	
	@After
	public void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);
	}

}

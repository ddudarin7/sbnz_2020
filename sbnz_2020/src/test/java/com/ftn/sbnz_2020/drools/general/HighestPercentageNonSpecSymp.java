package com.ftn.sbnz_2020.drools.general;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
public class HighestPercentageNonSpecSymp {
	
	private KieSession kSession;

	private Disease testDisease1;
	private Disease testDisease2;
	private Disease testDisease3;

	private Symptom s1;
	private Symptom s2;

	@Before
	public void setUpTest() {
		this.kSession = com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();

		// define data
		this.s1 = new Symptom(0L, "s0");
		this.s2 = new Symptom(1L, "s1");
		Symptom s3 = new Symptom(2L, "s2");
		Symptom s4 = new Symptom(3L, "s3");
		Symptom s5 = new Symptom(4L, "s4");

		ArrayList<Symptom> sList1 = new ArrayList<>();
		sList1.add(this.s1);

		ArrayList<Symptom> sList2 = new ArrayList<>();
		sList2.add(this.s1);
		sList2.add(this.s2);

		ArrayList<Symptom> sList3 = new ArrayList<>();
		sList3.add(s2);
		sList3.add(s3);
		sList3.add(s4);
		sList3.add(s5);
		
		ArrayList<Symptom> sList4=new ArrayList<>();
		sList4.add(s5);

		this.testDisease1 = new Disease(0L, "d0", DiseaseCategory.BACTERIAL, new ArrayList<Symptom>(), sList1, new ArrayList<Therapy>());
		this.testDisease2 = new Disease(1L, "d1", DiseaseCategory.BACTERIAL, new ArrayList<Symptom>(), sList2, new ArrayList<Therapy>());
		this.testDisease3 = new Disease(2L, "d2", DiseaseCategory.BACTERIAL, new ArrayList<Symptom>(), sList3, new ArrayList<Therapy>());

		kSession.insert(this.testDisease1);
		kSession.insert(this.testDisease2);
		kSession.insert(this.testDisease3);
	}
	
	@Test
	public void higestPercentageMatchNonSpec() {
		Diagnose makingDiagnose = new Diagnose();
		kSession.insert(makingDiagnose);
		
		kSession.insert(s2);
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertTrue(makingDiagnose.getSpecificSymptomsMatchedNum()==0L);
		assertTrue(makingDiagnose.getNonSpecificSymptomsMatchedNum()==1L);
		assertTrue(makingDiagnose.getDisease().equals(testDisease2)); //disease2 contains 50% of matched non specific symptoms
	}

	@After
	public void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);
	}
	
}

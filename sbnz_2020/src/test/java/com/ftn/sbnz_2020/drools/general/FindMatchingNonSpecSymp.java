package com.ftn.sbnz_2020.drools.general;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;

@RunWith(SpringJUnit4ClassRunner.class)
public class FindMatchingNonSpecSymp {

private KieSession kSession;
	
	private Disease testDisease1;
	private Disease testDisease2;
	private Disease testDisease3;
	
	private Symptom s1;
	private Symptom s2;
	
	@Before
	public void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		
		//define data
		this.s1=new Symptom(0L,"s0");
		this.s2=new Symptom(1L,"s1");		
		Symptom s3=new Symptom(2L,"s2");
		Symptom s4=new Symptom(3L,"s3");
		Symptom s5=new Symptom(4L,"s4");
		
		ArrayList<Symptom> sList1=new ArrayList<>();
		sList1.add(this.s1);
		
		ArrayList<Symptom> sList2=new ArrayList<>();
		sList2.add(this.s1);
		sList2.add(this.s2);
		
		ArrayList<Symptom> sList3=new ArrayList<>();
		sList3.add(s3);
		sList3.add(s4);
		sList3.add(s5);
		
		
		ArrayList<Symptom> sList4=new ArrayList<>();
		sList4.add(s4);
		
		this.testDisease1=new Disease(0L,"d0",DiseaseCategory.BACTERIAL,sList4,sList1,new ArrayList<Therapy>());
		this.testDisease2=new Disease(1L,"d1",DiseaseCategory.BACTERIAL,sList3,sList2,new ArrayList<Therapy>());
		this.testDisease3=new Disease(2L,"d2",DiseaseCategory.BACTERIAL,sList3,new ArrayList<Symptom>(),new ArrayList<Therapy>());
		
		kSession.insert(this.testDisease1);
		kSession.insert(this.testDisease2);
		kSession.insert(this.testDisease3);
	}
	
	@Test
	public void zeroSymptomsMatched() {
		Symptom testSymptom=new Symptom(5L,"s5");
		
		kSession.insert(testSymptom);
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		assertTrue(this.testDisease1.getNonSpecificSymptomsMatched().size()==0);
		assertTrue(this.testDisease2.getNonSpecificSymptomsMatched().size()==0);
		assertTrue(this.testDisease3.getNonSpecificSymptomsMatched().size()==0);
		
		assertTrue(this.testDisease1.getSpecificSymptomsMatched().size()==0);
		assertTrue(this.testDisease2.getSpecificSymptomsMatched().size()==0);
		assertTrue(this.testDisease3.getSpecificSymptomsMatched().size()==0);
		
		assertTrue(this.testDisease1.getSpecificSymptomsMatchedNum()==0);
		assertTrue(this.testDisease2.getSpecificSymptomsMatchedNum()==0);
		assertTrue(this.testDisease3.getSpecificSymptomsMatchedNum()==0);
	
		assertTrue(this.testDisease1.getNonSpecificSymptomsMatchedNum()==0);
		assertTrue(this.testDisease2.getNonSpecificSymptomsMatchedNum()==0);
		assertTrue(this.testDisease3.getNonSpecificSymptomsMatchedNum()==0);
	}
	
	@Test
	public void nonSpecSymptomsMatched() {
		kSession.insert(this.s1);
		kSession.insert(this.s2);
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		assertTrue(this.testDisease1.getSpecificSymptomsMatchedNum()==0);
		assertTrue(this.testDisease2.getSpecificSymptomsMatchedNum()==0);
		assertTrue(this.testDisease3.getSpecificSymptomsMatchedNum()==0);
		
		assertTrue(this.testDisease1.getSpecificSymptomsMatched().size()==0);
		assertTrue(this.testDisease2.getSpecificSymptomsMatched().size()==0);
		assertTrue(this.testDisease3.getSpecificSymptomsMatched().size()==0);
		
		assertTrue(this.testDisease1.getNonSpecificSymptomsMatched().size()==1);
		assertTrue(this.testDisease2.getNonSpecificSymptomsMatched().size()==2);
		assertTrue(this.testDisease3.getNonSpecificSymptomsMatched().size()==0);
		
		assertTrue(this.testDisease1.getNonSpecificSymptomsMatchedNum()==1);
		assertTrue(this.testDisease2.getNonSpecificSymptomsMatchedNum()==2);
		assertTrue(this.testDisease3.getNonSpecificSymptomsMatchedNum()==0);
		
		assertTrue(this.testDisease1.getNonSpecificSymptomsMatched().contains(this.s1));
		
		assertTrue(this.testDisease2.getNonSpecificSymptomsMatched().contains(this.s1));
		assertTrue(this.testDisease2.getNonSpecificSymptomsMatched().contains(this.s2));
	}
	
	@After
	public void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);	
	}


}

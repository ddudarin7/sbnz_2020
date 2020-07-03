package com.ftn.sbnz_2020.drools.reports;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ftn.sbnz_2020.dto.ReportWeakenedImmuneSystemDTO;
import com.ftn.sbnz_2020.facts.Breed;
import com.ftn.sbnz_2020.facts.Diagnose;
import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.facts.Ingredient;
import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.facts.Owner;
import com.ftn.sbnz_2020.facts.Patient;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;
import com.ftn.sbnz_2020.facts.Vaccination;
import com.ftn.sbnz_2020.facts.Vet;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeakenedImmunity {
	
	private KieSession kSession;
	
	private Disease testDisease1;
	
	private Patient testPatient1;
	
	@Before
	public void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		
		this.testDisease1=new Disease(0L,"d0",DiseaseCategory.BACTERIAL,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		
		this.testPatient1=new Patient(0L, "p0", "REC0", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		
		Diagnose d1=new Diagnose(1L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d2=new Diagnose(2L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d3=new Diagnose(3L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d4=new Diagnose(4L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d5=new Diagnose(5L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d6=new Diagnose(6L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d7=new Diagnose(7L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d8=new Diagnose(8L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d9=new Diagnose(9L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		
		kSession.insert(d1);
		kSession.insert(d2);
		kSession.insert(d3);
		kSession.insert(d4);
		kSession.insert(d5);
		kSession.insert(d6);
		kSession.insert(d7);
		kSession.insert(d8);
		kSession.insert(d9);
		

	}

	@Test
	public void noEnoughDiagnoses() {
		ArrayList<ReportWeakenedImmuneSystemDTO> result=new ArrayList<>();
		kSession.insert(result);
		
		kSession.getAgenda().getAgendaGroup("weakened immunity").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==0);
	}
	
	@Test
	public void badDate(){
		Calendar cal=Calendar.getInstance();
		cal.set(2010, 1, 1);
		Diagnose d10=new Diagnose(14L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), cal.getTime());
		kSession.insert(d10);
		
		ArrayList<ReportWeakenedImmuneSystemDTO> result=new ArrayList<>();
		kSession.insert(result);
		
		kSession.getAgenda().getAgendaGroup("weakened immunity").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==0);
	}
	
	@Test
	public void oneDisease() {
		Diagnose d10=new Diagnose(1L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		kSession.insert(d10);
		
		ArrayList<ReportWeakenedImmuneSystemDTO> result=new ArrayList<>();
		kSession.insert(result);
		
		kSession.getAgenda().getAgendaGroup("weakened immunity").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==0);	
	}
	
	@Test
	public void goodResults() {
		Disease disease2=new Disease(1L,"d1",DiseaseCategory.BACTERIAL,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Diagnose d10=new Diagnose(1L, disease2, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		kSession.insert(d10);
		
		ArrayList<ReportWeakenedImmuneSystemDTO> result=new ArrayList<>();
		kSession.insert(result);
		
		kSession.getAgenda().getAgendaGroup("weakened immunity").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==2);	
		assertTrue(result.get(0).getP().equals(testPatient1));
		assertTrue(result.get(1).getP().equals(testPatient1));
		assertTrue(result.get(0).getD().equals(testDisease1)||result.get(0).getD().equals(disease2));
		assertTrue(result.get(1).getD().equals(testDisease1)||result.get(1).getD().equals(disease2));
	}

	@After
	public void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);	
	}
	
}

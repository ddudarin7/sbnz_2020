package com.ftn.sbnz_2020.drools.specificRules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

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

class BreedsToothPathology {

	private KieSession kSession;
	
	private Patient testPatient1;
	private Patient testPatient2;
	private Patient testPatient3;
	
	private Disease testDisease1;
	private Disease testDisease2;
	
	private Diagnose testDiagnose;
	
	@BeforeEach
	void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		
		Symptom s1=new Symptom(0L,"s0");
		Symptom s2=new Symptom(1L,"s1");
		Symptom s3=new Symptom(2L,"s2");
		
		ArrayList<Symptom> sList1=new ArrayList<>();
		sList1.add(s1);
		sList1.add(s2);
		
		ArrayList<Symptom> sList2=new ArrayList<>();
		sList2.add(s3);
		
		this.testPatient1=new Patient(0L, "p0", "REC0", new Date(), Breed.BOXER, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		this.testPatient2=new Patient(1L, "p1", "REC1", new Date(), Breed.BULLDOG, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		this.testPatient3=new Patient(2L, "p2", "REC2", new Date(), Breed.PITBULL, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		
		this.testDisease1=new Disease(0L,"Tooth pathology",DiseaseCategory.OTHER,sList1,sList2,new ArrayList<Therapy>());
		this.testDisease2=new Disease(1L,"d1",DiseaseCategory.OTHER,sList1,sList2,new ArrayList<Therapy>());
		
		kSession.insert(s1);
		kSession.insert(s2);
		
		kSession.insert(testDisease1);
		kSession.insert(testDisease2);
	}
	
	@Test
	void boxerPositiveTest() {
		testDiagnose = new Diagnose();
		testDiagnose.setPatient(testPatient2);
		kSession.insert(testDiagnose);	
		
		kSession.insert(testPatient2);
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertTrue(testDiagnose.getDisease().equals(testDisease1));
	}
	
	@Test
	void bulldogPositiveTest() {
		testDiagnose = new Diagnose();
		testDiagnose.setPatient(testPatient1);
		kSession.insert(testDiagnose);	
		
		kSession.insert(testPatient1);
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertTrue(testDiagnose.getDisease().equals(testDisease1));
	}
	
	@Test
	void otherBreedsNegativeTest() {
		testDiagnose = new Diagnose();
		testDiagnose.setPatient(testPatient3);
		kSession.insert(testDiagnose);	
		
		kSession.insert(testPatient3);
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertFalse(testDiagnose.getDisease().equals(testDisease1));
		assertFalse(testDiagnose.getDisease().equals(testDisease2));
	}

	@AfterEach
	void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);	
	}
	
}

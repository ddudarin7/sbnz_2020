package com.ftn.sbnz_2020.drools.specificRules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.ftn.sbnz_2020.facts.Vaccine;

class CanineDistemper {

	private KieSession kSession;
	
	private Vaccine testVaccine;
	
	private Disease testDisease1;
	
	private Patient testPatient;
	
	private Diagnose testDiagnose;
	
	@BeforeEach
	void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		
		this.testVaccine=new Vaccine(1L, "DA2P", "v1");
		
		this.testPatient=new Patient(1L, "p1", "REC001", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(),
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		
		Symptom s1 = new Symptom(0L, "s0");
		Symptom s2 = new Symptom(1L, "s1");
		Symptom s3 = new Symptom(2L, "s2");
		Symptom s4 = new Symptom(3L, "s3");
		Symptom s5 = new Symptom(4L, "s4");
		
		ArrayList<Symptom> sList1 = new ArrayList<>();
		sList1.add(s1);
		sList1.add(s2);
		sList1.add(s3);
		sList1.add(s4);
		
		ArrayList<Symptom> sList2 = new ArrayList<>();
		sList2.add(s1);
		sList2.add(s4);
		sList2.add(s5);
		
		testDisease1 = new Disease(0L, "Canine distemper", DiseaseCategory.OTHER, sList1, new ArrayList<Symptom>(), new ArrayList<Therapy>());
		Disease d2 = new Disease(1L, "d1", DiseaseCategory.OTHER, sList2,  new ArrayList<Symptom>(), new ArrayList<Therapy>());
		
		kSession.insert(testDisease1);
		kSession.insert(d2);
		
		kSession.insert(s1);
		kSession.insert(s2);
		
		kSession.insert(testVaccine);

		
	}
	
	@Test
	void noVaccine() {
		testDiagnose = new Diagnose();
		testDiagnose.setPatient(testPatient);
		kSession.insert(testDiagnose);	
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertTrue(testDiagnose.getDisease().equals(testDisease1));
	}
	
	@Test
	void vaccineMoreThanYearAgo() {
		Calendar cal=Calendar.getInstance();
		cal.set(2010, 1, 1);
		Vaccination v=new Vaccination(1L, testVaccine, cal.getTime());
		
		testPatient.getVaccinations().add(v);
		
		kSession.insert(v);
		
		testDiagnose = new Diagnose();
		testDiagnose.setPatient(testPatient);
		kSession.insert(testDiagnose);	
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertTrue(testDiagnose.getDisease().equals(testDisease1));
	}
	
	@Test
	void vaccineTakenInLast14Days(){
		Vaccination v=new Vaccination(1L, testVaccine, new Date());
		
		testPatient.getVaccinations().add(v);
		
		kSession.insert(v);
		
		testDiagnose = new Diagnose();
		testDiagnose.setPatient(testPatient);
		kSession.insert(testDiagnose);	
		
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertTrue(testDiagnose.getDisease().equals(testDisease1));
	}
	
	@Test
	void vaccineTaken(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -16);
		Vaccination v=new Vaccination(1L, testVaccine, cal.getTime());
		
		kSession.insert(v);
		
		testPatient.getVaccinations().add(v);
		
		testDiagnose = new Diagnose();
		testDiagnose.setPatient(testPatient);
		kSession.insert(testDiagnose);	
		
		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		
		kSession.getAgenda().getAgendaGroup("diagnose").setFocus();
		kSession.fireAllRules();
		
		assertFalse(testDiagnose.getDisease().equals(testDisease1));
	}
	
	@AfterEach
	void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);	
	}

}

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

import com.ftn.sbnz_2020.dto.ReportChronicDiseasesDTO;
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
public class PotentialChronicDisease {

	private KieSession kSession;
	
	private Disease testDisease1;
	private Disease testDisease2;
	
	private Patient testPatient1;
	private Patient testPatient2;
	
	@Before
	public void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
				
		this.testDisease1=new Disease(0L,"d0",DiseaseCategory.BACTERIAL,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		this.testDisease2=new Disease(1L,"d1",DiseaseCategory.POISONING,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		
		this.testPatient1=new Patient(0L, "p0", "REC0", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		this.testPatient2=new Patient(1L, "p1", "REC1", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
	}
	
	@Test
	public void noChronicDisease() {
		//5 diagnoses, bad category=>no result
		Diagnose d6=new Diagnose(5L, testDisease2, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d7=new Diagnose(6L, testDisease2, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d8=new Diagnose(7L, testDisease2, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d9=new Diagnose(8L, testDisease2, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d10=new Diagnose(9L, testDisease2, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		
		//4 diagnose=>no result
		Diagnose d11=new Diagnose(10L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d12=new Diagnose(11L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d13=new Diagnose(12L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d14=new Diagnose(13L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		
		//bad date=>no result
		Calendar cal=Calendar.getInstance();
		cal.set(2010, 1, 1);
		Diagnose d15=new Diagnose(14L, testDisease1, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), cal.getTime());
		Diagnose d16=new Diagnose(15L, testDisease1, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d17=new Diagnose(16L, testDisease1, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d18=new Diagnose(17L, testDisease1, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d19=new Diagnose(18L, testDisease1, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		
		
		ArrayList<ReportChronicDiseasesDTO> result=new ArrayList<>();
		
		kSession.insert(result);
		kSession.insert(d6);
		kSession.insert(d7);
		kSession.insert(d8);
		kSession.insert(d9);
		kSession.insert(d10);
		kSession.insert(d11);
		kSession.insert(d12);
		kSession.insert(d13);
		kSession.insert(d14);
		kSession.insert(d15);
		kSession.insert(d16);
		kSession.insert(d17);
		kSession.insert(d18);
		kSession.insert(d19);
		
		kSession.getAgenda().getAgendaGroup("chronic diseases").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==0);
	}
	
	@Test
	public void chronicDiseaseFound() {
		//5 diagnoses=>should get good result
		Diagnose d1=new Diagnose(0L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d2=new Diagnose(1L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d3=new Diagnose(2L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d4=new Diagnose(3L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose d5=new Diagnose(4L, testDisease1, testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		
		ArrayList<ReportChronicDiseasesDTO> result=new ArrayList<>();
		
		kSession.insert(result);
		kSession.insert(d1);
		kSession.insert(d2);
		kSession.insert(d3);
		kSession.insert(d4);
		kSession.insert(d5);
		
		kSession.getAgenda().getAgendaGroup("chronic diseases").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==1);
		assertTrue(result.get(0).getP().equals(testPatient1));
		assertTrue(result.get(0).getD().equals(testDisease1));
	}

	@After
	public void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);	
	}
	
}

package com.ftn.sbnz_2020.drools.reports;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz_2020.dto.BreedDiseases;
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

class MostCommonDiseaseForBreed {

	private KieSession kSession;
	
	private BreedDiseases bd;
	
	@BeforeEach
	void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		this.bd = new BreedDiseases();
		this.bd.setBreed(Breed.MIXEDBREED);
	
		this.kSession.insert(bd);
	}
	
	@Test
	void noDiagnoses() {
		kSession.getAgenda().getAgendaGroup("breed diseases").setFocus();
		kSession.fireAllRules();
		
		assertTrue(this.bd.getDiseases().size()==0);
	}
	
	@Test
	void regularResults() {
		Patient p1=new Patient(0L, "p0", "REC0", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		Patient p2=new Patient(1L, "p1", "REC1", new Date(), Breed.BOXER, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		Patient p3=new Patient(2L, "p2", "REC2", new Date(), Breed.BOXER, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		
		Disease d1=new Disease(2L,"d2",DiseaseCategory.BACTERIAL,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d2=new Disease(3L,"d3",DiseaseCategory.BACTERIAL,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d3=new Disease(4L,"d4",DiseaseCategory.CARDIOVASCULAR,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d4=new Disease(5L,"d5",DiseaseCategory.CANCER,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d5=new Disease(6L,"d6",DiseaseCategory.EYEDISEASE,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());		
		
		Diagnose diagnose1=new Diagnose(1L, d1, p1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
						0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose2=new Diagnose(2L, d1, p1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose3=new Diagnose(3L, d1, p1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose4=new Diagnose(4L, d2, p1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose5=new Diagnose(5L, d2, p1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose6=new Diagnose(6L, d3, p1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose7=new Diagnose(7L, d4, p2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose8=new Diagnose(8L, d4, p2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose9=new Diagnose(9L, d5, p2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose10=new Diagnose(10L, d5, p3, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose11=new Diagnose(11L, d5, p3, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose12=new Diagnose(12L, d5, p3, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
				
		kSession.insert(diagnose1);
		kSession.insert(diagnose2);
		kSession.insert(diagnose3);
		kSession.insert(diagnose4);
		kSession.insert(diagnose5);
		kSession.insert(diagnose6);
		kSession.insert(diagnose7);
		kSession.insert(diagnose8);
		kSession.insert(diagnose9);
		kSession.insert(diagnose10);
		kSession.insert(diagnose11);
		kSession.insert(diagnose12);	
		
		kSession.getAgenda().getAgendaGroup("breed diseases").setFocus();
		kSession.fireAllRules();
		
		assertTrue(bd.getBreed().equals(Breed.MIXEDBREED));
		assertTrue(bd.getDiseases().get(d1.getId())==3);
		assertTrue(bd.getDiseases().get(d2.getId())==2);
		assertTrue(bd.getDiseases().get(d3.getId())==1);
		assertFalse(bd.getDiseases().containsKey(d4.getId()));
		assertFalse(bd.getDiseases().containsKey(d5.getId()));
	}
	
	@AfterEach
	void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);	
	}

}

package com.ftn.sbnz_2020.drools.reports;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

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

class CancerAsChronicDisease {

	private KieSession kSession;
	
	private Disease testDisease1;
	
	private Patient testPatient1;
	private Patient testPatient2;
	
	@BeforeEach
	void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
				
		this.testDisease1=new Disease(0L,"d0",DiseaseCategory.CANCER,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());		
		
		this.testPatient1=new Patient(0L, "p0", "REC0", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		this.testPatient2=new Patient(1L, "p1", "REC1", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
	}
	
	@Test
	void noChronicDisease() {
		//bad category=>no result
		Disease d1=new Disease(2L,"d2",DiseaseCategory.BACTERIAL,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d2=new Disease(3L,"d3",DiseaseCategory.BEHAVIORAL,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d3=new Disease(4L,"d4",DiseaseCategory.CARDIOVASCULAR,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d4=new Disease(5L,"d5",DiseaseCategory.ENDOCRINE,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d5=new Disease(6L,"d6",DiseaseCategory.EYEDISEASE,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d6=new Disease(7L,"d7",DiseaseCategory.INFECTIOUS,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d7=new Disease(8L,"d8",DiseaseCategory.NEUROLOGIC,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d8=new Disease(9L,"d9",DiseaseCategory.OTHER,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d9=new Disease(10L,"d10",DiseaseCategory.PARASITES,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d10=new Disease(11L,"d11",DiseaseCategory.POISONING,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d11=new Disease(12L,"d12",DiseaseCategory.SKELETAL_AND_MUSCULAR_DISORDER,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		Disease d12=new Disease(13L,"d13",DiseaseCategory.URINAR,new ArrayList<Symptom>(),new ArrayList<Symptom>(),new ArrayList<Therapy>());
		
		
		Diagnose diagnose1=new Diagnose(1L, d1, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
						0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose2=new Diagnose(2L, d2, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose3=new Diagnose(3L, d3, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose4=new Diagnose(4L, d4, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose5=new Diagnose(5L, d5, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose6=new Diagnose(6L, d6, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose7=new Diagnose(7L, d7, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose8=new Diagnose(8L, d8, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose9=new Diagnose(9L, d9, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose10=new Diagnose(10L, d10, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose11=new Diagnose(11L, d11, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		Diagnose diagnose12=new Diagnose(12L, d12, testPatient2, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
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
		
		ArrayList<ReportChronicDiseasesDTO> result=new ArrayList<>();
		
		kSession.insert(result);
		
		kSession.getAgenda().getAgendaGroup("chronic diseases").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==0);
		
	}
	
	@Test
	void chronicDiseaseFound() {
		Diagnose diagnose1=new Diagnose(1L, this.testDisease1, this.testPatient1, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(), 
				0L, 0L, new ArrayList<Therapy>(), new Date());
		
		kSession.insert(diagnose1);
		
		ArrayList<ReportChronicDiseasesDTO> result=new ArrayList<>();
		
		kSession.insert(result);
		
		kSession.getAgenda().getAgendaGroup("chronic diseases").setFocus();
		kSession.fireAllRules();
		
		assertTrue(result.size()==1);
		assertTrue(result.get(0).getP().equals(testPatient1));
		assertTrue(result.get(0).getD().equals(testDisease1));
	}
	
	@AfterEach
	void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);	
	}
	
	
	
}

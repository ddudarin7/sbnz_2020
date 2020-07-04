package com.ftn.sbnz_2020.drools.general;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class MedicineAllergy {
	
	private KieSession kSession;
	
	private Patient testPatient;
	private Diagnose testDiagnose;
	private Medicine testMedicine1;
	
	@Before
	public void setUpTest() {
		this.kSession = com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		
		this.testMedicine1=new Medicine(1L, "m1");
		Medicine m2=new Medicine(2L, "m2");
		
		ArrayList<Medicine> list1=new ArrayList<>();
		list1.add(this.testMedicine1);
		
		ArrayList<Medicine> list2=new ArrayList<>();
		list2.add(m2);
		
		ArrayList<Medicine> list3=new ArrayList<>();
		list3.add(this.testMedicine1);
		list3.add(m2);
		
		Therapy t1=new Therapy(1L, "t1", list1);
		Therapy t2=new Therapy(2L, "t2", list2);
		Therapy t3=new Therapy(3L, "t3", list3);
		
		ArrayList<Therapy> tList1=new ArrayList<>();
		tList1.add(t1);
		tList1.add(t2);
		tList1.add(t3);
		
		this.testPatient=new Patient(1L, "p", "REC001", new Date(), Breed.MIXEDBREED, new Owner(), new ArrayList<Medicine>(), 
				new ArrayList<Ingredient>(), new ArrayList<Vaccination>());
		
		Disease d1=new Disease(1L, "d1", DiseaseCategory.OTHER, new ArrayList<Symptom>(), new ArrayList<Symptom>(), tList1);
		
		this.testDiagnose=new Diagnose(1L, d1, testPatient, new Vet(), new ArrayList<Symptom>(), new ArrayList<Symptom>(),
				0L, 0L, tList1, new Date());
	}

	@Test
	public void allergyMatched() {
		this.testPatient.getMedicineAllergies().add(testMedicine1);
		kSession.insert(testDiagnose);
		kSession.getAgenda().getAgendaGroup("allergy checking").setFocus();
		kSession.fireAllRules();
		
		assertTrue(testDiagnose.getTherapies().size()==1);
		assertFalse(testDiagnose.getTherapies().get(0).getMedicines().contains(testMedicine1));
	}
	
	@Test
	public void noAllergyMatched() {
		kSession.insert(testDiagnose);
		kSession.getAgenda().getAgendaGroup("allergy checking").setFocus();
		kSession.fireAllRules();
		
		assertTrue(testDiagnose.getTherapies().size()==3);
		assertTrue(testDiagnose.getTherapies().get(0).getMedicines().contains(testMedicine1));
		assertTrue(testDiagnose.getTherapies().get(2).getMedicines().contains(testMedicine1));
	}
	
	@After
	public void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);
	}

}

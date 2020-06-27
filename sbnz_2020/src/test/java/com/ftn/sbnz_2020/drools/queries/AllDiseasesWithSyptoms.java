package com.ftn.sbnz_2020.drools.queries;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;
import com.ftn.sbnz_2020.facts.Symptom;
import com.ftn.sbnz_2020.facts.Therapy;


class AllDiseasesWithSyptoms {

	private KieSession kSession;
	
	private Disease testDisease1;
	private Disease testDisease2;
	private Disease testDisease3;
	private Disease testDisease4;
	private Disease testDisease5;
	
	@BeforeEach
	void setUpTest() {
		this.kSession=com.ftn.sbnz_2020.drools.utils.Utils.configurateKieSession();
		
		//define data
		Symptom s1=new Symptom(0L,"s0");
		Symptom s2=new Symptom(1L,"s1");		
		Symptom s3=new Symptom(2L,"s2");
		Symptom s4=new Symptom(3L,"s3");
		Symptom s5=new Symptom(4L,"s4");
		
		ArrayList<Symptom> sList1=new ArrayList<>();
		sList1.add(s1);
		
		ArrayList<Symptom> sList2=new ArrayList<>();
		sList2.add(s1);
		sList2.add(s2);
		
		ArrayList<Symptom> sList3=new ArrayList<>();
		sList3.add(s3);
		sList3.add(s4);
		sList3.add(s5);
		
		ArrayList<Symptom> sList4=new ArrayList<>();
		sList4.add(s2);
		
		//one out of two symptoms matched as spec.symptom
		this.testDisease1=new Disease(0L,"d0",DiseaseCategory.BACTERIAL,sList1,new ArrayList<Symptom>(),new ArrayList<Therapy>());
		//two out of two symptoms matched as spec.symptoms
		this.testDisease2=new Disease(1L,"d1",DiseaseCategory.BACTERIAL,sList2,new ArrayList<Symptom>(),new ArrayList<Therapy>());
		//no symptoms matched
		this.testDisease3=new Disease(2L,"d2",DiseaseCategory.BACTERIAL,sList3,new ArrayList<Symptom>(),new ArrayList<Therapy>());		
		//one out of two symptoms matched as non spec.symptom
		this.testDisease4=new Disease(3L,"d3",DiseaseCategory.BACTERIAL,new ArrayList<Symptom>(),sList1,new ArrayList<Therapy>());
		//one of spec. symptoms and one of non spec. symptoms matched
		this.testDisease5=new Disease(4L,"d4",DiseaseCategory.BACTERIAL,sList1,sList4,new ArrayList<Therapy>());
		
		//insert to session
		this.kSession.insert(s1);
		this.kSession.insert(s2);
		
		kSession.insert(this.testDisease1);
		kSession.insert(this.testDisease2);
		kSession.insert(this.testDisease3);
		kSession.insert(this.testDisease4);
		kSession.insert(this.testDisease5);
	}
	
	@Test
	void noResultsTest() {
		QueryResults results=this.kSession.getQueryResults("Get all diseases that satisfy one or more symptoms");
		assertTrue(results.size()==0);
	}

	@Test
	void resultNumber(){		
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		QueryResults results=this.kSession.getQueryResults("Get all diseases that satisfy one or more symptoms");
		assertTrue(results.size()==4);
	}
	
	@Test
	void resultValues() {
		List<Disease> matching=new ArrayList<>();
		kSession.getAgenda().getAgendaGroup("finding symptoms").setFocus();
		kSession.fireAllRules();
		QueryResults results=this.kSession.getQueryResults("Get all diseases that satisfy one or more symptoms");
		for (QueryResultsRow row: results) {
            System.out.println(row.get("$d"));
            Disease disease = (Disease) row.get("$d");
            matching.add(disease);
        }
		assertTrue(matching.contains(this.testDisease1));
		assertTrue(matching.contains(this.testDisease2));
		assertFalse(matching.contains(this.testDisease3));	
		assertTrue(matching.contains(this.testDisease4));
		assertTrue(matching.contains(this.testDisease5));
	}
	
	@AfterEach
	void endTest() {
		com.ftn.sbnz_2020.drools.utils.Utils.destroyKieSession(this.kSession);		
	}
		
}

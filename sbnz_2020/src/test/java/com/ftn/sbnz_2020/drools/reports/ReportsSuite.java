package com.ftn.sbnz_2020.drools.reports;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CancerAsChronicDisease.class,
	MostCommonDiseaseForBreed.class,
	PotentialChronicDisease.class,
	WeakenedImmunity.class
})
public class ReportsSuite {

}

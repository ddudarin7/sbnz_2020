package com.ftn.sbnz_2020.drools.general;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AllSpecAndNonSpecSymptoms.class,
	FindMatchingSpecSymp.class,
	FindMatchingNonSpecSymp.class,
	HighestPercentageNonSpecSymp.class,
	HighestPercentageSpecSymp.class,
	IngredientAllergy.class,
	MedicineAllergy.class,
	MoreNonSpecSymptoms.class,
	MoreSpecSymptoms.class,
	ZeroOrMoreDiseases.class
})

public class GeneralSuite {

}

package com.ftn.sbnz_2020.drools.specificRules;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	BreedsCancer.class,
	BreedsRickets.class,
	BreedsToothPathology.class,
	CanineDistemper.class
})
public class SpecialRulesSuite {

}

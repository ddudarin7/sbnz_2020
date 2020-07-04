package com.ftn.sbnz_2020;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ftn.sbnz_2020.drools.KieConfiguration;
import com.ftn.sbnz_2020.drools.general.GeneralSuite;
import com.ftn.sbnz_2020.drools.queries.AllDiseasesWithSyptoms;
import com.ftn.sbnz_2020.drools.reports.ReportsSuite;
import com.ftn.sbnz_2020.drools.specificRules.SpecialRulesSuite;

@RunWith(Suite.class)
@SuiteClasses({
	KieConfiguration.class,
	GeneralSuite.class,
	AllDiseasesWithSyptoms.class,
	ReportsSuite.class,
	SpecialRulesSuite.class,
})
public class AllTestsSuite {

}

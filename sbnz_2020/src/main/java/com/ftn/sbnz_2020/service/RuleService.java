package com.ftn.sbnz_2020.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;

import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBaseFactory;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.dto.RuleDTO;

@Service
public class RuleService {
	public RuleDTO save(KieSession kieSession, RuleDTO rule) {
		System.out.println(rule.getDroolsRuleCode());
		
		FileWriter fw;
		try {
			fw = new FileWriter("..\\drools-spring-kjar\\src\\main\\resources\\drools\\spring\\rules\\customRules.drl");
			fw.write(rule.getDroolsRuleCode());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return rule;
    }
	
	public RuleDTO getRules() {
		RuleDTO result = new RuleDTO("");
		File f = new File("..\\drools-spring-kjar\\src\\main\\resources\\drools\\spring\\rules\\customRules.drl");
		Scanner scan;
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				result.setDroolsRuleCode(result.getDroolsRuleCode() + "\n" + scan.nextLine());
			}
			System.out.println("USLO OVDJE!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}

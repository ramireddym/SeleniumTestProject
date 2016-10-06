package com.keetech.seleniumtraining.tests;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
@CucumberOptions(
		  features={"src/cucumber/"},
		  format={"pretty"}
		  )
public class CucumberRunner {

}

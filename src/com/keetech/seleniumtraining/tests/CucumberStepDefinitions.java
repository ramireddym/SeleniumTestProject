package com.keetech.seleniumtraining.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberStepDefinitions {
	WebDriver driver;
	
	@Before
	public void setup(){
		System.out.println("Inside before setup method...");
	}
	
	@Given("^URL as <url> and browser as <browser>$")
	public void url_as_url_and_browser_as_browser(DataTable table) throws Throwable {
		String url = table.getGherkinRows().get(1).getCells().get(0);
		String browsertype = table.getGherkinRows().get(1).getCells().get(1);
		 switch(browsertype){
	    	case "firefox": 
	    		 driver = new FirefoxDriver();
	    		 break;
	    	case  "ie":
	    		 driver = new InternetExplorerDriver();
	    		 break;
	    }
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	@Given("^URL as \"([^\"]*)\" and browser as \"([^\"]*)\"$")
	public void url_as_and_browser_as(String url, String browsertype) throws Throwable {
	    switch(browsertype){
	    	case "firefox": 
	    		 driver = new FirefoxDriver();
	    		 break;
	    	case  "ie":
	    		 driver = new InternetExplorerDriver();
	    		 break;
	    }
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
	}

	@When("^username is \"([^\"]*)\"$")
	public void username_is(String arg1) throws Throwable {
		
		WebElement usernameelement = driver.findElement(By.id("Email"));
		usernameelement.clear();
		usernameelement.sendKeys(arg1);
		driver.findElement(By.id("next")).click();
	}

	@When("^password is \"([^\"]*)\"$")
	public void password_is(String arg1) throws Throwable {
		driver.findElement(By.id("Passwd")).sendKeys(arg1);
		driver.findElement(By.id("signIn")).click();
	}

	@Then("^Verify Gmail Home Page$")
	public void verify_Gmail_Home_Page() throws Throwable {
	 
	}

	@Then("^Verify error message \"([^\"]*)\" displayed$")
	public void verify_error_message_displayed(String arg1) throws Throwable {
	    
	}
	@After
	public void teardown(){
		driver.quit();
	}
}

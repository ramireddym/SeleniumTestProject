#Author: prasanna
Feature: 
  Gmail Login Functionality
	
	@Regression
  Scenario: 
     Gmail Valid Login

    Given URL as "https://www.gmail.com" and browser as "firefox"
    When username is "ravikirangudla@gmail.com"
    And password is "abc@123"
    Then Verify Gmail Home Page
    
	@smoke
  Scenario: 
    Gmail Invalid Login

    Given URL as "https://www.gmail.com" and browser as "firefox"
    When username is "ravikirangudla@gmail.com"
    And password is "abc@123"
    Then Verify error message "invalid password" displayed

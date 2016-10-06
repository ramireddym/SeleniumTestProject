#Author: prasanna
Feature: 
  Gmail Login Functionality
	
	Background:
		Given URL as <url> and browser as <browser>
	|URL|browser|
	|https://www.gmail.com|firefox|
	|https://www.gmail.com|ie|
	
  @Regression
  Scenario: 
     Gmail Valid Login

    Given URL as <url> and browser as <browser>
    |url|browser|
    |https://www.gmail.com|firefox|
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

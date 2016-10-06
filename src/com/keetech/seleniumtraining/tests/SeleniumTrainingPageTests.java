package com.keetech.seleniumtraining.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumTrainingPageTests {
    public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///D:/test.html");
		driver.manage().window().maximize();
		
		//this timeout is applicable for the entire driver life cycle
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Test to Validate Header Text of a page
		String headerText = driver.findElement(By.id("header")).getText();
		Assert.assertEquals(headerText, "WelCome to Selenium Training");
		
		//Another Test - To Test Login
		driver.findElement(By.id("username")).sendKeys("keetech");
		driver.findElement(By.id("password")).sendKeys("keetech");
		driver.findElement(By.id("login")).click();
		
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.alertIsPresent());
	
		Alert alert = driver.switchTo().alert();
		String alerttext = alert.getText();
		System.out.println(alerttext);
		Assert.assertEquals(alert.getText(), "OOPS!!! Login Failed!!!");
		alert.accept();
		Assert.assertEquals(alerttext, "Login Success");
	}
}
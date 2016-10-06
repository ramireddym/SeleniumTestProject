package com.keetech.seleniumtraining.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleHomePageTests {

	public static WebDriver driver = new FirefoxDriver();

	@BeforeTest
	public void checkbeforetest(){
		System.out.println("Before Test method....");
	}
	@Test(groups={"Regression"})
	public void searchGoogle(){
		driver.get("https://www.google.co.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //implicit wait
		
		WebElement searchfield = driver.findElement(By.id("lst-ib"));
		searchfield.clear();
		searchfield.sendKeys("keetech");
		driver.findElement(By.className("lsb")).click();
		String actualtext = driver.findElement(By.className("_Rm")).getText();
		String expectedtext = "www.keetechsoft.com/";
		
		Assert.assertEquals(actualtext, expectedtext);
		
	}
	
	@Test
	public void secondTest(){
		System.out.println("Second Test");
	}
}

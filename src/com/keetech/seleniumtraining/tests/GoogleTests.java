package com.keetech.seleniumtraining.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GoogleTests {
    public static void main(String[] args) throws InterruptedException {
    	final String expectedinfomsg="Meet Advay, a teen scientist from Chennai who is making fishing safer";
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.google.co.in/?gfe_rd=cr&ei=DeyVV8GxOqvv8weUx6yAAQ&gws_rd=ssl");
		driver.manage().window().maximize();
		System.out.println("Current Browser Title: "+driver.getTitle());
		WebElement infomsg = driver.findElement(By.xpath("//div[contains(text(),'Meet')]"));
		//System.out.println("Information message from Google Search page:"+infomsg.getText());
		Assert.assertEquals(infomsg.getText(), expectedinfomsg);
		
		WebElement searchbox = driver.findElement(By.id("lst-ib"));
		searchbox.clear();
		searchbox.sendKeys("mantis bt");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		String currentwindowtitle = driver.getTitle();
		driver.findElement(By.linkText("MantisBT | SourceForge.net")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains("MantisBT | Source"));
		/*int count=0;
		String currenttitle=currentwindowtitle;
		while(count++<10 && currenttitle.equalsIgnoreCase(currentwindowtitle))
		{
			currenttitle = driver.getTitle();
			System.out.println("Inside while loop getting page title:"+currenttitle);
			Thread.sleep(2000);
		}*/
		//String actualtitle = driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "MantisBT | SourceForge.net");
    }
}

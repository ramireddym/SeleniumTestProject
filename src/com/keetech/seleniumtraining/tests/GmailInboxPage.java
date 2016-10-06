package com.keetech.seleniumtraining.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GmailInboxPage {
	WebDriver driver;
	public GmailInboxPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void searchInbox(String searchtext){
		navigateToPage();
		driver.findElement(By.id("gbqfq")).sendKeys(searchtext);
		driver.findElement(By.id("gbqfb")).click();
	}
	
	public int getInboxMailsCount(){
		List<WebElement> lstablerows = driver.findElements(By.xpath("//div[@gh='tl']//table/tbody/tr"));
		return lstablerows.size();
	}
	
	public void navigateToPage(){
		driver.findElement(By.partialLinkText("Inbox")).click();
	}
}

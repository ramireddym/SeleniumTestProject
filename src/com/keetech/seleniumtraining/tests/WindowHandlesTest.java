package com.keetech.seleniumtraining.tests;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class WindowHandlesTest {
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.manage().window().maximize();
		
		//this timeout is applicable for the entire driver life cycle
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String parentwindowid = driver.getWindowHandle();
		System.out.println("parentwindow: "+parentwindowid);
		WebElement knowmoreelement = driver.findElement(By.linkText("Know More"));
		
		/*Actions action = new Actions(driver);
		action.moveToElement(knowmoreelement);
		action.build().perform();*/
		
		knowmoreelement.click(); 
		
		System.out.println("Current window id: "+driver.getWindowHandle());
		Set<String> setwindowids = driver.getWindowHandles();
		for(String window: setwindowids){
			if(window.equals(parentwindowid))
				continue;
			else{
				driver.switchTo().window(window);
				break;
			}
			
		}//for loop close
		System.out.println("Current window title: " +driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "HDFC Bank | NetBanking-Convenience");
		
		driver.close(); //close child window
		driver.switchTo().window(parentwindowid);
		System.out.println("Current window:"+ driver.getTitle());;
	}
}

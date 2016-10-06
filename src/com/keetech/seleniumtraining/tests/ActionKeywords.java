package com.keetech.seleniumtraining.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionKeywords {
	WebDriver driver;

	/*public ActionKeywords(WebDriver driver) {
		this.driver = driver;
	}*/
	public void openBrowser(String browsertype){
	 if(browsertype.equalsIgnoreCase("firefox")){
		 if(driver!=null)
			 quitdriver();
		   driver = new FirefoxDriver();
		   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		   driver.manage().window().maximize();
	 }
	}
	
	public void openURL(String url){
		driver.get(url);
	}
	public void typeEditBox(String uielement, String locatortype, String value){
		WebElement we = getElement(uielement, locatortype);
		we.clear();
		we.sendKeys(value);
	}
	
	public void clickButton(String uielement, String locatortype){
		WebElement we = getElement(uielement, locatortype);
		we.click();
	}
	
	public void clickLink(String uielement, String locatortype){
		WebElement we = getElement(uielement, locatortype);
		we.click();
	}
	
	public void checkCheckBox(String uielement, String locatortype){
		WebElement we = getElement(uielement, locatortype);
		if(!we.isSelected())
			we.click();
	}
	public void uncheckCheckBox(String uielement, String locatortype){
		WebElement we = getElement(uielement, locatortype);
		if(we.isSelected())
			we.click();
	}
	
	public void verifyUnsuccessfulLogin(String uielement, String locatortype) throws Exception{
		System.out.println("Inside verifyUnsuccessfulLogin method");
		WebElement we = getElement(uielement, locatortype);
		if(we!=null){
			System.out.println("Successfully verified invalid password login..");
			System.out.println("Error message.."+we.getText());
		}
		else
		{
			throw new Exception("It has to be failed to login. But it seems to be a success!!!");
		}
	}
	
	public void verifysuccessfulLogin(String uielement,String locatortype) throws Exception
	{
		WebElement composemail = getElement(uielement, locatortype);
		if(composemail!=null)
			System.out.println("User has successfully logged in");
		else
		{
			throw new Exception("It should be a successful login. But it's failed. Please check");
		}
		/*WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='COMPOSE']")));*/
	}
	
	public WebElement getElement(String uielement,String locatortype){
		System.out.println("Locator Value:"+uielement);
		System.out.println("Locator Type:"+locatortype);
		switch(locatortype.trim()){
		case "id": 
			 return driver.findElement(By.id(uielement));
		case "cssselector": 
			return driver.findElement(By.cssSelector(uielement));
		case "xpath": 
			return driver.findElement(By.xpath(uielement));
		case "linktext":
			return driver.findElement(By.linkText(uielement));
		default:
			System.out.println("locatortype doesn't exist"+locatortype);
			return null;
		}
	}
	public void quitdriver(){
		driver.quit();
	}
}

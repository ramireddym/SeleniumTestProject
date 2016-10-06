package com.keetech.seleniumtraining.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.beust.testng.TestNG;

public class KeywordDriver {

	static ActionKeywords actionkeyword = new ActionKeywords();
	public static void main(String[] args) throws Exception {
		Xls_Reader reader = new Xls_Reader("TestSteps.xlsx");
		XSSFWorkbook workbook = reader.getWorkBook();
		XSSFSheet sheet1 = reader.getSheetByIndex(workbook, 1);
		//Load all tcs marked as YES into ArrayList
		List<String> lsTestCasesForExecution = new ArrayList<>();
		for(int i=1;i<=sheet1.getLastRowNum();i++){
			XSSFRow row = sheet1.getRow(i);
			String runmode = reader.getCellValueAsString(row.getCell(1));
			if(runmode.equalsIgnoreCase("YES"))
				lsTestCasesForExecution.add(reader.getCellValueAsString(row.getCell(0)));
		}
		
		XSSFSheet sheet = reader.getSheetByIndex(workbook, 0);
		String testcaseid="";
		TestNG testng = new TestNG();
		XmlSuite loginSuite = new XmlSuite();
		boolean istestcasematched =false;
		for(String testcase: lsTestCasesForExecution){
		for(int i=2;i<=sheet.getLastRowNum();i++){
			XSSFRow row = sheet.getRow(i);
			String actualtestcaseid = reader.getCellValueAsString(row.getCell(0));
			/*if(!testcaseid.isEmpty() && !actualtestcaseid.equalsIgnoreCase(testcaseid)){
					testcaseid="";
					System.out.println("Reached end of test. Hence killing the driver");
					actionkeyword.quitdriver();
			}*/
			//check whether testcase is matched or not if it's matched run all steps of that testcase
			//once all the steps have finished then kill the driver
			//If i got a new TestcaseID then assuming that all steps of current testcaseid are finished
			if(!actualtestcaseid.equalsIgnoreCase(testcase) && istestcasematched){
				istestcasematched=false;
				actionkeyword.quitdriver();
				break;
			}
			else if(actualtestcaseid.equalsIgnoreCase(testcase))
				istestcasematched = true;
			else
				continue;
			
			
			XSSFCell cell = row.getCell(5);
			//check whether teststep is marked for execution or not
			if(cell!=null){
				String runmode = reader.getCellValueAsString(row.getCell(5));
				if(runmode.equalsIgnoreCase("NO"))
					continue;
			}
			//start of test
/*			if(!actualtestcaseid.equalsIgnoreCase(testcaseid) && testcaseid.isEmpty())
			{
				testcaseid = actualtestcaseid;
				loginSuite.setName("Sample Test Suite");

				XmlTest first_test = new XmlTest();
				first_test.setName(testcaseid);
				first_test.setSuite(loginSuite);
				loginSuite.addTest(first_test);
				List<XmlSuite> ls = new ArrayList<XmlSuite>();
				ls.add(loginSuite);
				testng.setXmlSuites(ls);
				testng.run();
			}*/
			//end of test

			String action = reader.getCellValueAsString(row.getCell(3));
		switch(action.trim()){
			case "openBrowser":
				String browsertype = reader.getCellValueAsString(row.getCell(4));
				actionkeyword.openBrowser(browsertype);
				break;
			case "openURL":
				actionkeyword.openURL(reader.getCellValueAsString(row.getCell(4)));
				break;
			case "typeEditBox":
				String uielement = reader.getCellValueAsString(row.getCell(1));
				String locatortype = reader.getCellValueAsString(row.getCell(2));
				String value = reader.getCellValueAsString(row.getCell(4));
				actionkeyword.typeEditBox(uielement, locatortype, value);
				break;
			case "clickButton":
				String uielement1 = reader.getCellValueAsString(row.getCell(1));
				String locatortype1 = reader.getCellValueAsString(row.getCell(2));
				actionkeyword.clickButton(uielement1,locatortype1);
				break;

			case "verifyUnsuccessfulLogin":
				String uielement2 = reader.getCellValueAsString(row.getCell(1));
				String locatortype2 = reader.getCellValueAsString(row.getCell(2));
				actionkeyword.verifyUnsuccessfulLogin(uielement2, locatortype2);
				break;
			case "verifysuccessfulLogin":
				String uielement3 = reader.getCellValueAsString(row.getCell(1));
				String locatortype3 = reader.getCellValueAsString(row.getCell(2));
				actionkeyword.verifysuccessfulLogin(uielement3, locatortype3);
				break;
			default:
				System.out.println("No action found: "+action);
			}
		}
		actionkeyword.quitdriver();
	}
}
}

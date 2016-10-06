package com.keetech.seleniumtraining.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.examples.CellTypes;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class Xls_Reader {
	
	String excelfilename;
	
	public Xls_Reader(String filename){
		this.excelfilename = filename;
	}
	
	public XSSFWorkbook getWorkBook() throws FileNotFoundException, IOException{
		XSSFWorkbook workbook3 = new XSSFWorkbook(new FileInputStream(new File(excelfilename)));
		return workbook3;
	}
	
	public XSSFSheet getSheetByIndex(XSSFWorkbook workbook, int index){
		if(workbook!=null)
			return workbook.getSheetAt(index);
		else{
			System.out.println("Workbook can't be null");
			return null;
		} 
	}
	
	public XSSFSheet getSheetByName(XSSFWorkbook workbook,String sheetname){
		if(workbook!=null)
			return workbook.getSheet(sheetname);
		else{
			System.out.println("Workbook can't be null");
			return null;
		} 
	}
	
	public String getCellValue(XSSFSheet sheet, int rownum, int col){
		XSSFRow row = sheet.getRow(rownum);
		XSSFCell cell = row.getCell(col);
		String cellvalue = getCellValueAsString(cell);
		return cellvalue;
	}
	
	public String[][] getAllValuesofTestCaseID(String exptestcaseid, int sheetindex) throws FileNotFoundException, IOException{
		XSSFWorkbook workbook = getWorkBook();
		XSSFSheet sheet = getSheetByIndex(workbook, sheetindex);
		int rowcount = sheet.getLastRowNum();
		int startrow=0;
		int endrow=0;
		boolean ismatch=false;
		for(int i=0;i<rowcount;i++){
			XSSFRow row = sheet.getRow(i);
			String actualtestcaseid = getCellValueAsString(row.getCell(0));
			if(actualtestcaseid.equalsIgnoreCase(exptestcaseid))
			{
				if(!ismatch){
					startrow = i;
					ismatch=true;
				}
				endrow = i;
			}
			else{
				if(ismatch)
				{
					break;
				}
			}
		}
	
		int l=0,m=0;
		String[][] testcasedata=null;
		for(int j=startrow;j<endrow;j++){
			XSSFRow row = sheet.getRow(j);
			int cellcount = row.getLastCellNum();
			 testcasedata = new String[endrow-startrow][cellcount];

			for(int k=1;k<cellcount;k++){
				String cellvalue = getCellValueAsString(row.getCell(k));
				testcasedata[l++][k-1] = cellvalue;
			}
			
		}
		return testcasedata;
	}
	
	public String getCellValueAsString(XSSFCell cell){
	 switch(cell.getCellType()){
	 	case XSSFCell.CELL_TYPE_STRING:
	 		return cell.getStringCellValue();

	 	case XSSFCell.CELL_TYPE_NUMERIC:
	 		return String.valueOf(cell.getNumericCellValue());
	
	 	case XSSFCell.CELL_TYPE_BOOLEAN:
	 		return String.valueOf(cell.getBooleanCellValue());
	 	}
	 return null;
	}
	
	public static void main(String[] args) throws IOException, InvalidFormatException {
		//String s = "Hey \"apple\"";
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("E:\\abc.xlsx")));
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		System.out.println("Number of rows:"+rows);
		for(int i=1;i<=rows;i++){
			XSSFRow row = sheet.getRow(i);
			int cellscount = row.getLastCellNum();
			 for(int j=0;j<cellscount;j++){
				 XSSFCell cell = row.getCell(j);
				 
				 switch(cell.getCellType()){
				 	case XSSFCell.CELL_TYPE_STRING:
				 		System.out.println("Data in row,cell:"+i+","+j+" is :"+cell.getStringCellValue());
				 		break;
				 	case XSSFCell.CELL_TYPE_NUMERIC:
				 		System.out.println("Data in row,cell:"+i+","+j+" is :"+(int)cell.getNumericCellValue());
				 		break;
				 	case XSSFCell.CELL_TYPE_BOOLEAN:
				 		System.out.println("Data in row,cell:"+i+","+j+" is :"+cell.getBooleanCellValue());
				 		break;
				 }
				 
			 }
		}
		workbook.close();
	}	
	}

package com.mmt.qa.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelCellValueFetch {

	public static void main(String[] args) throws NumberFormatException, IOException, InvalidFormatException {

		System.out.println("Enter the row number");
		BufferedReader obj = new BufferedReader(new InputStreamReader((System.in)));
		int row = Integer.parseInt(obj.readLine());
		System.out.println("Enter the column number");
		int column = Integer.parseInt(obj.readLine());
		System.out.println("Describe the sheet name");
		String sheetName = obj.readLine();

		ExcelCellValueFetch rc = new ExcelCellValueFetch(); // object of the class
		// reading the value of particular row and column
		rc.ReadCellData(row, column, sheetName);
	}

	// method defined for reading a cell
	public void ReadCellData(int vRow, int vColumn, String sheetName) throws InvalidFormatException {

		String value = null; // variable for storing the cell value
		Workbook wb = null; // initialize Workbook null
		try {
			// reading data from a file in the form of bytes
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Shreo-Niloy\\eclipse-workspace\\MakeMyTrip\\src\\main\\java\\com\\mmt\\qa\\test\\ExcelCellValueFetch.xlsx");
			// constructs an Workbook object, by buffering the whole stream into the
			// memory
			wb = WorkbookFactory.create(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Sheet sheet = wb.getSheet(sheetName); // getting the Sheet object at given index

		if (vRow < sheet.getLastRowNum()) {
			Row row = sheet.getRow(vRow);
			if (vColumn < sheet.getRow(0).getLastCellNum()) {
				Cell cell = row.getCell(vColumn); // getting the cell representing the given column
				value = cell.getStringCellValue(); // getting cell value
				System.out.println(value);// prints the cell value
			} else {
				System.out.println("Entered column value exceeds the total column count in the sheet");
			}
		} else {
			System.out.println("Entered row value exceeds the total row count in the sheet");
		}
	}
}

package CommonMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility_Methods_Practice {
	static String tc_name;
	public static ArrayList<String> ReadAndWriteDataExcel(String filename, String Sheetname, String TestCaseName)
			throws IOException {
		 ArrayList<String> dataarraylist = new ArrayList<String>();
		
		// Fetch Current Working Directory

		String projectfolder = System.getProperty("user.dir");
		System.out.println(projectfolder);

		// Locate Designated file
		FileInputStream fis = new FileInputStream(projectfolder + "\\Data_File\\" + filename + ".xlsx");

		// Open file using XSSFWorkbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		// Find number of sheets
		int countOfsheets = wb.getNumberOfSheets();
		// System.out.println("\nNumber of Sheets " +countOfsheets);
		for (int i = 0; i < countOfsheets; i++) {
			String sheetname = wb.getSheetName(i); // stores all sheets of in excelfile in sheetname
			// System.out.println("\nName of All the Sheets in Excel File " sheetname);
			if (sheetname.equals(Sheetname)) { // compares all sheets with the sheet we put in argument of method
				XSSFSheet sheet = wb.getSheetAt(i); // store that required sheet
				// System.out.println("\nName of the sheet Required " +sheet.getSheetName());
				Iterator<Row> rows = sheet.iterator(); //iteration supposed to happen on object Row in sheet, iterate in sheet on row, beneficial for dynamic data, unlike for loop we set endvalue.
				while(rows.hasNext()) { //whether sheet contains rows or not, considers all rows in sheet
					Row datarows = rows.next(); //move towards next rows, one by one
				 tc_name = datarows.getCell(0).getStringCellValue(); // stores all test cases of rows with given test case
				//System.out.println(tc_name);
				if(tc_name.equals(TestCaseName)) { //compare testcase names
					//System.out.println(tc_name);
					Iterator<Cell> celldata = datarows.cellIterator(); //iterates through rows
					while(celldata.hasNext()) { //will iterate until last cell
						Cell cd = celldata.next(); // will iterate in next cell
						String testdata = null;
						CellType datatype = cd.getCellType();
						if(datatype.toString().equals("STRING")) {
							testdata = cd.getStringCellValue();
							
						}
						else if(datatype.toString().equals("NUMERIC")) {
							double inttestdata = cd.getNumericCellValue();
							testdata = String.valueOf(inttestdata);
						}
						dataarraylist.add(testdata);
						//System.out.println(testdata);
					}
				break;
			} 
				}
				if(!rows.hasNext()) {
					System.out.println("desired " +tc_name+ "was not found in " +sheetname+ "of file" );
			}
		break;
	} else  {
		System.out.println("Desired" + Sheetname + "was not found in" + filename + ".xlsx");	
}
		
}
		wb.close();
		return dataarraylist;
	}
	
}
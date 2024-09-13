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

public class ExcelPractice {

	static String tc_name;
	
	public static ArrayList<String> readDataFromExcel(String filename, String Sheetname, String TestCasename) throws IOException {
		
		ArrayList<String> dataArrayList = new ArrayList<String>();
		
		//Fetch current working directory
		String projectfolder = System.getProperty("user.dir");
		System.out.println(projectfolder);
		
		//Locate the designated excel file 
		FileInputStream fis = new FileInputStream(projectfolder+ "\\Data_File" +filename+ ".xlsx");
	
		//Open Excel file using XSSFWorkbook
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		
		//count no of sheets in excel file
		int countofsheets = wb.getNumberOfSheets();
		for(int i = 0; i<countofsheets; i++) {
		String sheetname = wb.getSheetName(i);
		if(sheetname.equals(Sheetname)) {
			XSSFSheet sheet = wb.getSheetAt(i);
			Iterator<Row> rows = sheet.iterator();
			while(rows.hasNext()) {
				Row datarows = rows.next();
				 tc_name = datarows.getCell(0).getStringCellValue();
				if(tc_name.equals(TestCasename)) {
					Iterator<Cell> celldata = datarows.cellIterator();
					while(celldata.hasNext()) {
						Cell cd = celldata.next();
						String testdata = null;
						CellType datatype = cd.getCellType();
						if(datatype.toString().equals("STRING")) {
							testdata = cd.getStringCellValue();
						} else if (datatype.toString().equals("NUMERIC")) { 
							double inttestdata = cd.getNumericCellValue();
							testdata = String.valueOf(inttestdata);
						} else if (datatype.toString().equals("FORMULA")) {
							testdata = cd.getCellFormula();
					}
						dataArrayList.add(testdata);
					}
					break;
			}
		}
			if(!rows.hasNext()) {
				System.out.println("Desired " +tc_name+ "was not found in " +sheetname+ " of file");
			}
			break;
		} else {
		System.out.println("Desired " +sheetname+ " was not found in " +filename+ ".xlsx");
		}	
		}
	wb.close();
	return dataArrayList;
	}
}
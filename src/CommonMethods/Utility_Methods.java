package CommonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility_Methods {

	public static File createFolder(String foldername) {
		String RestAssuredFolder = System.getProperty("user.dir");
		System.out.println(RestAssuredFolder);
		
		File folder = new File(RestAssuredFolder+ "\\ApiLogs\\" +foldername);
		
		if(folder.exists()) {
			System.out.println(folder+ " folder already exists : " +RestAssuredFolder);
		}else {
			System.out.println(folder+ " Folder does not exists : " +RestAssuredFolder);
			folder.mkdir();
			System.out.println(folder+ " Creating new folder : " +RestAssuredFolder);
			}
		return folder;
	}
	
	public static void CreateLogFile (String filename,
			File filelocation,
			String endpoint,
			String requestBody,
			String responseHeader,
			String responseBody) throws IOException
	{
		File logfile = new File(filelocation+ "\\" +filename+ ".txt");
		System.out.println("File created with name : " +logfile.getName());
		
		FileWriter writedata = new FileWriter(logfile);
		writedata.write("Endpoint is :" +endpoint+ "\n\n");
		writedata.write("Request Body is :" +requestBody+ "\n\n");
		writedata.write("Rresponse Header is :" +responseHeader+ "\n\n");
		writedata.write("Response Body is :" +responseBody+ "\n\n");

		writedata.close();
		
	}

	public static ArrayList<String> ReadWriteExcel(String Filename, String Sheetname, String Testcasename) throws IOException {
		ArrayList<String> dataarraylist = new ArrayList<String>();
		
		//Fetch Current Working Directory
		String projectfolder = System.getProperty("user.dir");
		System.out.println(projectfolder);
		
		//Locate Designated file
		FileInputStream fis = new FileInputStream(projectfolder+  "\\Data_File\\" +Filename+".xlsx");
		
		//Open file using XSSFWorkbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		//Count no of sheets
		int countOfSheets = wb.getNumberOfSheets();
		
		for(int i=0; i<countOfSheets; i++) {
			String sheetname = wb.getSheetName(i);
			if(sheetname.equals(Sheetname)) {
				XSSFSheet sheet = wb.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				while(rows.hasNext()) {
					Row datarows = rows.next();
					String tc_name = datarows.getCell(0).getStringCellValue();
					if(tc_name.equals(Testcasename)) {
						Iterator<Cell> celldata = datarows.cellIterator();
						while(celldata.hasNext()) {
							Cell cell = celldata.next();
							String testdata = cell.getStringCellValue();
						}
						break;
					} else {
						System.out.println("Desired" +Testcasename+ "was not found in " +Sheetname+ "of file " +Filename+ ".xlsx");
					}
				}
				break;
			} else {
				
			
		
		System.out.println("Desired " +Sheetname+ "was not found in " +Filename+ ".xlsx");
			}
	}
	wb.close();
	return dataarraylist;
}
}
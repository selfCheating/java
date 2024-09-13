package RequestRepository;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.formula.Formula;

import CommonMethods.ExcelPractice;

public class ExcelRequestRepository {

	public static String Post_DD() throws IOException {
		ArrayList<String> data = ExcelPractice.readDataFromExcel("Input_data", "Post_API","Post_API_TC1");
		String req_body = "{\r\n"
				+ " \"name\" : \"default\",  \r\n"
				+ "  \"job\" : \"default\"    \r\n"
				+ "}";
		try {
		String Key_name = data.get(1);
		String key_name_value = data.get(2);
		String Key_job = data.get(3);
		String key_job_value = data.get(4);
		
		 req_body = "{\r\n"
				+ " \""+Key_name+"\" : \""+key_name_value+"\",  \r\n"
				+ "  \""+Key_job+"\" : \""+key_job_value+"\"    \r\n"
				+ "}";
		
	}catch(IndexOutOfBoundsException e ) {
			System.out.println("Test Case not found please check your data sheet");
		}
		return req_body;
	}
}
			
	


package RequestRepository;

import java.io.IOException;
import java.util.ArrayList;

import CommonMethods.ExcelPractice;
import CommonMethods.Utility_Methods_Practice;
import Environment.Post_Put_Patch_Get_API_ENV;

public class Post_Put_Patch_Get_API_Repo extends Post_Put_Patch_Get_API_ENV {

	public static String Post_API_R1() {
		String req_body = "{\r\n"
				+ " \"name\": \"morpheus\", \r\n"
				+"\"job\" : \"leader\" \r\n"
				+ "}";
		return req_body;
	}
	
	public static String Post_API_ExcelDataDriven() throws IOException {
		ArrayList<String> data  = ExcelPractice.readDataFromExcel("Input_data", "Post_API", "Post_API_TC1");
		
		String requestbody = "{\r\n"
				+ " \"name\" : \"default\", \r\n"
				+"  \"job\" : \"default\"        \r\n"
				+"}";
		
		try {
		String key_name = data.get(1);
		String key_name_value = data.get(2);
		String key_job = data.get(3);
		String key_job_value = data.get(4);
		
		requestbody = "{\r\n"
				+ " \""+key_name+"\" : \""+key_name_value+"\", \r\n"
				+"  \""+key_job+"\" : \""+key_job_value+"\"        \r\n"
				+"}";
		
		
		}	catch(IndexOutOfBoundsException e) {
			System.out.println("Test Case Not found please check your data sheet");
		}
		return requestbody;
	}

	public static String Post_API_R2() {
		String req_body = "{\r\n"
				+ " \"name\": \"albert\", \r\n"
				+"\"job\" : \"manager\" \r\n"
				+ "}";
		return req_body;
		

	}
	public static String Put_API_R1() {
		String req_body = "{\r\n"
				+ " \"name\": \"morpheus\", \r\n"
				+"\"job\" : \"zion resident\" \r\n"
				+ "}";
		return req_body;
		

	}

	public static String Put_API_R2() {
		String req_body = "{\r\n"
				+ " \"name\": \"pranjal\", \r\n"
				+"\"job\" : \"TM\" \r\n"
				+ "}";
		return req_body;
		

	}
	public static String Patch_API_R1() {
		String req_body = "{\r\n"
				+ " \"name\": \"morpheus\", \r\n"
				+"\"job\" : \"zion resident\" \r\n"
				+ "}";
		return req_body;
		

	}

	public static String Patch_API_R2() {
		String req_body = "{\r\n"
				+ " \"name\": \"Ajit\", \r\n"
				+"\"job\" : \"TM\" \r\n"
				+ "}";
		return req_body;
	}
	
	public static String Post_API_Data_Driven() throws IOException  {

		ArrayList<String> data = Utility_Methods_Practice.ReadAndWriteDataExcel("Input_data", "Post_API", "Post_API_TC2");
		
		String Key_name = data.get(1);
		String Key_name_value = data.get(2);
		String Key_job = data.get(3);
		String Key_job_value = data.get(4);
		
		String requestbody = "{\r\n"
				+ " \""+Key_name+"\" : \""+Key_name_value+"\", \r\n"
				+ " \""+Key_job+"\" : \""+Key_job_value+"\" \r\n"
                +"}";
		return requestbody;
				
		
	}
}

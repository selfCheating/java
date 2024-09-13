
package GetScripts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonMethods.API_Trigger;
import CommonMethods.Utility_Methods;
import Environment.Post_Put_Patch_Get_API_ENV;
import POJO.GET_API_Parent_POJO;
import POJO.Get_API_Data_POJO;
import POJO.Get_API_Support_POJO;
import io.restassured.response.Response;

public class Get_API_POJO extends Post_Put_Patch_Get_API_ENV {
          File folder;
          Response response;
          
	@BeforeTest
	public void setUp() {
		folder = Utility_Methods.createFolder("Post_API_DataProvider_sameClass");
}
	
	@Test(description = "Validate Get API Test Case with POJO")
 public void ValidateResponse() {
	 response = API_Trigger.Trigger_Get_API(Get_APIEnv());
	 int statuscode = response.getStatusCode();
	 Assert.assertEquals(statuscode, 200, "Desired status code not found ");
	 GET_API_Parent_POJO responseBody = response.as(GET_API_Parent_POJO.class);
	 
	 int res_page = responseBody.getPage();
	 System.out.println(res_page);
	 int res_per_page = responseBody.getPer_page();
	 System.out.println(res_per_page);
	 int res_total = responseBody.getTotal();
	 System.out.println(res_total);
	 int res_total_pages = responseBody.getTotal_pages();
	 System.out.println(res_total_pages);
	 
	 List<Get_API_Data_POJO> res_data = responseBody.getData();
	 
	 for(Get_API_Data_POJO res_data_param : res_data) {
		 int res_data_id = res_data_param.getId(); 
		 System.out.println(res_data_id);
		 String res_data_firstname = res_data_param.getFirst_name(); 
		 System.out.println(res_data_firstname);
		 String res_data_lastname = res_data_param.getLast_name();
		 System.out.println(res_data_lastname);
		 String res_data_email = res_data_param.getEmail();
		 System.out.println(res_data_email);
		 String res_data_avatar = res_data_param.getAvatar();
		 System.out.println(res_data_avatar);

	 }
		 Get_API_Support_POJO res_support = responseBody.getSupport(); 
		 String res_support_text = res_support.getText();
         System.out.println(res_support_text);
		 String res_support_url = res_support.getUrl();
         System.out.println(res_support_url);

		 
	}

	public void logfile() throws IOException {
		Utility_Methods.CreateLogFile("GET_API_TC_POJO", folder, Get_APIEnv(), "GET API hence request body not needed", response.getHeader("Date") , response.getBody().asString());
	}
}

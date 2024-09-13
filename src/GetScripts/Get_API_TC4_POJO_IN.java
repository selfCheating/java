package GetScripts;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommonMethods.API_Trigger_Post_Get_API;
import POJO.Get_Data_POJO;
import POJO.Get_POJO;
import POJO.Get_Support_POJO;
import io.restassured.response.Response;

public class Get_API_TC4_POJO_IN extends API_Trigger_Post_Get_API  {
	Get_POJO res_body;
	
	@Test(description = "Validate Get API Test Case with POJO")
	public void validateResponseStatusCode() {
		Response response = API_Trigger_Post_Get_API.Trigger_Get_API(Get_Endpoint());
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200, "Desired status code not found");
		res_body = response.as(Get_POJO.class);
	}
		public void ValidateparentJson() {
		int res_page = res_body.getPage();
		System.out.println(res_page);
		int res_per_page = res_body.getPer_page();
		System.out.println(res_per_page);
		int res_total = res_body.getTotal();
		System.out.println(res_total);
		int res_total_pages = res_body.getTotal_pages();
		System.out.println(res_total_pages);
		}
		
		public void validateDataArray() {
		List<Get_Data_POJO> res_data = res_body.getData();
		for(Get_Data_POJO res_data_param : res_data) {
			int res_id = res_data_param.getId();
			System.out.println(res_id);
			String res_email = res_data_param.getEmail();
			System.out.println(res_email);
			String res_first_name = res_data_param.getFirst_name();
			System.out.println(res_first_name);
			String res_last_name = res_data_param.getLast_name();
			System.out.println(res_last_name);
			String res_avatar = res_data_param.getAvatar();
			System.out.println(res_avatar);
			
			Get_Support_POJO res_support = res_body.getSupport();
			String res_url = res_support.getUrl();
			System.out.println(res_url);
			String res_text = res_support.getText();		
			System.out.println(res_text);

		}
		
		}
		
	}
		
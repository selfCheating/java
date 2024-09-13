package Post_Scripts;

import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommonMethods.API_Trigger;
import CommonMethods.TestRetryAnalyzer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class PostScript_DataProvider_diffClass_Practice extends API_Trigger  {

	String requestbody;
	Response response;
	ResponseBody res_body;
	
	@Test(dataProviderClass = Environment.Post_API_DataProvider_Practice.class, dataProvider = "requestData", 
			retryAnalyzer = TestRetryAnalyzer.class, description = "Validate Response Status Code ")
	public void validateResponseStatusCode(String Req_name, String Req_job) {
		 requestbody = "{\r\n"
				+"  \"name\" : \""+Req_name+"\", \r\n"
				+ "  \"job\" : \""+Req_job+"\" \r\n"
				+ "}";                               
     response = API_Trigger.Trigger_Post_API("requestbody","Post_APIENV()");
     res_body = response.getBody();
     System.out.println(res_body.asPrettyString());
     int statuscode = response.getStatusCode();
     Assert.assertEquals(statuscode, 201);
     if(statuscode==201) {
    	 ValidateResponseBody(Req_name, Req_job);
     }
	}
	public void ValidateResponseBody(String Req_name, String Req_job) {
		String res_id = res_body.jsonPath().getString("id");
		String res_name = res_body.jsonPath().getString("name");
		String res_job = res_body.jsonPath().getString("job");
		String res_createdAt = res_body.jsonPath().getString("createdAt");
		res_createdAt = res_createdAt.toString().substring(0, 11);
		
		JsonPath jspreq = new JsonPath(requestbody);
		String req_name = jspreq.getString("name");
		String req_job = jspreq.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate =  currentdate.toString().substring(0, 11);
		
		Assert.assertEquals(req_name, res_name);
		Assert.assertEquals(req_job, res_job);
		Assert.assertEquals(res_createdAt, expecteddate);
		Assert.assertNotNull(res_id);
		
	}
	}



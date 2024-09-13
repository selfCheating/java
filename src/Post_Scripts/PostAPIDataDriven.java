package Post_Scripts;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import CommonMethods.API_Trigger;
import CommonMethods.TestRetryAnalyzer;
import CommonMethods.Utility_Methods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class PostAPIDataDriven extends API_Trigger {

	File folder;
	String requestBody;
	ResponseBody res_body;
	Response response;

	@BeforeClass
	public void setUp() {
		folder = Utility_Methods.createFolder("Post_API_DataDriven");
	}

	@Parameters({ "Req_name", "Req_job" })
	@Test(retryAnalyzer = TestRetryAnalyzer.class, description = "Validate response body for POST API")
	public void validateReponseStatusCode(String Req_name, String Req_job) {
		requestBody = "{\r\n" 
	+ " \"name\" : \" " + Req_name + " \" , \r\n" 
	+ " \"job\" :  \" " + Req_job + " \"      \r\n" 
	+ "}";

		response = API_Trigger.Trigger_Post_API(requestBody, Post_APIENV());
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(201, statuscode);
		res_body = response.getBody();
		System.out.println(res_body.asPrettyString());
		
	}

	@Test(dependsOnMethods = {"validateReponseStatusCode"}, description = "validate response body for post api")
	public void validateResponseBody() {

		String res_name = res_body.jsonPath().getString("name");
		String res_job = res_body.jsonPath().getString("job");
		String res_id = res_body.jsonPath().getString("id");
		String res_createdAt = res_body.jsonPath().getString("createdAt");
		res_createdAt = res_createdAt.toString().substring(0, 8);

		JsonPath jspreq = new JsonPath(requestBody);
		String req_name = jspreq.getString("name");
		String req_job = jspreq.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 8);

		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_createdAt, expecteddate);
		Assert.assertNotNull(res_id);
	}
	@AfterClass
            public void Logfile() throws IOException{
  Utility_Methods.CreateLogFile("PostAPIDataDriven", folder, Post_APIENV(), requestBody, response.getHeaders().toString() ,res_body.asString());	
}
}

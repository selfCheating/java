package Post_Scripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import CommonMethods.API_Trigger;
import CommonMethods.TestRetryAnalyzer;
import CommonMethods.Utility_Methods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class Post_API_TC_DataDriven2_xml extends API_Trigger {
	File folder;
	Response response;
	String requestBody;
	ResponseBody res_body;
	
	@BeforeClass
	public void newFolder() {
		folder = Utility_Methods.createFolder("Post_API_TC_DataDriven2_xml");
		
	}
	@Parameters({"Req_name1", "Req_job1"})
        @Test(retryAnalyzer = TestRetryAnalyzer.class, description = "Validate response status code for POST_API_TC1")
	public void ValidateResponseStatusCode(String Req_name1, String Req_job1) {
         requestBody =  "{\r\n"
        		 +" \"name\" : \""+Req_name1+"\", \r\n"
        		 +" \"job\" : \""+Req_job1+"\" \r\n"
        		 + "}";
         
		response = API_Trigger.Trigger_Post_API(requestBody, Post_APIENV());
		res_body = response.getBody();
		System.out.println("ResponseBody :" +res_body.asPrettyString());
		int statuscode = response.statusCode();
		Assert.assertEquals(statuscode, 201,"Desired status code not found");
}
		@Test(dependsOnMethods = {"ValidateResponseStatusCode"}, description = "Validate response status code for POST_API_TC1" )
	public void validateResponseBody() {
        String res_name = res_body.jsonPath().getString("name");	
        String res_job = res_body.jsonPath().getString("job");
        String res_id = res_body.jsonPath().getString("id");
        String res_createdAt = res_body.jsonPath().getString("createdAt");
        res_createdAt = res_createdAt.toString().substring(0, 8);
        
        
        JsonPath jsp_req = new JsonPath(requestBody);
        String req_name = jsp_req.getString("name");
        String req_job = jsp_req.getString("job");
        LocalDateTime currentdate = LocalDateTime.now();
        String expecteddate = currentdate.toString().substring(0,8);
        
        Assert.assertEquals(res_name, req_name, "Name in Response does not match with name in request");
        Assert.assertEquals(res_job, req_job, "Job in response does not match with job in request");
        Assert.assertNotNull(res_id, "Id found to be null");
        Assert.assertEquals(res_createdAt, expecteddate,"date in response does not match with date in request");
		}
		
		@AfterClass
        public void LogFile() throws IOException {
        	Utility_Methods.CreateLogFile("Post_API_TC1", folder, Post_APIENV(),requestBody, response.getHeaders().toString(), res_body.asString());
     
        }
	}


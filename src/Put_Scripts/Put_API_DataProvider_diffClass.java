package Put_Scripts;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
import Environment.TestNG_Data_Provider_PUT_API;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class Put_API_DataProvider_diffClass extends API_Trigger {
	File folder;
	Response response;
	String requestBody;
	ResponseBody res_body;

	
	@BeforeTest
	public void newFolder() {
		folder = Utility_Methods.createFolder("Put_API_DataProvider_DiffClass");
		
	}
        @Test(dataProviderClass = TestNG_Data_Provider_PUT_API.class,dataProvider = "requestData", retryAnalyzer = TestRetryAnalyzer.class, description = "Validate response status code for Put_API_TC1")
	public void ValidateResponseStatusCode(String Req_name, String Req_job) {
        	requestBody =  "{\r\n"
           		 +" \"name\" : \""+Req_name+"\", \r\n"
           		 +" \"job\" : \""+Req_job+"\" \r\n"
           		 + "}";
        	
		response = API_Trigger.Trigger_Put_API(requestBody, Put_APIEnv());
		res_body = response.getBody();
		System.out.println("ResponseBody :" +res_body.asPrettyString());
		int statuscode = response.statusCode();
		Assert.assertEquals(statuscode, 200,"Desired status code not found");
		if (statuscode == 201) {
			validateResponseBody(Req_name, Req_job);
		}
}
	public void validateResponseBody(String Req_name, String Req_job) {
        String res_name = res_body.jsonPath().getString("name");	
        String res_job = res_body.jsonPath().getString("job");
        String res_upatedAt = res_body.jsonPath().getString("updatedAt");
        res_upatedAt = res_upatedAt.toString().substring(0, 7);
        
        
        JsonPath jsp_req = new JsonPath(requestBody);
        String req_name = jsp_req.getString("name");
        String req_job = jsp_req.getString("job");
        LocalDateTime currentdate = LocalDateTime.now();
        String expecteddate = currentdate.toString().substring(0,7);
        
        Assert.assertEquals(res_name, req_name, "Name in Response does not match with name in request");
        Assert.assertEquals(res_job, req_job, "Job in response does not match with job in request");
        Assert.assertEquals(res_upatedAt, expecteddate,"date in response does not match with date in request");
		}
		
		@AfterTest
        public void LogFile() throws IOException {
        	Utility_Methods.CreateLogFile("Put_API_TC1", folder, Put_APIEnv(), requestBody, response.getHeaders().toString(), res_body.asString());
     
        }
        		

        
        

	}


package PatchScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.annotations.BeforeClass;
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
import POJO.PATCH_API_POJO;
import POJO.PUT_API_POJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TestScripts_Patch_API_POJO extends API_Trigger {

	File folder;
	Response response;
	ObjectMapper objMapper;
	String Requestbody;
	PATCH_API_POJO PojoObject;
	PATCH_API_POJO res_body;

	@BeforeClass
	public void newFolder() throws JsonProcessingException {
		folder = Utility_Methods.createFolder("Patch_API_TC1");
		objMapper = new ObjectMapper();
		PojoObject = new PATCH_API_POJO();
		PojoObject.setName("morpheus");
		PojoObject.setJob("zion resident");
		Requestbody = objMapper.writeValueAsString(PojoObject);
	}

	@Test(retryAnalyzer = TestRetryAnalyzer.class, description = "Validate response status code for Patch_API_TC1")
	public void ValidateResponseStatusCode() {
		response = API_Trigger.Trigger_Patch_API(Requestbody, Patch_APIEnv());
		System.out.println(response.getBody().asString());
		res_body = response.as(PATCH_API_POJO.class);
		int statuscode = response.statusCode();
		Assert.assertEquals(statuscode, 200, "Desired status code not found");
	}

	@Test(dependsOnMethods = { "ValidateResponseStatusCode" }, description = "Validate response body for Patch_API_TC1")
	public void validateResponseBody() {
		String res_name = res_body.getName();
		String res_job = res_body.getJob();
		String res_updatedAt = res_body.getUpdatedAt();
		res_updatedAt = res_updatedAt.toString().substring(0, 8);

		JsonPath jsp_req = new JsonPath(Patch_API_R1());
		String req_name = jsp_req.getString("name");
		String req_job = jsp_req.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 8);

		Assert.assertEquals(res_name, req_name, "Name in Response does not match with name in request");
		Assert.assertEquals(res_job, req_job, "Job in response does not match with job in request");
		Assert.assertEquals(res_updatedAt, expecteddate, "date in response does not match with date in request");
	}

	@AfterClass
	public void LogFile() throws IOException {
		Utility_Methods.CreateLogFile("Patch_API_TC1", folder, Requestbody, Patch_API_R1(),
				response.getHeaders().toString(), response.getBody().asString());

	}

}

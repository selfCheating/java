package Post_Scripts;

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
import POJO.POST_API_POJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TestScript_PostAPIPOJO extends API_Trigger {
	File folder;
	Response response;
	POST_API_POJO res_body;
	String Requestbody;
	ObjectMapper ObjMapper;
	POST_API_POJO PojoObject;

	@BeforeClass
	public void newFolder() throws JsonProcessingException {
		folder = Utility_Methods.createFolder("Post_API_TC1");
		ObjMapper = new ObjectMapper();
		PojoObject = new POST_API_POJO();
		PojoObject.setName("morpheus");
		PojoObject.setJob("leader");
		Requestbody = ObjMapper.writeValueAsString(PojoObject);

	}

	@Test(retryAnalyzer = TestRetryAnalyzer.class, description = "Validate response status code for POST_API_TC1", groups = {"statuscode"})
	public void ValidateResponseStatusCode() {
		response = API_Trigger.Trigger_Post_API(Requestbody, Post_APIENV());
		System.out.println(response.getBody().asString());
		res_body = response.as(POST_API_POJO.class);
		int statuscode = response.statusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 201, "Desired status code not found");
	}

	@Test(dependsOnMethods = { "ValidateResponseStatusCode" }, description = "Validate response body for POST_API_TC1")
	public void validateResponseBody() {
		String res_name = res_body.getName();
		String res_job = res_body.getJob();
		String res_id = res_body.getId();
		String res_createdAt = res_body.getCreatedAt();
		res_createdAt = res_createdAt.toString().substring(0, 8);

		JsonPath jsp_req = new JsonPath(Requestbody);
		String req_name = jsp_req.getString("name");
		String req_job = jsp_req.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 8);

		Assert.assertEquals(res_name, req_name, "Name in Response does not match with name in request");
		Assert.assertEquals(res_job, req_job, "Job in response does not match with job in request");
		Assert.assertNotNull(res_id, "Id found to be null");
		Assert.assertEquals(res_createdAt, expecteddate, "date in response does not match with date in request");
	}

	@AfterClass
	public void LogFile() throws IOException {
		Utility_Methods.CreateLogFile("Post_API_TC1", folder, Post_APIENV(), Requestbody,
				response.getHeaders().toString(), response.getBody().asString());

	}

}

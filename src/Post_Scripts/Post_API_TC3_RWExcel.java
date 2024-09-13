package Post_Scripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Post_API_TC3_RWExcel extends API_Trigger {
	File folder;
	Response response;
	ResponseBody res_body;

	@BeforeClass
	public void newFolder() {
		folder = Utility_Methods.createFolder("Post_API_TC3_R_W_Excel");

	}

	@Test(retryAnalyzer = TestRetryAnalyzer.class, description = "Validate response status code for POST_API_TC1", groups = {"statuscode"})
	public void ValidateResponseStatusCode() throws IOException {
		response = API_Trigger.Trigger_Post_API(Post_API_Data_Driven(), Post_APIENV());
		res_body = response.getBody();
		System.out.println("ResponseBody :" + res_body.asPrettyString());
		int statuscode = response.statusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 201, "Desired status code not found");
	}

	@Test(dependsOnMethods = { "ValidateResponseStatusCode" }, description = "Validate response body for POST_API_TC1")
	public void validateResponseBody() throws IOException {
		String res_name = res_body.jsonPath().getString("name");
		String res_job = res_body.jsonPath().getString("job");
		String res_id = res_body.jsonPath().getString("id");
		String res_createdAt = res_body.jsonPath().getString("createdAt");
		res_createdAt = res_createdAt.toString().substring(0, 8);

		JsonPath jsp_req = new JsonPath(Post_API_Data_Driven());
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
		Utility_Methods.CreateLogFile("Post_API_TC3_R_W_Excel", folder, Post_APIENV(), Post_API_Data_Driven(),
				response.getHeaders().toString(), res_body.asString());

	}

}

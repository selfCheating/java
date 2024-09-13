package GetScripts;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import CommonMethods.API_Trigger;
import CommonMethods.TestRetryAnalyzer;
import CommonMethods.Utility_Methods;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Get_API_TC1 extends API_Trigger {
	
	File folder;
	Response response;
	ResponseBody res_body;

	@BeforeClass
	public void setUp() {
	 folder = Utility_Methods.createFolder("GET API");
	}
	
	@Test(retryAnalyzer = TestRetryAnalyzer.class, description = "validate and extract status code for Get API TC1")
	public void ExtrctAndValidateResponseStatusCode() {
		response = API_Trigger.Trigger_Get_API(Get_APIEnv());
		
		int statuscode = response.getStatusCode();
        System.out.println("Status code for the method is : " +statuscode);
        Assert.assertEquals(200, statuscode, "Desired Status code not found");

        res_body = response.getBody();
        System.out.println(res_body.asPrettyString());
                
        
	}
	
	@Test(dependsOnMethods = {"ExtrctAndValidateResponseStatusCode"} , description = "validate response body for GET_API_TC1")
	public void validateResponseBody() {
    int exp_page = 2;
    int exp_per_page = 6;
    int exp_total= 12;
    int exp_total_pages = 2;
	int[] exp_id = {7,8,9,10,11,12};
	String[] exp_email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in","tobias.funke@reqres.in", 
			"byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
	String[] exp_first_name = {"Michael", "Lindsay","Tobias","Byron","George", "Rachel" };
	String[] exp_last_name = {"Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"};
	String[] exp_avatar = {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg",
			"https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg",
			"https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
	String exp_support_url = "https://reqres.in/#support-heading";
	String exp_support_text = "To keep ReqRes free, contributions towards server costs are appreciated!";
	
	int res_page = res_body.jsonPath().getInt("page"); 
	int res_per_page = res_body.jsonPath().getInt("per_page");
	int res_total = res_body.jsonPath().getInt("total");
	int res_total_pages = res_body.jsonPath().getInt("total_pages");
	
	Assert.assertEquals(exp_page, res_page);
	Assert.assertEquals(exp_per_page, res_per_page);
	Assert.assertEquals(exp_total,res_total);
	Assert.assertEquals(exp_total_pages, res_total_pages);
	
	java.util.List<Object> dataArray = res_body.jsonPath().getList("data");
	
	for (int i=0; i<dataArray.size(); i++) {
		Assert.assertEquals(res_body.jsonPath().getInt("data[" + i + "].id"), exp_id[i]);
		Assert.assertEquals(res_body.jsonPath().getString("data[" + i + "].email"), exp_email[i]);
		Assert.assertEquals(res_body.jsonPath().getString("data[" +i+"].first_name"), exp_first_name[i]);
		Assert.assertEquals(res_body.jsonPath().getString("data[" +i+"].last_name"), exp_last_name[i]);
		Assert.assertEquals(res_body.jsonPath().getString("data[" + i + "].avatar"), exp_avatar[i]);
		
		String res_support_url = "https://reqres.in/#support-heading";
		String res_support_text = "To keep ReqRes free, contributions towards server costs are appreciated!";
		
		Assert.assertEquals(exp_support_url, res_support_url);
		Assert.assertEquals(exp_support_text, res_support_text);

	}
	}
	
	@AfterClass
	public void createLog() throws IOException {
	Utility_Methods.CreateLogFile("Get_API_TC1", folder, Get_APIEnv(), "",  response.getHeaders().toString(), res_body.asString());
	}
}

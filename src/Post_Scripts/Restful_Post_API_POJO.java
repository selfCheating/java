package Post_Scripts;

import java.io.File;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import CommonMethods.API_Trigger_Post_Get_API;
import CommonMethods.TestRetryAnalyzer;
import CommonMethods.createFoldertoSaveLogEvidence;
import POJO.Restful_PostAPI_POJO;
import POJO.Restful_Post_API_DATA_POJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Restful_Post_API_POJO extends API_Trigger_Post_Get_API {
	String requestbody;
	Restful_PostAPI_POJO res_body;
	File folder;

	@BeforeClass
	public void setUp() {
		folder = createFoldertoSaveLogEvidence.createFolder("Restful_Post_API");
	}

	@Test(retryAnalyzer = TestRetryAnalyzer.class, description = "Post API POJO validate status code")
	public void validatePostResponseStatuscode() throws JsonProcessingException {
		Restful_Post_API_DATA_POJO pojoObject = new Restful_Post_API_DATA_POJO();
		pojoObject.setPrice(1849.99);
		pojoObject.setYear(2019);
		pojoObject.setCpuModel("Intel Core i9");
		pojoObject.setHardDiskSize("1 TB");
		Restful_PostAPI_POJO PojoObject = new Restful_PostAPI_POJO();
		PojoObject.setName("Apple MacBook Pro 16");
		PojoObject.setData(pojoObject);
		ObjectMapper objMapper = new ObjectMapper();
		requestbody = objMapper.writeValueAsString(PojoObject);
		Response response = API_Trigger_Post_Get_API.Post_API_POJO(requestbody, Post_endpoint());
		int statuscode = response.getStatusCode();
		Assert.assertEquals(200, statuscode);
		System.out.println(statuscode);
		System.out.println(response.getBody().asPrettyString());
		res_body = response.as(Restful_PostAPI_POJO.class);
	}

	@Test(dependsOnMethods = "validatePostResponseStatuscode", description = "Validate response body parameters ")
	public void valiadatePostResponseParams() {
		JsonPath jspreq = new JsonPath(requestbody);
		String req_name = jspreq.getString("name");
		System.out.println(req_name);
		int req_year = jspreq.getInt("data.year");
		System.out.println(req_year);
		double req_price = jspreq.getDouble("data.price");
		System.out.println(req_price);
		String req_CpuModel = jspreq.getString("data.'CPU model'");
		System.out.println(req_CpuModel);
		String req_Hard_disk_size = jspreq.getString("data.'Hard disk size'");
		System.out.println(req_Hard_disk_size);
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 11);
		System.out.println(expecteddate);

		String res_id = res_body.getId();
		System.out.println(res_id);
		String res_name = res_body.getName();
		System.out.println(res_name);

		Restful_Post_API_DATA_POJO resdata = res_body.getData();
		double res_price = resdata.getPrice();
		System.out.println(res_price);

		int res_year = resdata.getYear();
		System.out.println(res_year);

		String res_cpumodel = resdata.getCpuModel();
		System.out.println(res_cpumodel);

		String res_HDD = resdata.getHardDiskSize();
		System.out.println(res_HDD);

		String res_createdAt = res_body.getCreatedAt();
		res_createdAt = res_createdAt.toString().substring(0, 11);
		System.out.println(res_createdAt);

		Assert.assertEquals(req_name, res_name);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(req_year, res_year);
		Assert.assertEquals(req_price, res_price);
		Assert.assertEquals(req_CpuModel, res_cpumodel);
		Assert.assertEquals(req_Hard_disk_size, res_HDD);
		Assert.assertEquals(expecteddate, res_createdAt);

	}
}

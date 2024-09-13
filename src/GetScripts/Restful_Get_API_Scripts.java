package GetScripts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import CommonMethods.API_Trigger_Post_Get_API;
import CommonMethods.createFoldertoSaveLogEvidence;
import POJO.RestFul_Get_API_Data_POJO;
import POJO.Restful_Get_API_Parent_POJO_Pract;
import io.restassured.response.Response;

public class Restful_Get_API_Scripts extends API_Trigger_Post_Get_API {
	Restful_Get_API_Parent_POJO_Pract[] res_body;
	File folder;
	Response response;

	public void setUp() {
		folder = createFoldertoSaveLogEvidence.createFolder("Restful_Get_API");
	}

	public void validateResponseStatusCode() {
		response = API_Trigger_Post_Get_API.Trigger_Get_API(Get_Endpoint());
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200, "Desired statuscode not found");
	}

	public static void validateResponseBodyParam() throws JsonMappingException, JsonProcessingException {
		String GetResponsebody = "[\r\n"
				+ "   {\r\n"
				+ "      \"id\": \"1\",\r\n"
				+ "      \"name\": \"Google Pixel 6 Pro\",\r\n"
				+ "      \"data\": {\r\n"
				+ "         \"color\": \"Cloudy White\",\r\n"
				+ "         \"capacity\": \"128 GB\"\r\n"
				+ "      }\r\n"
				+ "   },";

		ObjectMapper objectMapper = new ObjectMapper();

		RestFul_Get_API_Data_POJO data = objectMapper.readValue(GetResponsebody, RestFul_Get_API_Data_POJO.class);
		System.out.println("Color: " + data.getColorjson());
		System.out.println("Capacity: " + data.getCapacity());

	}

	public void logfile() throws IOException {
		createFoldertoSaveLogEvidence.createLogfile("Restful_GetAPI_Logfile", folder, "Get_Endpoint()",
				"Get API so no Request Body", response.getBody().asPrettyString());
	}
}

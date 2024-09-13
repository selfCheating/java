package CommonMethods;

import RequestRepository.Post_Put_Patch_Get_API_Repo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Trigger extends Post_Put_Patch_Get_API_Repo {
	static String headername = "Content-Type";
	static String headervalue = "application/json";
	
public static Response Trigger_Post_API(String req_body, String endPoint) {
			
		RequestSpecification req_spec = RestAssured.given();
		req_spec.header(headername,headervalue);
		req_spec.body(req_body);
		Response response = req_spec.post(endPoint);
		return response;
		}

public static Response Trigger_Put_API(String req_body , String endPoint) {

	RequestSpecification req_spec = RestAssured.given();
	req_spec.header(headername, headervalue);
	req_spec.body(req_body);
	Response response = req_spec.put(endPoint);
	return response;
}

public static Response Trigger_Patch_API(String req_body, String endPoint) {
	RequestSpecification req_spec= RestAssured.given();
	req_spec.header(headername, headervalue);
	req_spec.body(req_body);
	Response response = req_spec.patch(endPoint);
	return response;
	
}

public static Response Trigger_Get_API(String endPoint) {
	RequestSpecification req_spec = RestAssured.given();
	req_spec.header(headername,headervalue);
	Response response = req_spec.get(endPoint);
	return response;
}
}


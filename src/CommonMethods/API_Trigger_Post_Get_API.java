package CommonMethods;

import Environment.Get_Post_POJO_Env;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Trigger_Post_Get_API extends Get_Post_POJO_Env {

	public static Response Trigger_Get_API(String endPoint) {
		RequestSpecification req_spec = RestAssured.given();
		req_spec.header("Content-Type", "application/json");
		Response response = req_spec.get(endPoint);
		int statuscode =  response.getStatusCode();
		System.out.println(statuscode);
	return response;
	}
	
	public static Response Post_API_POJO(String req_body, String endPoint) {
		RequestSpecification req_spec = RestAssured.given();
		req_spec.header("Content-Type","application/json");
		req_spec.body(req_body);
		Response response = req_spec.post(endPoint);
		return response;
	}
}

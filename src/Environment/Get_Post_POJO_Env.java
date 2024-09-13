package Environment;

public class Get_Post_POJO_Env {
	public static String Get_Endpoint() {
		String hostname = "https://api.restful-api.dev";
		String resource = "/objects/7";
		String endpoint = hostname + resource;
		return endpoint;
	}

	public static String Post_endpoint() {
		String hostname = "https://api.restful-api.dev/";
		String resource = "objects";
		String endpoint = hostname + resource;
		return endpoint;
	}
}

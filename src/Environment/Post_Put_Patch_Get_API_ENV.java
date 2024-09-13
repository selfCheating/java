package Environment;

public class Post_Put_Patch_Get_API_ENV {

	public static String Post_APIENV() {
		String hostname = "https://reqres.in/";
		String resource = "api/users";
				String endpoint = hostname+resource;
		return endpoint;

	}

	public static String Get_APIEnv() {
		String hostname = "https://reqres.in/";
		String resource = "api/users?page=2";
		String endpoint = hostname + resource;
		return endpoint;

    }
	public static String Put_APIEnv() {
		String hostname = "https://reqres.in";
		String resource = "/api/users/2";
		String endpoint = hostname+resource;
		return endpoint;
	}
	
    public static String Patch_APIEnv() {
	String hostname = "https://reqres.in";
	String resource = "/api/users/2";
	String endpoint = hostname+resource;
	return endpoint;
    }

}

package apiEndPoints;

public class Routes {
	
	public static String baseURL = "https://petstore.swagger.io/v2/";
	
	public static String getURL = baseURL + "user/{username}"; 

	public static String logsURL = baseURL + "user/login";
	
	public static String deleteURL = baseURL + "user/{username}";
	
	public static String updateURL = baseURL + "user/{username}";
	
	public static String postURL = baseURL + "user";
	
	public static String postURlWithList = "https://petstore.swagger.io/v2/user/createUsersWithArrayInput";

}

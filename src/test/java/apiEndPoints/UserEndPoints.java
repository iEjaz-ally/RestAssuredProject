package apiEndPoints;

import org.testng.annotations.Test;

import apiPayloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class UserEndPoints {

	public static Response createuser(User payload) {
		Response res = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
							.when().post(Routes.postURL);
		
		return res;
	}
	public static Response readUser(String arg1) {
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("username", arg1)
							.when()
								.get(Routes.getURL);
		
		return response;
	}
	public static Response updateUser(String userName, User payload) {
		
		Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.pathParam("username", userName)
						.body(payload)
						.when()
						.put(Routes.updateURL);
		
		return res;

	}
	
public static Response deleteUser(String userName) {
		
		Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.pathParam("username", userName)
						.when()
						.delete(Routes.deleteURL);
		
		return res;

	}
public static Response createuserWithList(ArrayList<Map<Object, Object>> arr) {
	Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(arr)
				.when().post(Routes.postURL);
	
	return res;
}
public static Response logUser(String username, String password) {
	Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.queryParam("username", username)
							.queryParam("password", password)
						.when()
							.get(Routes.logsURL);
	return response;
	}
}


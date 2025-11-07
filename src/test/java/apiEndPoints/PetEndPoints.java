package apiEndPoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ResourceBundle;

import apiPayloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {

	static ResourceBundle getURL() {
		ResourceBundle routesBundle = ResourceBundle.getBundle("Routes");
		return routesBundle;
	}
	public static Response uploadImage(int petID, File image) {
		
	String urlString=	getURL().getString("postImageURL");
		Response res = given()
							.contentType("multipart/form-data")
							.accept(ContentType.JSON)
							.pathParam("petId", petID)
							.multiPart("file", image)
							.when().post(urlString);
		
		return res;
	}
	public static Response readUser(String arg1) {
		String urlString=	getURL().getString("getURL");
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("username", arg1)
							.when()
								.get(urlString);
		
		return response;
	}

public static Response deleteUser(String userName) {
	String urlString=	getURL().getString("deleteURL");
		Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.pathParam("username", userName)
						.when()
						.delete(urlString);
		
		return res;

	}
}

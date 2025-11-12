package apiEndPoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;
import java.util.ResourceBundle;

import apiPayloads.PetPayload;
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

	public static Response readPetByAvailability(String arg1) {
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.queryParam("status", arg1)
							.when()
								.get(getURL().getString("getURLByStatus"));
		
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
/*public static Response createNewPet(Map<String, Object> data) {
	String urlString = getURL().getString("postNewPet");
	Response response = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(data)
						.when()
							.post(urlString);
	
	return response;
	}*/
public static Response createNewPet(PetPayload petPayload) {
	String urlString = getURL().getString("postNewPet");
	Response response = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(petPayload)
						.when()
							.post(urlString);
	
	return response;
	}
public static Response updatePet(int id, PetPayload petPayload) {
	Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.queryParam("id", id)
							.body(petPayload)
							.when().put(getURL().getString("updateURL"));
	return response;
							
}
public static Response readPetByID(int ID) {
	Response response = given()
							.accept(ContentType.JSON)
							.pathParam("petId", ID)
							.when()
								.get(getURL().getString("getURLByPetID"));
	
	return response;

	}
public static Response deletePetByID(int ID) {
	Response response = given()
							.accept(ContentType.JSON)
							.queryParam("api_key", 123)
							.pathParam("petId", ID)
							.when()
								.get(getURL().getString("deleteURLByPetID"));
	
	return response;

	}
public static Response updateAPetById(int petID, String updatedName, String updatedStatus) {
	Response response = given().
								contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("petId", petID)
								.formParam("name", updatedName)
								.formParam("status", updatedStatus)
								.when()
									.post(getURL().getString("updatePetById"));
	
	return response;
								
	}
}
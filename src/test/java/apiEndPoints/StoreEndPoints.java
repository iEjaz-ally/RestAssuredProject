package apiEndPoints;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.nio.channels.AcceptPendingException;
import java.util.Map;
import java.util.ResourceBundle;



import apiPayloads.StorePayLoad;
import apiPayloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class StoreEndPoints {

	public static ResourceBundle getULR() {
		
		ResourceBundle bundle = ResourceBundle.getBundle("StoreRoutes");
		return bundle;
		
	}
	public static Response postOrder(StorePayLoad payload) {
		
		Response response = given().
								contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
								.when()
								.post(getULR().getString("postURL"));
		
		return response;
	}
	public static Response getInventory() {
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.when()
									.get(getULR().getString("getURL"));
		
		return response;
	}
	public static Response getOrderByID(int ID) {
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("orderId", ID)
							.when()
								.get(getULR().getString("getURLByID"));
		
		return response;
	}
	public static Response deleteOrder(int ID) {
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("orderId", ID)
							.when()
								.delete(getULR().getString("deleteURLByID"));
		
		return response;
	}
}

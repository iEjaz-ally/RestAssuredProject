package apiTest;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;



import apiEndPoints.StoreEndPoints;
import apiPayloads.StorePayLoad;
import apiUtilities.DataProviders;
import io.restassured.response.Response;

public class StoreTestCase {

	StorePayLoad payLoad = new StorePayLoad();
	
	@Test(dataProvider = "StoreDataProvider",dataProviderClass = DataProviders.class)
	public void postOrder(HashMap<String, String> data) {
		String dateAndTime = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
		System.out.println(dateAndTime);
		
		
		System.out.println(data);
		payLoad.setId(Integer.valueOf((data.get("Id"))));
		payLoad.setPetId(Integer.valueOf( data.get("petId")));
		payLoad.setQuantity(Integer.valueOf(data.get("quantity")));
		payLoad.setShipDate(String.valueOf(dateAndTime));
		payLoad.setStatus(( data.get("status")));
		payLoad.setComplete(true);
		
		Response response = StoreEndPoints.postOrder(payLoad);
		response.then().statusCode(200).log().all();
		Assert.assertEquals(response.jsonPath().getInt("petId"), Integer.valueOf(data.get("petId")));
		
	}
	@Test(dependsOnMethods = "postOrder")
	public void getInventory() {
		Response response = StoreEndPoints.getInventory();
		
		response.then().statusCode(200).log().all();
	}
	@Test(dependsOnMethods = "getInventory", dataProvider = "GETORDERID", dataProviderClass = DataProviders.class)
	public void getOrder(String orderId) {
		
		Response response = StoreEndPoints.getOrderByID(Integer.valueOf(orderId));
		
		response.then().statusCode(200).log().all();
	}
	@Test(dependsOnMethods = "getOrder", dataProvider = "GETORDERID", dataProviderClass = DataProviders.class )
	public void deleteOrder(String orderID)
	
	{
		Response response = StoreEndPoints.deleteOrder(Integer.valueOf(orderID));
				
				response.then().statusCode(200).log().all();
	}
}

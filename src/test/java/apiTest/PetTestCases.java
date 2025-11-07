package apiTest;

import java.io.File;

import org.testng.annotations.Test;

import apiEndPoints.PetEndPoints;
import io.restassured.response.Response;

public class PetTestCases {
	
	@Test
	public void uploadImage() {
		File file = new File(System.getProperty("user.dir")+ File.separator + "TestData" + File.separator + "TestImage.jpg");
		Response response = PetEndPoints.uploadImage(123, file);
		response.then().statusCode(200).log().all();
	}
	

}

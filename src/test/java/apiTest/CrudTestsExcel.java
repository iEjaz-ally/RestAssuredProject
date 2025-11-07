package apiTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import apiEndPoints.UserEndPoints;
import apiPayloads.User;
import apiUtilities.DataProviders;
import io.restassured.response.Response;

public class CrudTestsExcel {
	Logger logger = LogManager.getLogger(this.getClass());
	
	
	@Test(dataProvider = "ExcelData", dataProviderClass = DataProviders.class)
	public void postRequest(String userId, String firstName, String lastName, String userEmail, String passwoString, String phoneNUm) {
		
	try {	
		
		logger.info("*****Creating a new user*****");
		User payload = new User();
		
		payload.setId(Integer.valueOf(userId));
		payload.setFirstNameString(firstName);
		payload.setLastNameString(lastName);
		payload.setEmailString(userEmail);
		payload.setPasswoString(passwoString);
		payload.setPhoneNumString(phoneNUm);
		
		Response responseCache = UserEndPoints.createuser(payload);
		
		Assert.assertEquals(responseCache.getStatusCode(), 200);
	}catch (Exception e) {
		// TODO: handle exception
		logger.info("Could not create a new user because of following reason "+e.getMessage());
	}
		
	}
	@Test(dependsOnMethods = "postRequest", dataProvider = "Username", dataProviderClass = DataProviders.class)
	public void deleteUser(String userName) {
		try {
			
		logger.info("*****Deleting user*****");
		Response response = UserEndPoints.deleteUser(userName);
		
		Assert.assertEquals(response.getStatusCode(), 404);
	}catch (Exception e) {
		// TODO: handle exception
		logger.info("Could not delete user because of following reason "+e.getMessage());
	}
	}
}

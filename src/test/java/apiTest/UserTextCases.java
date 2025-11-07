package apiTest;

import apiPayloads.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import apiEndPoints.UserEndPoints;


public class UserTextCases {

	Faker faker;
	 User payLoad;
	Logger logger;
	@BeforeClass
	public void setUP() {
		faker = new Faker();
		
		payLoad = new User();
		payLoad.setId(faker.number().hashCode());
		payLoad.setFirstNameString(faker.name().firstName());
		payLoad.setLastNameString(faker.name().lastName());
		payLoad.setUserNameString(faker.name().username());
		payLoad.setEmailString(faker.internet().safeEmailAddress());
		payLoad.setPasswoString(faker.internet().password());
		payLoad.setPhoneNumString(faker.phoneNumber().cellPhone());
		logger= LogManager.getLogger(this.getClass());
		
	}
	@Test
	public void testPostUser() {
		try {
			logger.info("****Creating a new user****");
			
		Response res = UserEndPoints.createuser(payLoad);
		res.then().log().all();
			Assert.assertEquals(res.getStatusCode(), 200);
		}catch(Exception E) {
			logger.error("Unable to create user because of following reason "+E.getMessage());
		}
	}
	@Test(dependsOnMethods = "testPostUser")
	public void testGetUserByName() {
		try {
			logger.info("****Fetching user info****");

		Response response= UserEndPoints.readUser(this.payLoad.getUserNameString());
		
		response.then().log().all();
		response.then().statusCode(404);		
		}catch(Exception E) {
			logger.error("Could not fetch user info because of folllowing reason "+E.getMessage());
		}
	}
	@Test(dependsOnMethods = "testGetUserByName")
	public void testUpdateUser() {
		try {
			logger.info("***** Updating user info*****");
		payLoad.setFirstNameString(faker.name().firstName());
		payLoad.setLastNameString(faker.name().lastName());
		payLoad.setEmailString(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.payLoad.getUserNameString(), payLoad);
		response.then().log().all();
		Assert.assertEquals(response.jsonPath().getString("[0]firstName"), payLoad.getFirstNameString()," Name doesn't match");
		response.then().log().body().statusCode(200);
		
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Could not update user info because of folllowing reason "+e.getMessage());
		}	
	}
	@Test(dependsOnMethods = "testUpdateUser")
	public void testDeleteUser() throws Exception {
		try {
		Response response = UserEndPoints.deleteUser(payLoad.getUserNameString());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404);
		}catch(Exception E) {
			logger.error("Could not delete user info because of following reason "+E.getMessage());
		}
		
	}
	@Test(dependsOnMethods = "testDeleteUser")
	public void postUsingArray() {
		try{User userArr = new User();
		userArr.setEmailString(faker.internet().safeEmailAddress());
		userArr.setFirstNameString(faker.name().firstName());
		userArr.setLastNameString(faker.name().lastName());
		userArr.setUserNameString(faker.name().username());
		userArr.setId(faker.idNumber().hashCode());
		userArr.setPasswoString(faker.internet().password());
		userArr.setPhoneNumString(faker.phoneNumber().cellPhone());
		User[] userArray = {payLoad,userArr};
		
		Response response = UserEndPoints.createuser(userArr);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	@Test(dependsOnMethods = "postUsingArray")
	public void postUsingList() {
		try{
	
		Map<Object, Object> map1 = new HashMap<>();
		Map<Object, Object> map2 = new HashMap<>();
		
		map1.put("id", faker.idNumber());
		map1.put("firstName", faker.name().firstName());
		map1.put("lastName", faker.name().lastName());
		map1.put("username", faker.name().username());
		map1.put("email", faker.internet().emailAddress());
		map1.put("password", faker.internet().password());
		map1.put("phoneNumber", faker.phoneNumber().cellPhone());
		
		map2.put("id", faker.idNumber());
		map2.put("firstName", faker.name().firstName());
		map2.put("lastName", faker.name().lastName());
		map2.put("username", faker.name().username());
		map2.put("email", faker.internet().emailAddress());
		map2.put("password", faker.internet().password());
		map2.put("phoneNumber", faker.phoneNumber().cellPhone());
		
		ArrayList<Map<Object, Object>> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);
		Response response = UserEndPoints.createuserWithList(list);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	@Test(dependsOnMethods = "postUsingList")
	public void logsUser() {
	 Response response  = UserEndPoints.logUser(faker.name().username(), faker.internet().password());
	 response.then().statusCode(200).log().all();
	}
}

package apiTest;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.ietf.jgss.Oid;
import org.testng.annotations.Test;

import apiEndPoints.PetEndPoints;
import apiUtilities.ExcelUtilities;
import io.restassured.response.Response;

public class PetTestCases {
	
	@Test
	public void uploadImage() {
		File file = new File(System.getProperty("user.dir")+ File.separator + "TestData" + File.separator + "TestImage.jpg");
		Response response = PetEndPoints.uploadImage(123, file);
		response.then().statusCode(200).log().all();
	}
	@Test(dependsOnMethods = "uploadImage")
	public void postANewPet() throws IOException {
		ExcelUtilities utilities = new ExcelUtilities(System.getProperty("user.dir")+File.separator+"TestData"+File.separator+"TestData.xlsx");
	//	System.out.println(utilities.getSheetName(1)+ "this os );
		ArrayList<Map<String,String>> listDataList = utilities.readDataForPetExcel(utilities.getSheetName(1));
		
		for(Map<String, String> petDataMap : listDataList) {
			
			System.out.println(petDataMap);
			Map<String, Object> category = new HashMap<>();
			category.put("id",Integer.valueOf(petDataMap.get("categoryId")));
			category.put("name",petDataMap.get("categoryName"));
			
			
			Map<String, Object> tagMap = new HashMap<>();
			tagMap.put("id", Integer.valueOf(petDataMap.get("tagId")));
			tagMap.put("name", petDataMap.get("tagName"));
			
			Map<String, Object> petMap = new HashMap<>();
			petMap.put("id", Integer.valueOf(petDataMap.get("id")));
			petMap.put("category", category);
			petMap.put("name",petDataMap.get("name"));
			petMap.put("photoUrls",Collections.singletonList(petDataMap.get("photoUrl")));
			petMap.put("tags", Collections.singleton(tagMap));
			petMap.put("status", petDataMap.get("status"));	
			
			
			Response response = PetEndPoints.createNewPet(petMap);
			response.then().statusCode(200).log().all();
			}
		
		
	}

}

package apiTest;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import apiEndPoints.PetEndPoints;
import apiPayloads.PetPayload;
import apiUtilities.DataProviders;
import apiUtilities.ExcelUtilities;
import io.restassured.response.Response;

public class PetTestCases {
	
	PetPayload petPayload = new PetPayload();
	
	@Test
	public void uploadImage() {
		File file = new File(System.getProperty("user.dir")+ File.separator + "TestData" + File.separator + "TestImage.jpg");
		Response response = PetEndPoints.uploadImage(123, file);
		response.then().statusCode(200).log().all();
	}
/*	//@Test(dependsOnMethods = "uploadImage")
	public void postANewPet() throws IOException {
		ExcelUtilities utilities = new ExcelUtilities(System.getProperty("user.dir")+File.separator+"TestData"+File.separator+"TestData.xlsx");
		ArrayList<Map<String,String>> listDataList = utilities.readDataForPetExcel(utilities.getSheetName(1));
		
		for(Map<String, String> petDataMap : listDataList) {
			
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
			Assert.assertEquals(response.jsonPath().getString("name"), petMap.get("name"));
			Assert.assertEquals(response.jsonPath().getString("category.name"), category.get("name"));
					}
		
		
	}*/
	@Test(dataProvider = "dataProviderForPets", dataProviderClass = DataProviders.class, dependsOnMethods = "uploadImage")
	public void postANewPet1(HashMap<String, String> data) throws IOException {

		Map<String, Object> categoryHashMap= new HashMap<>();
		categoryHashMap.put("id",Integer.valueOf(data.get("categoryId")));
		categoryHashMap.put("name", data.get("categoryName"));
		
		
		
		Map<String, Object> tagMap = new HashMap<>();
		tagMap.put("id", Integer.valueOf(data.get("tagId")));
		tagMap.put("name", data.get("tagName"));
		
			
		petPayload.setCategory(categoryHashMap);
		petPayload.setId(Integer.valueOf( data.get("id")));
		petPayload.setName((data.get("name")));
		petPayload.setStatus((data.get("status")));
		petPayload.setPhotoUrls(Arrays.asList(data.get("photoUrl")));
		petPayload.setTags(Collections.singletonList(tagMap));
		
		Response response = PetEndPoints.createNewPet(petPayload);
		response.then().statusCode(200).log().all();
		Assert.assertEquals(response.jsonPath().getString("name"), petPayload.getName());
		Assert.assertEquals(response.jsonPath().getString("category.name"), categoryHashMap.get("name"));	
	}
	
	@Test(dataProvider = "dataProviderForPets", dataProviderClass = DataProviders.class, dependsOnMethods = "postANewPet1")
	public void updatePet(HashMap<String, String> data) throws IOException {
		
		petPayload.setName("Biscuit");
		petPayload.setName("sold");
		
		Response response = PetEndPoints.updatePet(Integer.valueOf(data.get("id")), petPayload);
		response.then().statusCode(200).log().all();
		Assert.assertEquals(response.jsonPath().getString("name"), petPayload.getName());
		Assert.assertEquals(response.jsonPath().getString("status"), petPayload.getStatus());
	
	}
	@Test(dependsOnMethods = "updatePet")
	public void getPetByAvailability() {
		Response response = PetEndPoints.readPetByAvailability("available");
		
		response.then().statusCode(200);
		JsonArray jsonArray = JsonParser.parseString(response.getBody().asString()).getAsJsonArray();
		
		
		System.out.println("Avaialable dogs are");
		for(JsonElement jsonElement : jsonArray) {
			
			JsonObject petJsonObject = jsonElement.getAsJsonObject();
			
			String statusString = petJsonObject.has("status") ? petJsonObject.get("status").getAsString() : "";
				
			String categoryString="";
			if(petJsonObject.has("category") && petJsonObject.get("category").isJsonObject()) {
				JsonObject categoryJsonObject = petJsonObject.getAsJsonObject("category");
				if(categoryJsonObject.has("name")) {
					categoryString = categoryJsonObject.get("name").getAsString();
					
					
				}
				
			}
			if(categoryString.equalsIgnoreCase("Dogs") && (statusString.equalsIgnoreCase("available"))) {
				
				int id = petJsonObject.has("id") ? petJsonObject.get("id").getAsInt() : 0000;
				String nameString = petJsonObject.has("name") ? petJsonObject.get("name").getAsString() : "Unknown";
				
				System.out.print(nameString + " " + id + " " + categoryString + " " + statusString +"\n" );
			}
			
		}
/*     List<String> names = response.jsonPath().getList("name");
        List<String> categories = response.jsonPath().getList("category.name");
        List<String> statuses = response.jsonPath().getList("status");

        System.out.println("----- Available Dogs -----");

        // 🔹 Iterate and print only dogs with available status
        for (int i = 0; i < names.size(); i++) {
            String category = (categories != null && i < categories.size()) ? categories.get(i) : "Unknown";
            String status = statuses.get(i);

            if ("Dogs".equalsIgnoreCase(category) && "available".equalsIgnoreCase(status)) {
                System.out.println("🐶 Pet Name: " + names.get(i));
                System.out.println("   Category: " + category);
                System.out.println("   Status: " + status);
                System.out.println("-----------------------------");
            }
        }
    }
}  */
	}
	@Test(dependsOnMethods = "getPetByAvailability", dataProvider = "PETID", dataProviderClass = DataProviders.class)
	public void getPetByID(String petId) {
		int petID = Integer.valueOf(petId);
		Response response = PetEndPoints.readPetByID(petID);
		
		response.then().statusCode(200);
		
		System.out.println("Dog name with ID "+petID +" "+response.jsonPath().getString("name"));
		
	}
	@Test(dependsOnMethods = "updatePetByID", dataProvider = "PETID", dataProviderClass = DataProviders.class)
	public void deletePetByID(String petId) {
		int petID = Integer.valueOf(petId);
		
		Response response = PetEndPoints.deletePetByID(petID);
		
		
		response.then().statusCode(200);
		
		System.out.println("Deleted pet information with ID "+petID);
		
	}
	@Test(dataProvider = "PETID", dataProviderClass = DataProviders.class, dependsOnMethods =  "getPetByID")
	public void updatePetByID(String petId) {
		int petID = Integer.valueOf(petId);
		Response response = PetEndPoints.updateAPetById(petID, "Biscuite", "available");
		response.then().statusCode(200);
	}

}

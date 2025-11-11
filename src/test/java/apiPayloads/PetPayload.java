package apiPayloads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class PetPayload {
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public List<Object> getTags() {
		return tags;
	}
	public void setTags(List<Object> tags) {
		this.tags = tags;
	}
	public Map<String, Object> getCategory() {
		return category;
	}
	public void setCategory(Map<String, Object> category) {
		this.category = category;
	}
	
	
	
	
	Map<String, Object> category = new HashMap<>();
	
	String name;
	
	 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	List<String> photoUrls;
	
	List<Object>  tags = new ArrayList<>();
	
	String status;
 }


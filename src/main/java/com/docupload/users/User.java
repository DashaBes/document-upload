package com.docupload.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.docupload.config.Config;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

import io.github.mohammadmoustafa.MongoDriver;

public class User implements Serializable {

	private static final long serialVersionUID = -469556336156689187L;
	
	private String userName = "";
	private String password = "";
	private String userClass = ""; // "generalUser" or "admin"
	
	// TODO List of documents from mongo?
	private List<Document> approvedList = new ArrayList<>();
	private List<Document> toBeApprovedList = new ArrayList<>();
	
	//to be approved, approved
	
	public String getUploads() {
		
		//TODO get User's documents from mongo and save in object
		
		approvedList.clear();
		toBeApprovedList.clear();
		
		// Connect to MongoDB driver
		MongoDriver mongo = new MongoDriver((String) Config.getInstance().getProperty("db_name"),
				new MongoClientURI((String) Config.getInstance().getProperty("mongo_database_uri")));
		
		//get user
		Bson filter = Filters.and(Filters.eq("name", userName), Filters.eq("password", password));
		FindIterable<Document> users = mongo.find((String) Config.getInstance().getProperty("user_collection_name"), filter);
		
		
		//add to their documents list				
		for (Document user: users) { //there will only be one
			
			//Util.logInfo("USER");
			
			@SuppressWarnings("unchecked")
			ArrayList<Document> userResources = (ArrayList<Document>) user.get("resources");
			
			for (Document resource: userResources) {
				//get the document from to be approved/approved by id
				
				//Util.logInfo("resource: " + resource.toString());
				//Util.logInfo("ID: " +  resource.get("id"));
				
				Bson filter2 = Filters.eq("_id", new ObjectId(resource.get("id").toString()));
				
				FindIterable<Document> approvedDocument = mongo.find((String) Config.getInstance().getProperty("approved_collection"), filter2);
				for (Document d: approvedDocument) {
					//Util.logInfo("document: " + d.toString());
					approvedList.add(d);
				}
				
				//SAME for to-be-approved list
				FindIterable<Document> toBeApprovedDocument = mongo.find((String) Config.getInstance().getProperty("to_be_approved_collection"), filter2);
				for (Document d: toBeApprovedDocument) {
					toBeApprovedList.add(d);
				}
			}
		}
		
		//Util.logInfo("APPROVED LIST: " + approvedList);
		return "true";
	}
	
	public void addResource(ObjectId id) {
		
		// Connect to MongoDB driver
		MongoDriver mongo = new MongoDriver((String) Config.getInstance().getProperty("db_name"),
				new MongoClientURI((String) Config.getInstance().getProperty("mongo_database_uri")));
		
		//get user
		Bson filter = Filters.and(Filters.eq("name", userName), Filters.eq("password", password));
		FindIterable<Document> users = mongo.find((String) Config.getInstance().getProperty("user_collection_name"), filter);
		
		//add to their documents list
		for (Document user: users) { //there will only be one
			
			@SuppressWarnings("unchecked")
			ArrayList<Document> userResources = (ArrayList<Document>) user.get("resources");
			
			//create a JSON out of the Resource
			JSONObject newResource = new JSONObject();
			try {
				newResource.put("id", id);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//add to user's resource list
			userResources.add(Document.parse(newResource.toString()));
			
			//update the user's resource list in mongo
			Document setFields = new Document();
			setFields.append("resources", userResources);
			Bson operation = new Document("$set", setFields);
			mongo.update((String) Config.getInstance().getProperty("user_collection_name"), filter, (Document) operation);
			
		}
		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Document> getApprovedList() {
		return this.approvedList;
	}
	public List<Document> getToBeApprovedList() {
		return this.toBeApprovedList;
	}
	
}

package com.docupload.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.docupload.config.Config;
import com.docupload.util.Util;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import io.github.mohammadmoustafa.MongoDriver;

public class ResourceCollection implements Serializable {
	
	private static final long serialVersionUID = -2961874481388670958L;
	
	private List<Document> forApproval = new ArrayList<>();
	private String selected = "";
	private List<String> selectedIdList = new ArrayList<>();
	
	public String getSubmissions() {
		
		try {
			
			List<Document> submissions = new ArrayList<>();
			
			// Connect to MongoDB driver
			MongoDriver mongo = new MongoDriver((String) Config.getInstance().getProperty("db_name"),
					new MongoClientURI((String) Config.getInstance().getProperty("mongo_database_uri")));
			
			FindIterable<Document> documents = mongo.find((String) Config.getInstance().getProperty("to_be_approved_collection"));
			
			for (Document doc: documents) {
				submissions.add(doc);
			}
			
			setForApproval(submissions);
		}
		catch (Exception e) {
			return "false";
		}
		
		return "true";
	}

	public List<Document> getForApproval() {
		return forApproval;
	}

	public void setForApproval(List<Document> forApproval) {
		this.forApproval = forApproval;
	}
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	
	//TODO put the approved items in a list, show them in the confirmation
	//approve the names in mongo
	
	
	public String approveSelected() {
		Util.logInfo("SELECTED: " + selected);
		
		selectedIdList = Arrays.asList(selected.split("\\s*,\\s*"));
		Util.logInfo("selected as list: " + selectedIdList);
		
		//connect to mongo
		//MongoDriver mongo = new MongoDriver((String) Config.getInstance().getProperty("db_name"),
				//new MongoClientURI((String) Config.getInstance().getProperty("mongo_database_uri")));
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase(Config.getInstance().getProperty("db_name"));
		
		
		for (String id: selectedIdList) {
			
			//make filter
			Bson filter = Filters.eq("_id", new ObjectId(id));
			
			//get the document
			FindIterable<Document> docs = database.getCollection((String) Config.getInstance().getProperty("to_be_approved_collection")).find(filter);
			Document doc = docs.first(); //there will only be 1 because it was found using the ID
			
			//insert the document into the approved colleciton
			database.getCollection((String) Config.getInstance().getProperty("approved_collection")).insertOne(doc);
			
			//delete the document from the to_be_approved collection
			database.getCollection((String) Config.getInstance().getProperty("to_be_approved_collection")).deleteOne(filter);
			
			//TODO change the status in the user's docs
			// get user associated with the doc?
			
			
		}
		
		mongoClient.close();
		
		return "true";
	}


}

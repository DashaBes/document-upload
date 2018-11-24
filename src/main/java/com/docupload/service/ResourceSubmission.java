package com.docupload.service;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.docupload.config.Config;
import com.docupload.document.Resource;
import com.docupload.users.User;
import com.docupload.util.Util;
import com.mongodb.MongoClientURI;

import io.github.mohammadmoustafa.MongoDriver;

@Service
public class ResourceSubmission {
	
	public String submitResource(Resource doc, User user) {
		
		String title = doc.getTitle();
		String resourceType = doc.getResourceType();
		String content = doc.getDescription();
		String contactName = doc.getContactName();
		String resource = doc.getResource();
		
		Config config = Config.getInstance();
		
		try {
			
			// Connect to MongoDB driver
			MongoDriver mongo = new MongoDriver((String) Config.getInstance().getProperty("db_name"),
						new MongoClientURI((String) Config.getInstance().getProperty("mongo_database_uri")));
			
			
			// TODO the labels currently used are a bit strange
			// should be changed in this and in the researchwiki system later
			// "filename" especially
			
			JSONObject document = new JSONObject();
			document.put("name", title);
			document.put("content", content);
			document.put("resourceType", resourceType);
			document.put("contactName", contactName);
			document.put("filename", resource);
			
			if (user.getUserClass().equals("admin")) {
				
				//straight into approved documents collection
				Document document2 = Document.parse(document.toString());
				mongo.insert((String) config.getProperty("approved_collection"), document2);
				ObjectId id = (ObjectId)document2.get("_id");
				
				//add to user's document list
				user.addResource(id);
				
			} else if (user.getUserClass().equals("generalUser")) {
				
				//into "for approval" collection
				Document document2 = Document.parse(document.toString());
				mongo.insert((String) config.getProperty("to_be_approved_collection"), document2);
				ObjectId id = (ObjectId)document2.get("_id");
				
				//add to user's document list
				user.addResource(id);
				
			}
		}
		catch (Exception e) {
			Util.logInfo(e.toString());
			Util.logInfo(doc.getTitle());
			Util.logInfo(doc.getResourceType());
			Util.logInfo(doc.getDescription());
			return "false";
		}
		return "true";
	}
}

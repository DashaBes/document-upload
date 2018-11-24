package com.docupload.service;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.docupload.config.Config;
import com.docupload.users.User;
import com.docupload.util.Util;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

import io.github.mohammadmoustafa.MongoDriver;

@Service
public class LoginService
{
	public String validateUser(User user)
	{
		String userName = user.getUserName();
		String password = user.getPassword();
		
		//Util.logInfo("name: " + userName);
		//Util.logInfo("password: " + password);
		
		Config config = Config.getInstance();
		
		try {
			
			// Connect to MongoDB driver
			MongoDriver mongo = new MongoDriver((String) Config.getInstance().getProperty("db_name"),
						new MongoClientURI((String) Config.getInstance().getProperty("mongo_database_uri")));
			
			Bson filter = Filters.and(Filters.eq("name", userName), Filters.eq("password", password));
			
			FindIterable<Document> users = mongo.find((String) config.getProperty("user_collection_name"), filter);
			
			
			int count = 0;
			for (Document doc: users) {
				count += 1;
			}
			
			//Util.logInfo("users: " + count);
			
			if (count == 1) {
				//set the user's class so it can be used in the rest of the application flow
				String userClass = (String) users.first().get("class");
				user.setUserClass(userClass);
				return "true";
			}
			else {
				return "false";
			}
			
			
			
			} catch (Exception e) {
				Util.logInfo("Error logging in: " + e.toString());
				return "false";
				//TODO actually handle error
			}
		
		//verify the user
		
		 /*
		 * db.collection.find({"name": "Dasha", "password": "123"})
		 * 
		 * https://docs.mongodb.com/manual/reference/method/db.collection.find/
		 * 
		 */
		
		//AggregateIterable<Document> individualDocs = mongo.aggregate((String) Config.getInstance().getProperty("db_collection_name"), individualWordsFilter);
		
		
		
		//make a bson filter for searching for a name
		
		
		//get aggregate iterable with mongo.find
		//there should only be 1 entry or 0
		
		
		
		/*
		if(userName.equals("Dasha") && password.equals("123")) {
			return "true";
		}
		else {
			return "false";
		}
		*/
		
		
		
		
	}
		
}

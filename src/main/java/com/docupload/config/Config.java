package com.docupload.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Config {
	
	private static Config INSTANCE = null;
	private static Map<Object, Object> props = new HashMap<Object, Object>();
	private static String filepath = "C:/Document Upload/docupload.conf";
	
	private Config() {}
	
	public static Config getInstance() {
		if (INSTANCE == null) {
			InputStream inputStream = null;
			try {
				Properties prop = new Properties();
				inputStream = new FileInputStream(filepath);
				if (inputStream != null) {
					prop.load(inputStream);
				}
				for (Entry<Object, Object> entry : prop.entrySet()) {
					props.put(entry.getKey(), entry.getValue());
				}
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			INSTANCE = new Config();
		}
		
		return INSTANCE;
	}
	
	public String getProperty(String propertyName) {
		return (String) props.get(propertyName);
	}
	
	public static void setFilepath(String path) {
		INSTANCE = null;
		props.clear();
		filepath = path;
	}
	

}

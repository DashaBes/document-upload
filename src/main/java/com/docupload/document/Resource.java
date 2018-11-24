package com.docupload.document;

import java.io.Serializable;

public class Resource implements Serializable {
	
	private static final long serialVersionUID = -3222420802699722341L;

	private boolean acceptedStatus = false;
	private String title = "";
	private String description = "";
	private String resourceType = ""; // one of: local document, external document, website, contact
	
	private String contactName = "";
	
	private String resource = "";
	
	//TODO extra metadata?
	
	public boolean getAcceptedStatus () {
		return acceptedStatus;
	}
	public void setAcceptedStatus(boolean status) {
		this.acceptedStatus = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}

}

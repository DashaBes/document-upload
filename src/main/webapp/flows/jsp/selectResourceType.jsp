<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="<c:url value="/classes/css/styles.css"/>" />
		<title>Select Resource Type</title>
	</head>
	
	<body>
	
	<div class="header">
		<h2>Resource Selection</h2>
	</div>
		
	<div class="content">
	
		<h3>Select the type of resource you wish to add.</h3>
		
		<form method="post" action="${flowExecutionUrl}">
		
		<input type="hidden" name="_eventId" value="Proceed">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
		
			<input type="radio" name="resourceType" value="Contact"/> Contact <br>
			<input type="radio" name="resourceType" value="Link"/> Link <br>
			
			<!-- <input type="radio" name="resourceType" value="Document Upload"/> Document Upload <br> -->
			
			<br>
			<input class="regular-button" type="submit" value="Ok">
			
		</form>
		
	</div>
		
		
		
	 	
	 
	 <!--  WORKING VERSION:
	 
		<form action="flows/jsp/fileUpload.jsp" method="post" enctype="multipart/form-data">
		
			<input type="file" name="file" size="50" />
			<br />
			<input type="submit" value="Upload" />
			
		</form>
		
		-->
		
		
		
		
		
		<!--
		<form:form enctype="mulitpart/form-data" method="post" modelAttribute="fileUploadModel" action="${flowExecutionUrl}">
    		<input type="file" name="file" size="50" />
    		<br />
    		<input type="submit" name="_eventId_submitUpload" value="Upload" />
		</form:form>
		-->
		
		<!--
		
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
		
		<form:form modelAttribute="uploadFile" enctype="multipart/form-data">
			<input type="file" name="file" size="50" />
			<br />
			<input type="submit" value="Upload" name="_eventId_submitUpload" />
		</form:form>
		
		-->
		
		
		
	</body>
</html>
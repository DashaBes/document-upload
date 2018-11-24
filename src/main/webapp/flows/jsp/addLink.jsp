<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored ="false" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Link</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/classes/css/styles.css"/>" />
	</head>
	
	<body>
		
		<div class="header">
			<h3> Add a Link resource </h3>
		</div>
		
		<div class="content">
		
			<form method="post" action="${flowExecutionUrl}">
			
				<input type="hidden" name="_eventId" value="submitLink"> 
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
				
				<p>Resource Title:</p>
				<input type="text" name="title"><br> <!-- maximum amount of characters? -->
				
				<p>Description:</p>
				<textarea name="description" rows="10" cols="30"></textarea><br>
				
				<p>Link:</p>
				<input type="text" name="resource"><br>
				
				<br>
				
				<input class="regular-button" type="submit" value="Submit" />
			
			</form>
		
		</div>
		
	</body>
	
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored ="false" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Submissions</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/classes/css/styles.css"/>" />
	<script type="text/javascript" src="<c:url value="/classes/jquery/jquery-3.3.1.min.js"/>"></script>
</head>

<body>
	
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
	
	<div class="header">
		<h3> Resources Pending Approval</h3>
	</div>
	
	<div class="content">
	
		 <c:forEach items="${resourceCollection.forApproval}" var="item">
	    	<div class="collapsible">
	    		<label>${item.resourceType}</label>
	    		<label>${item.name}</label>
	    	</div>
	    	<div class="info">
	    	
	    		<p>Resource Type: ${item.resourceType}</p>
	    		<p>Name: ${item.name}</p>
	    		<p>Description: ${item.content}</p>
	    		<p>Resource: ${item.filename}</p>
	    		<br>
	    		
	    		
	    		
	    	</div>
	    	
	    </c:forEach>
	    
	    <script>
		var coll = document.getElementsByClassName("collapsible");
		var i;

		for (i = 0; i < coll.length; i++) {
		  coll[i].addEventListener("click", function() {
		    this.classList.toggle("active");
		    var content = this.nextElementSibling;
		    if (content.style.maxHeight){
		      content.style.maxHeight = null;
		    } else {
		      content.style.maxHeight = content.scrollHeight + "px";
		    } 
		  });
		}
		</script>
	
	
	
		<form method="post" action="${flowExecutionUrl}">
		
			<c:forEach items="${resourceCollection.forApproval}" var="item">
				<input type="checkbox" name="selected" value="${item._id}"> ${item.name} <br>
			</c:forEach>
			
			<input type="submit" name="_eventId_approve" value="Approve">
			
		</form>
	
	</div>
	
	<!--  
	<table>
	    <tr>
	        <th>Resource Type</th>
	        <th>Title</th>
	        <th>Description</th>
	        <th>Resource</th>
	        <th>Status</th>
	    </tr>
	    
	    <c:forEach items="${user.approvedList}" var="item">
	        <tr>
	            <td>${item.resourceType}</td>
	            <td>${item.name}</td>
	            <td>${item.content}</td>
	            <td>${item.filename}</td>
	            <td>${item.status}</td>
	        </tr>
	    </c:forEach>
	    
	   </table>
		-->
	
	
</body>
</html>
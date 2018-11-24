<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored ="false" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<link type="text/css" rel="stylesheet" href="<c:url value="/classes/css/styles.css"/>" />
	</head>
<body>

	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
	
	<div class="header" id="homeScreenHeader">
	
		<div class="greeting"> <h2> Welcome to your home screen, ${user.userName}. </h2> </div>
		
		<div class="logOut">
			<form method="post" action="${flowExecutionUrl}">
				<input class="logout-button" type="submit" name="_eventId_LogOut" value="Log Out"/>
			</form>
		</div>
		
	</div>
		
	<div class="button-content">
	
		<form method="post" action="${flowExecutionUrl}">
			<input class="option-button" type="submit" name="_eventId_upload" value="Add a new resource"/>
		</form>
		
		<form method="post" action="${flowExecutionUrl}">
			<input class="option-button" type="submit" name="_eventId_checkUploads" value="View added resources"/>
		</form>
		
		
		
	
	</div>
	
	<script>
	
		// When the user scrolls the page, execute myFunction 
		window.onscroll = function() {myFunction()};
	
		// Get the header
		var header = document.getElementById("homeScreenHeader");
	
		// Get the offset position of the navbar
		var sticky = header.offsetTop;
	
		// Add the sticky class to the header when you reach its scroll position. Remove "sticky" when you leave the scroll position
		function myFunction() {
		  if (window.pageYOffset > sticky) {
		    header.classList.add("sticky");
		  } else {
		    header.classList.remove("sticky");
		  }
		}
	
	</script>
	
	
</body>
</html>
<%@ page isELIgnored="false"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    
	<!--  <link rel="stylesheet" type="text/css" href="css/login.css"/> -->
	<link type="text/css" rel="stylesheet" href="<c:url value="/classes/css/styles.css"/>" />
</head>

<body>

	<div id="container">
	
		<div id="title">
			<h2>Log in</h2>
		</div>
		
		<form method="post" action="${flowExecutionUrl}">
	
			<input type="hidden" name="_eventId" value="performLogin">
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
	
			<div class="input-box">
				<label>Username:</label>
				<input type="text" name="userName" maxlength="40"><br>
			</div>
			
			<div class="input-box">
				<label>Password:</label>
				<input type="password" name="password" maxlength="40">
			</div>
			
			<div id="button-container">
				<input class="regular-button" type="submit" value="Login" />
			</div>
			
			
	
		</form>
		
	</div>

</body>
</html>

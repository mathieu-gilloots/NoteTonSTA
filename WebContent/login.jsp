<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Speaker Area - Login</title>
		<link rel="stylesheet" type="text/css" href="<%= getServletContext().getContextPath() %>/style/style.css" />
		
		<style type="text/css">
      		.container > footer p {
        		text-align: center; /* center align it with the container */
        	}
        	
        	.content{
        		padding: 50px;
        	}
    	</style>
    	
	</head>
	<body>
		<%@ include file="/templates/header.jsp" %>
	
		<div class="content">
	  		<h1>Login into Speaker Area</h1>
	  		<c:if test="${not empty param.error}">
	  			<c:out value="<span style='color : #FF0000'><b>Invalid Login !</b></span><br /><br />" escapeXml="false" />
	  		</c:if>
		  		<form action="login" method="post">
			  		<label for="email">E-Mail :&nbsp;</label>
					<input type="text" name="email" id="email" /><br /><br />
					<label for="password">Password :&nbsp;</label>
					<input type="password" name="password" id="password" />
					<input type="submit" value="Login !" />
		  		</form>

	    </div>
		
		<%@ include file="/templates/footer.jsp" %>
	</body>
</html>
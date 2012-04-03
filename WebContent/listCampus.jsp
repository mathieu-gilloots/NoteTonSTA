<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Home</title>
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
	  		<h1>Welcome to Note Ton STA !</h1>
	  		<p>
		  		This website propose you to evaluate interventions of SUPINFO speakers.<br />
		  		You can also see statistics by speaker or by campus.
	  		</p>
	  		<p>
		  		Please select your campus : 
		  		<form action="ListIntervCampus" method="post">
			  		<select name="CampusId" onchange="javascript:submit();">
			  			<option selected="selected">-- Campus --</option>
			  			<!-- Retrieving list of Campus -->
			  			<c:forEach items="${lescampus}" var="campus">
				  			<option value="<c:out value="${ campus.id }" />"><c:out value="${campus.name}"/></option>
			  			</c:forEach>
			  		</select>
		  		</form>
		  	</p>
	  		<p>
	  			If you are a speaker and have already an account, please <a href="login">Authenticate you</a> to manage your interventions.<br />
	  			If you doesn't have an account, please <a href="speakers/register">Register you</a> !
	  		</p>
	  		
	    </div>
		
		<%@ include file="/templates/footer.jsp" %>
	</body>
</html>
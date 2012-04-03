<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Register as Speaker</title>
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
	  		<h1>Register as Speaker</h1>
	  		<p>
		  		Please fill all fields !
		  	</p>
	 	
	 	
	 	
	 	<form action="register" method="POST">
	 		<table style='width : 100%;'>
	 			<tr>
	 				<td style='width : 18%;'>First Name : </td>
	 				<td>
	 					<input type='text' name='FirstName' value="<c:if test="${not empty FirstName}"><c:out value="${FirstName}" /></c:if>" />
	 					<c:if test="${ not empty error_FirstName}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_FirstName}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>Last Name : </td>
	 				<td>
	 					<input type='text' name='LastName' value="<c:if test="${not empty LastName}"><c:out value="${LastName}" /></c:if>" />
	 					<c:if test="${ not empty error_LastName}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_LastName}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>E-Mail : </td>
	 				<td>
	 					<input type='text' name='email' value="<c:if test="${not empty email }"><c:out value="${email}" /></c:if>" />
	 					<c:if test="${ not empty error_email}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_email}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>Password : </td>
	 				<td>
	 					<input type='password' name='password' />
	 					<c:if test="${ not empty error_password}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_password}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>Password [Confirm] : </td>
	 				<td>
	 					<input type='password' name='passwordConfirm' />
	 					<c:if test="${ not empty error_passwordConfirm}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_passwordConfirm}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td colspan='2'><input type='submit' value='Register !' /></td>
	 			</tr>
	 			
	 		</table>
	 	</form>
	 	
	    </div>
		
		<%@ include file="/templates/footer.jsp" %>
	</body>
</html>
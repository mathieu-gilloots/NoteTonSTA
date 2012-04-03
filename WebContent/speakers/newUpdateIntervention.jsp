<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<c:choose>
			<c:when test="${ action == 'new' }">
				<title>Add a new Intervention</title>
			</c:when>
			<c:otherwise>
				<title>Update an Intervention</title>
			</c:otherwise>
		</c:choose>
		<link rel="stylesheet" type="text/css" href="<%= getServletContext().getContextPath() %>/style/style.css" />
		<link rel="stylesheet" href="<%= getServletContext().getContextPath() %>/js/JqueryUI/themes/base/jquery.ui.all.css">
		<style type="text/css">
      		.container > footer p {
        		text-align: center; /* center align it with the container */
        	}
        	
        	.content{
        		padding: 50px;
        	}
        	

    	</style>
    	
    	<!-- Include Jquery & JQuery UI & Chart Library -->
    	<script type="text/javascript" src="<%= getServletContext().getContextPath() %>/js/jquery-1.6.2.js"></script>
    	<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.core.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.widget.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.datepicker.js"></script>
		
		<!-- Define the datePiker  -->
		<script type="text/javascript">
			$(function() {
				$( "#fromPeriod" ).datepicker(
					{dateFormat : "yy-mm-dd"}
				);
				
				$( "#toPeriod" ).datepicker(
					{dateFormat : "yy-mm-dd"}
				);
			});
		</script>
    	
	</head>
	<body>
		<%@ include file="/templates/header.jsp" %>
		
		<div class="content">
	  		<h1>Add a new Intervention</h1>
	  		<p>
		  		Please fill all fields !
		  	</p>
	 	
	 	
	 	
	 	<c:choose>
	 		<c:when test="${ action=='new' }">
	 			<form action="<%= getServletContext().getContextPath() %>/interventions/new" method="POST">
	 		</c:when>
	 		<c:otherwise>
	 			<form action="<%= getServletContext().getContextPath() %>/interventions/update" method="POST">
	 		</c:otherwise>
	 	</c:choose>
	 	<c:if test="${ action=='update' }">
	 		<input type="hidden" name="intervId" value="<c:out value="${ intervId }" />" />
	 	</c:if>
	 		<table style='width : 100%;'>
	 			<tr>
	 				<td style='width : 18%;'>Subject : </td>
	 				<td>
	 					<input type='text' name='subject' value="<c:if test="${not empty subject}"><c:out value="${subject}" /></c:if>" />
	 					<c:if test="${ not empty error_subject}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_subject}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>Campus : </td>
	 				<td>
	 					<select name="campus">
	 						<c:forEach items="${CampusList}" var="campusOption">
		 						<c:choose>
			 						<c:when test="${(not empty campus) && (campus == campusOption.id) }">
			 							<option value="<c:out value="${campusOption.id}" />" selected><c:out value="${campusOption.name}" /></option>
			 						</c:when>
			 						<c:otherwise>
			 							<option value="<c:out value="${campusOption.id}" />"><c:out value="${campusOption.name}" /></option>
			 						</c:otherwise>
		 						</c:choose>
	 						</c:forEach>
	 					</select>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>From : </td>
	 				<td>
	 					<input type='text' name='fromPeriod' id='fromPeriod' value="<c:if test="${not empty fromPeriod }"><c:out value="${fromPeriod}" /></c:if>" />
	 					<c:if test="${ not empty error_fromPeriod}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_fromPeriod}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>To : </td>
	 				<td>
	 					<input type='text' name='toPeriod' id='toPeriod' value="<c:if test="${not empty toPeriod }"><c:out value="${toPeriod}" /></c:if>" />
	 					<c:if test="${ not empty error_toPeriod}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_toPeriod}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>Description : </td>
	 				<td>
	 					<!-- Leave this line in one line because the textarea value will be a most blank caracters without that -->
	 					<textarea name='description'><c:if test="${not empty description }"><c:out value="${description}" /></c:if></textarea>
	 					
	 					<c:if test="${ not empty error_description}">
	 						<span style='color : #FF0000; font-weight : bold;'><c:out value="${error_description}" /></span>
	 					</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td colspan='2'>
		 				<c:choose>
					 		<c:when test="${ action=='new' }">
					 			<input type='submit' value='Add This Intervention !' />
					 		</c:when>
					 		<c:otherwise>
					 			<input type='submit' value='Update This Intervention !' />
					 		</c:otherwise>
					 	</c:choose>
	 				</td>
	 			</tr>
	 			
	 		</table>
	 	</form>
	 	
	    </div>
		
		<%@ include file="/templates/footer.jsp" %>
	</body>
</html>
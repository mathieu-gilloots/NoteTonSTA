<%@page import="com.supinfo.notetonsta.dao.InterventionsDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>My interventions</title>
		<link rel="stylesheet" type="text/css" href="<%= getServletContext().getContextPath() %>/style/style.css" />
		<style type="text/css">
      		.container > footer p {
        		text-align: center; /* center align it with the container */
        	}
        	
        	.content{
        		padding: 50px;
        	}
        	
        	td{
        		text-align : center;
        	}
    	</style>
    	
	</head>
	<body>
		<%@ include file="/templates/header.jsp" %>
		
		<div class="content">
	  		<h1>List of my Interventions</h1>
	  		
	  		<br />
	  		<p style="text-align: center;"><a href="<%= getServletContext().getContextPath() %>/interventions/new">Add an intervention</a></p>
	  		
		  	<c:choose>
		  		<c:when test="${ empty InterventionsList }">
		  			<p style='text-align : center; font-weight : bold;'>No Intervention to display ! </p>
		  		</c:when>
		  		<c:otherwise>
		  			<table style="width : 100%">
				  		<tr>
				  			<th>Subject</th>
				  			<th>Campus</th>
				  			<th>Begin</th>
				  			<th>End</th>
				  			<th>Status</th>
				  			<th>Global Event Mark</th>
				  			<th>Actions</th>
				  		</tr>
				  		<c:forEach items="${ InterventionsList }" var="intervention">
				  			<tr>
				  				<td><a href='<%= getServletContext().getContextPath() %>/viewIntervention?id=<c:out value="${ intervention.id }" />'><c:out value="${ intervention.subject }" /></a></td>
				  				<td><c:out value="${ intervention.campus.name }" /></td>
				  				<td><fmt:formatDate value="${intervention.fromPeriod}" pattern="dd MMMM yyyy" /></td>
				  				<td><fmt:formatDate value="${intervention.toPeriod}" pattern="dd MMMM yyyy" /></td>
				  				<td>
				  					<c:choose>
					  					<c:when test="${ intervention.fromPeriod > now }">
					  						<!-- If the intervention has to begin -->
					  						Not Started
					  					</c:when>
					  					<c:when test="${ intervention.toPeriod < now }">
					  						<!-- If the intervention is Ended  -->
					  						Done
					  					</c:when>
					  					<c:otherwise>
					  						<!-- Intervention in progress -->
					  						in progress
					  					</c:otherwise>
				  					</c:choose>
				  				</td>
				  				<td>
				  					<fmt:formatNumber value="${ InterventionDAO.getAVGAllMarks(intervention.id) }" maxFractionDigits="1"/>/5
				  				</td>
				  				<td>
				  					<a href="<%= getServletContext().getContextPath() %>/interventions/remove?id=<c:out value="${intervention.id}" />">Remove</a> - <a href="<%= getServletContext().getContextPath() %>/interventions/update?id=<c:out value="${intervention.id}" />">Update</a>
				  				</td>
				  			</tr>
				  		</c:forEach>
				  	</table>
		  		</c:otherwise>
		  	</c:choose>	
	  		
	  		
	    </div>
		
		<%@ include file="/templates/footer.jsp" %>
	</body>
</html>
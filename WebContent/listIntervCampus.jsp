<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Interventions on <c:out value="${ Campus.name }" /> Campus</title>
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
	  		<h1>List of Interventions on <c:out value="${ Campus.name }" /> Campus</h1>
	  		
	  		<p style="text-align : center;"><a href="listCampus">Change Campus - Back to Campus List</a></p>
	  		<br />
	  		
		  	<c:choose>
		  		<c:when test="${ empty InterventionsList }">
		  			<p style='text-align : center; font-weight : bold;'>No Intervention to display ! </p>
		  		</c:when>
		  		<c:otherwise>
		  			<table style="width : 100%">
				  		<tr>
				  			<th>Subject</th>
				  			<th>Begin</th>
				  			<th>End</th>
				  			<th>Status</th>
				  		</tr>
				  		<c:forEach items="${ InterventionsList }" var="intervention">
				  			<tr>
				  				<td><a href='<%= getServletContext().getContextPath() %>/viewIntervention?id=<c:out value="${ intervention.id }" />'><c:out value="${ intervention.subject }" /></a></td>
				  				<td><fmt:formatDate value="${intervention.fromPeriod}" pattern="dd MMMM yyyy" /></td>
				  				<td><fmt:formatDate value="${intervention.toPeriod}" pattern="dd MMMM yyyy" /></td>
				  				<td>
				  					<c:choose>
					  					<c:when test="${ intervention.fromPeriod > now }">
					  						<!-- If the intervention has to begin -->
					  						Has to Begin
					  					</c:when>
					  					<c:when test="${ intervention.toPeriod < now }">
					  						<!-- If the intervention is Ended  -->
					  						Ended
					  					</c:when>
					  					<c:otherwise>
					  						<!-- Intervention in progress -->
					  						in progress
					  					</c:otherwise>
				  					</c:choose>
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
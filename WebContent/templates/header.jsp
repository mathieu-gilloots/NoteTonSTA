<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="topbar">
	<div class="fill">
    	<div class="container">
        	<span class="brand"><a href="<%= getServletContext().getContextPath() %>/listCampus">Note Ton STA</a></span>
        	
			<ul class="nav">
			<c:choose>
			<c:when test="${ not empty userId }">
				<li><a class='active'>Welcome <c:out value="${FirstName} ${LastName}" /></a></li>
				<li><a href="<%= getServletContext().getContextPath() %>/interventions/mine">My Interventions</a></li>
				<li><a href="<%= getServletContext().getContextPath() %>/interventions/new">New Intervention</a></li>
				<li><a href="<%= getServletContext().getContextPath() %>/logout">Logout</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="<%= getServletContext().getContextPath() %>/speakers/register">Register</a></li>
			<li><a href="<%= getServletContext().getContextPath() %>/login">Login</a></li>
			</c:otherwise>
			</c:choose>
            	
          	</ul>
        </div>
    </div>
</div>
<div class="container">
    
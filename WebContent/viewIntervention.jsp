<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Intervention Details</title>
		<link rel="stylesheet" type="text/css" href="<%= getServletContext().getContextPath() %>/style/style.css" />
		<link rel="stylesheet" href="<%= getServletContext().getContextPath() %>/js/JqueryUI/themes/base/jquery.ui.all.css">
		<style type="text/css">
      		.container > footer p {
        		text-align: center; /* center align it with the container */
        	}
        	
        	td{
        		text-align : center;
        	}
        	
        	.content{
        		padding: 50px;
        	}
        	
        	#EvaluateForm td, th{
        		border : 1px #DDDDDD solid;
        	}
        	
    	</style>
    	
    	
    	<!-- Include Jquery & JQuery UI & Chart Library -->
    	<script type="text/javascript" src="<%= getServletContext().getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/external/jquery.bgiframe-2.1.2.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.core.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.widget.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.mouse.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.button.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.draggable.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.position.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.resizable.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.ui.dialog.js"></script>
		<script src="<%= getServletContext().getContextPath() %>/js/JqueryUI/ui/jquery.effects.core.js"></script>
    	<script type="text/javascript" src="<%= getServletContext().getContextPath() %>/js/highcharts/highcharts.js"></script>
    	
    	<!-- Define Chart -->
    	<c:if test="${interventionDAO.getNumberEvaluation(intervention.id) != 0 }">
	  		<!-- Call Chart Script only when the intervention have a minimum of 1 mark -->
    	<script type="text/javascript">
		
			var chart;
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'Graph',
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					title: {
						text: ''
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ Math.round(this.percentage*Math.pow(10,2))/Math.pow(10,2) + ' %';
						}
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: false
							},
							showInLegend: true
						}
					},
				    series: [{
						type: 'pie',
						name: 'Marks Repartition',
						data: [
							<%= request.getAttribute("StringChart") %>
						]
					}]
				});
			});
				
		</script>
    	</c:if>
    	
    	<!-- Define Evaluate Form -->
    	<script type="text/javascript">
	    	$(function() {
	    		$( "#EvaluateForm" ).dialog({
	    			autoOpen: false,
	    			height: 600,
	    			width: 600,
	    			modal: true,
	    			buttons: {
	    				"Submit": function() {
	    					$(this).dialog("close");
	    					$("#EvalFormulaire").submit();
	    				}
	    			}
	    		});
	
	    		$( "#evaluate" )
	    			.click(function(){
	    				$( "#EvaluateForm" ).dialog( "open" );
	    			});
	    	});
    	</script>
    	
    	
	</head>
	<body>
		<%@ include file="/templates/header.jsp" %>
		<!-- Define Evaluation Form Block -->
		<div id="EvaluateForm" title="Evaluate the Intervention">
			<form action="EvaluateIntervention" method="post" id="EvalFormulaire">
				<input type="hidden" name="interventionId" value="<c:out value="${ intervention.id }" />" />
				<br />
				<p style="text-align : center;">
					ID Booster : <input type="text" name="idBooster" />
				</p>
				<br /><br />
				<h3 style="text-align:center;">About the Speaker</h3>
				<table style="width : 90%; text-align: center;">
					<tr>
						<th></th>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
					</tr>
					<tr>
						<td>His Knowledge of the Subject</td>
						<td><input type="radio" name="speakerQ1Mark" value="1" /></td>
						<td><input type="radio" name="speakerQ1Mark" value="2" /></td>
						<td><input type="radio" name="speakerQ1Mark" value="3" /></td>
						<td><input type="radio" name="speakerQ1Mark" value="4" /></td>
						<td><input type="radio" name="speakerQ1Mark" value="5" /></td>
					</tr>
					<tr>
						<td>His Teaching abilities</td>
						<td><input type="radio" name="speakerQ2Mark" value="1" /></td>
						<td><input type="radio" name="speakerQ2Mark" value="2" /></td>
						<td><input type="radio" name="speakerQ2Mark" value="3" /></td>
						<td><input type="radio" name="speakerQ2Mark" value="4" /></td>
						<td><input type="radio" name="speakerQ2Mark" value="5" /></td>
					</tr>
					<tr>
						<td>The quality of answers</td>
						<td><input type="radio" name="speakerQ3Mark" value="1" /></td>
						<td><input type="radio" name="speakerQ3Mark" value="2" /></td>
						<td><input type="radio" name="speakerQ3Mark" value="3" /></td>
						<td><input type="radio" name="speakerQ3Mark" value="4" /></td>
						<td><input type="radio" name="speakerQ3Mark" value="5" /></td>
					</tr>
				</table>
				<br /><br />
				<h3 style="text-align:center;">About the Slides</h3>
				<table style="width : 90%; text-align: center;">
					<tr>
						<th></th>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
					</tr>
					<tr>
						<td>The richness of the content</td>
						<td><input type="radio" name="slidesQ1Mark" value="1" /></td>
						<td><input type="radio" name="slidesQ1Mark" value="2" /></td>
						<td><input type="radio" name="slidesQ1Mark" value="3" /></td>
						<td><input type="radio" name="slidesQ1Mark" value="4" /></td>
						<td><input type="radio" name="slidesQ1Mark" value="5" /></td>
					</tr>
					<tr>
						<td>The format / Layout</td>
						<td><input type="radio" name="slidesQ2Mark" value="1" /></td>
						<td><input type="radio" name="slidesQ2Mark" value="2" /></td>
						<td><input type="radio" name="slidesQ2Mark" value="3" /></td>
						<td><input type="radio" name="slidesQ2Mark" value="4" /></td>
						<td><input type="radio" name="slidesQ2Mark" value="5" /></td>
					</tr>
					<tr>
						<td>The examples</td>
						<td><input type="radio" name="slidesQ3Mark" value="1" /></td>
						<td><input type="radio" name="slidesQ3Mark" value="2" /></td>
						<td><input type="radio" name="slidesQ3Mark" value="3" /></td>
						<td><input type="radio" name="slidesQ3Mark" value="4" /></td>
						<td><input type="radio" name="slidesQ3Mark" value="5" /></td>
					</tr>
				</table> 
				<p style="text-align : center;">
					Comments :
					<textarea name="comments"></textarea>
				</p>		
			</form>
		</div>
		
		<div class="content">
	  		<h2 style="text-align : center;"><c:out value="${ intervention.subject }" /></h2>
	  		
	  		<table style="width : 90%; margin-left : auto; margin-right : auto; text-align : center;">
	  			<tr>
	  				<td>Campus : <c:out value="${ intervention.campus.name }" /></td>
	  				<td>From : <fmt:formatDate value="${intervention.fromPeriod}" pattern="dd MMMM yyyy" /></td>
	  				<td>To : <fmt:formatDate value="${intervention.toPeriod}" pattern="dd MMMM yyyy" /></td>
	  			</tr>
	  			<tr>
	  				<td colspan="3">
	  					Status : 
	  					<c:choose>
					  		<c:when test="${ intervention.fromPeriod > now }">
					  			<!-- If the intervention has to begin -->
					  			<span style="color : #00FF00; font-weight : bold;">Has to Begin</span>
					  		</c:when>
					  		<c:when test="${ intervention.toPeriod < now }">
					  			<!-- If the intervention is Ended  -->
					  			<span style="color : #FF0000; font-weight : bold;">Ended</span>
					  		</c:when>
					  		<c:otherwise>
					  			<!-- Intervention in progress -->
					  			<span style="color : #0000FF; font-weight : bold;">in progress</span>
					  		</c:otherwise>
				  		</c:choose>
	  				</td>
	  			</tr>
	  		</table>
	  		
	  		<br />
	  		
	  		<p><c:out value="${ intervention.description }" /></p>
	  		<br /><br />
	  		<ul>
	  			<li>Number of marks : <c:out value="${ interventionDAO.getNumberEvaluation(intervention.id) }" /></li>
	  			<li>Speaker mark : <fmt:formatNumber value="${ interventionDAO.getAVGSpeakerMarks(intervention.id) }" maxFractionDigits="1"/>/5</li>
	  			<li>Slide mark : <fmt:formatNumber value="${ interventionDAO.getAVGSlideMarks(intervention.id) }" maxFractionDigits="1"/>/5</li>
	  			<li>Global event mark : <fmt:formatNumber value="${ interventionDAO.getAVGAllMarks(intervention.id) }" maxFractionDigits="1"/>/5</li>
	  		</ul>
	  		
	  		<br /><br />
	  		<c:if test="${interventionDAO.getNumberEvaluation(intervention.id) != 0 }">
	  			<!-- Display the Chart only when the intervention have a minimum of 1 mark -->
	  			<div id="Graph" style="width: 800px; height: 400px; margin: 0 auto"></div>
	  		</c:if>
	  		
	  		<c:if test="${ empty userId }">
	  			<!-- Display the Evaluate Link only for Non-Speaker User -->
	  			<a href="#" id="evaluate">Evaluate !</a>
	  		</c:if>
	  		
	    </div>
		
		<%@ include file="/templates/footer.jsp" %>
	</body>
</html>
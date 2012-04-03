package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.dao.InterventionsDAO;
import com.supinfo.notetonsta.dao.SpeakersDAO;
import com.supinfo.notetonsta.entity.Campus;
import com.supinfo.notetonsta.entity.Interventions;
import com.supinfo.notetonsta.entity.Speakers;

/**
 * Servlet implementation class newInterventionServlet
 */
@WebServlet("/interventions/new")
public class newInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SpeakersDAO speakersDAO;
	private InterventionsDAO interventionDAO;
	private CampusDAO campusDAO;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		speakersDAO = DAOFactory.getInstance().getSpeakersDAO();
		campusDAO = DAOFactory.getInstance().getCampusDao();
		interventionDAO = DAOFactory.getInstance().getInterventionsDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve the campus List
		request.setAttribute("CampusList", campusDAO.getAllCampus());
		request.setAttribute("action", "new");
		request.getRequestDispatcher("/speakers/newUpdateIntervention.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Verification of fields and redirect with response if incorrect
		String subject = request.getParameter("subject");
		Long campus = Long.valueOf(request.getParameter("campus"));
		String fromPeriodStr = request.getParameter("fromPeriod");
		String toPeriodStr = request.getParameter("toPeriod");
		String description = request.getParameter("description");
		
		Boolean validate = true;

		if(subject.isEmpty()){
			validate = false;
			request.setAttribute("error_subject", "Subject is required !");
		}
		
		if(fromPeriodStr.isEmpty()){
			validate = false;
			request.setAttribute("error_fromPeriod", "Begin date is required !");
		}
		
		if(toPeriodStr.isEmpty()){
			validate = false;
			request.setAttribute("error_toPeriod", "End date is required !");
		}
		
		if(description.isEmpty()){
			validate = false;
			request.setAttribute("error_description", "description is required !");
		}

		if(validate){
			//Register the new Intervention
			Interventions newInterv = new Interventions();
			newInterv.setSubject(subject);
			
			//Cast the Dates
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date fromPeriod = null;
			try {
				fromPeriod = dateFormat.parse(fromPeriodStr);
			}
			catch (ParseException e) {}
			
			newInterv.setFromPeriod(fromPeriod);
			
			Date toPeriod = null;
			try {
				toPeriod = dateFormat.parse(toPeriodStr);
			} catch (ParseException e) {}
			
			newInterv.setToPeriod(toPeriod);
			newInterv.setDescription(description);
			
			//set the Campus
			Campus theCampus = campusDAO.getCampusById(campus);
			newInterv.setCampus(theCampus);
			
			//Set the Speaker
			Speakers theSpeaker = speakersDAO.getSpeakersById((Long) request.getSession().getAttribute("userId"));
			newInterv.setSpeaker(theSpeaker);
			
			//add the Intervention
			interventionDAO.addInterventions(newInterv);
			
			response.sendRedirect("../interventions/mine");
			
		}
		else{
			//Reafectation of values
			request.setAttribute("subject", subject);
			request.setAttribute("campus", campus);
			request.setAttribute("fromPeriod", fromPeriodStr);
			request.setAttribute("toPeriod", toPeriodStr);
			request.setAttribute("description", description);
			request.setAttribute("CampusList", campusDAO.getAllCampus());
			request.setAttribute("action", "new");
			
			//Redirect to Register Form
			request.getRequestDispatcher("/speakers/newUpdateIntervention.jsp").forward(request, response);
		}
	}

}

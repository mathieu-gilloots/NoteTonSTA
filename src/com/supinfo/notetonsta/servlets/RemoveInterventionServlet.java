package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.dao.InterventionsDAO;
import com.supinfo.notetonsta.entity.Interventions;


@WebServlet("/interventions/remove")
public class RemoveInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InterventionsDAO interventionDAO;
       
	@Override
	public void init() throws ServletException {
		interventionDAO = DAOFactory.getInstance().getInterventionsDAO();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.valueOf(request.getParameter("id"));
		
		//Verification if the speaker is the owner
		Interventions intervention = interventionDAO.getInterventionById(id);
		
		if(intervention.getSpeaker().getId().equals(request.getSession().getAttribute("userId"))){
			//Remove of the Intervention
			interventionDAO.removeIntervention(intervention);
		}
			//Redirect to Mine Intervention in all case
			request.getRequestDispatcher("/interventions/mine").forward(request, response);
		
	}

}

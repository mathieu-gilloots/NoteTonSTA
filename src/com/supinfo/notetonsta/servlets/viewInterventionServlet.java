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

@WebServlet("/viewIntervention")
public class viewInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	private InterventionsDAO InterventionDao;
	

	@Override
	public void init() throws ServletException {
		InterventionDao = DAOFactory.getInstance().getInterventionsDAO();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String IdParam = request.getParameter("id");
		Long id = Long.valueOf(IdParam);
				
		//Retrieve the Intervention, if null, redirect to Home
		Interventions intervention = InterventionDao.getInterventionById(id);
		
		if(intervention==null){
			response.sendRedirect(getServletContext().getContextPath() + "/listCampus");
		}
		else{
			
			//Intervention exist, so, Display details
			request.setAttribute("intervention", intervention);
			request.setAttribute("interventionDAO", InterventionDao);
			
			//Sent the parameter stringChart.
			request.setAttribute("StringChart", InterventionDao.getStringChart(intervention.getId()));
			
			
			request.getRequestDispatcher("/viewIntervention.jsp").forward(request, response);
		}
		
	}

}

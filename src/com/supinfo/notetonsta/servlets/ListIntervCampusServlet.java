package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.dao.InterventionsDAO;
import com.supinfo.notetonsta.entity.Campus;


@WebServlet("/ListIntervCampus")
public class ListIntervCampusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	private InterventionsDAO InterventionsDao;
	private CampusDAO CampusDao;

	@Override
	public void init() throws ServletException {
		InterventionsDao = DAOFactory.getInstance().getInterventionsDAO();
		CampusDao = DAOFactory.getInstance().getCampusDao();
	}
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(getServletContext().getContextPath() + "/listCampus");
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Return the list of Interventions
		String campusIdParam = request.getParameter("CampusId");
		Long campusId = Long.valueOf(campusIdParam);
		if(campusId!=null){
			
			Campus campus = CampusDao.getCampusById(campusId);
			
			//Affect the List
			request.setAttribute("InterventionsList", campus.getInterventions());
			request.setAttribute("Campus", campus );
			
			//Dispatch to the JSP
			request.getRequestDispatcher("/listIntervCampus.jsp").forward(request, response);
		}
		else{
			response.sendRedirect(getServletContext().getContextPath() + "/listCampus");
		}
	}

}

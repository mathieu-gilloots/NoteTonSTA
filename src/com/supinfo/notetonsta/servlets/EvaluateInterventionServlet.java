package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.dao.InterventionsDAO;
import com.supinfo.notetonsta.dao.MarksDAO;
import com.supinfo.notetonsta.entity.Interventions;
import com.supinfo.notetonsta.entity.Marks;


@WebServlet("/EvaluateIntervention")
public class EvaluateInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private InterventionsDAO InterventionsDao;
	private MarksDAO MarksDao;
	
	@Override
	public void init() throws ServletException {
		InterventionsDao = DAOFactory.getInstance().getInterventionsDAO();
		MarksDao = DAOFactory.getInstance().getMarksDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(getServletContext().getContextPath() + "/listCampus");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//If we've a Form posted
		Long interventionId = Long.valueOf(request.getParameter("interventionId"));
		Long idBooster = Long.valueOf(request.getParameter("idBooster"));
		Float speakerQ1Mark = Float.valueOf(request.getParameter("speakerQ1Mark"));
		Float speakerQ2Mark = Float.valueOf(request.getParameter("speakerQ2Mark"));
		Float speakerQ3Mark = Float.valueOf(request.getParameter("speakerQ3Mark"));
		Float slidesQ1Mark = Float.valueOf(request.getParameter("slidesQ1Mark"));
		Float slidesQ2Mark = Float.valueOf(request.getParameter("slidesQ2Mark"));
		Float slidesQ3Mark = Float.valueOf(request.getParameter("slidesQ3Mark"));
		String comments = request.getParameter("comments");
		
		//If no value : value to 1
		if(speakerQ1Mark==null){speakerQ1Mark=(float) 1;}
		if(speakerQ2Mark==null){speakerQ2Mark=(float) 1;}
		if(speakerQ3Mark==null){speakerQ3Mark=(float) 1;}
		if(slidesQ1Mark==null){slidesQ1Mark=(float) 1;}
		if(slidesQ2Mark==null){slidesQ2Mark=(float) 1;}
		if(slidesQ3Mark==null){slidesQ3Mark=(float) 1;}
		
		//Retrieving the Intervention
		Interventions curIntervention =InterventionsDao.getInterventionById(interventionId);
		
		//Creation of new Mark
		Marks newMark = new Marks();
		newMark.setIdBooster(idBooster);
		newMark.setIntervention(curIntervention);
		newMark.setSpeakerQ1Mark(speakerQ1Mark);
		newMark.setSpeakerQ2Mark(speakerQ2Mark);
		newMark.setSpeakerQ3Mark(speakerQ3Mark);
		newMark.setSlidesQ1Mark(slidesQ1Mark);
		newMark.setSlidesQ2Mark(slidesQ2Mark);
		newMark.setSlidesQ3Mark(slidesQ3Mark);
		newMark.setComments(comments);
		
		//Add the Mark
		MarksDao.addMark(newMark);
		
		response.sendRedirect(getServletContext().getContextPath() + "/viewIntervention?id=" + interventionId);
		
	}

}

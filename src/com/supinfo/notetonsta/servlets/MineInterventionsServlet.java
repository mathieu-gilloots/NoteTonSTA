package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.dao.InterventionsDAO;
import com.supinfo.notetonsta.dao.SpeakersDAO;
import com.supinfo.notetonsta.entity.Interventions;
import com.supinfo.notetonsta.entity.Speakers;

@WebServlet("/interventions/mine")
public class MineInterventionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SpeakersDAO SpeakerDao;
	private InterventionsDAO InterventionDao;

	@Override
	public void init() throws ServletException {
		SpeakerDao = DAOFactory.getInstance().getSpeakersDAO();
		InterventionDao = DAOFactory.getInstance().getInterventionsDAO();
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId =  (Long) request.getSession().getAttribute("userId");
		Speakers speaker = SpeakerDao.getSpeakersById(userId);

		List<Interventions> MineInterventions = speaker.getInterventions();
		
		
		request.setAttribute("InterventionsList", MineInterventions);
		
		//Send the InterventionDAO for the foreach statement
		request.setAttribute("InterventionDAO", InterventionDao);
		
		request.getRequestDispatcher("/speakers/mineInterventions.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

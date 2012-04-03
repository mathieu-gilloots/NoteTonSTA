package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.entity.Campus;


@WebServlet("/listCampus")
public class ListCampusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CampusDAO CampusDao;
	private String list_campus[] = {"Paris", "Marseille", "Nice", "Lille", "Toulouse"};
	

	@Override
	public void init() throws ServletException {
		CampusDao = DAOFactory.getInstance().getCampusDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Add somes campus if not exist
		for (int i = 0; i < list_campus.length; i++) {
			if(CampusDao.getCampusByName(list_campus[i]) == null){
				Campus campus = new Campus(list_campus[i]);
				campus = CampusDao.addCampus(campus);
			}
		}
		
		List<Campus> lescampus = CampusDao.getAllCampus();
		request.setAttribute("lescampus", lescampus);
		request.getRequestDispatcher("listCampus.jsp").forward(request, response);
	}

}

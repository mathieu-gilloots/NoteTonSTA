package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.entity.Speakers;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Verification if Identity
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		//Login Speaker with Login method
		Speakers speaker = DAOFactory.getInstance().getSpeakersDAO().LoginSpeaker(email, password);
		
		
		if(speaker != null){
			req.getSession().setAttribute("userId", speaker.getId());
			//Stock of First and LastName for welcome message (Welcome FirstName LastName)
			req.getSession().setAttribute("FirstName", speaker.getFirstName());
			req.getSession().setAttribute("LastName", speaker.getLastName());
			resp.sendRedirect(getServletContext().getContextPath() + "/interventions/mine");
		}
		
		else{
			req.setAttribute("error", "Invalid Login !");
			resp.sendRedirect(getServletContext().getContextPath() + "/login.jsp?error=invalidLogin");
			return;
		}
		

		
		
		
	}

}

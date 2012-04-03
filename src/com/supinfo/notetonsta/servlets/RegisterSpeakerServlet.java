package com.supinfo.notetonsta.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.DAOFactory;
import com.supinfo.notetonsta.dao.SpeakersDAO;
import com.supinfo.notetonsta.entity.Speakers;


@WebServlet("/speakers/register")
public class RegisterSpeakerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SpeakersDAO speakersDAO;
	
	
	public void init(ServletConfig config) throws ServletException {
		speakersDAO = DAOFactory.getInstance().getSpeakersDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Verification of fields and redirect with response if incorrect
		String FirstName = (String) request.getParameter("FirstName");
		String LastName = (String) request.getParameter("LastName");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String passwordConfirm = (String) request.getParameter("passwordConfirm");
		
		Boolean validate = true;

		if(FirstName.isEmpty()){
			validate = false;
			request.setAttribute("error_FirstName", "FirstName is required !");
		}
		
		if(LastName.isEmpty()){
			validate = false;
			request.setAttribute("error_LastName", "LastName is required !");
		}
		
		//Email Validation
		
		if(email.isEmpty()){
			validate = false;
			request.setAttribute("error_email", "email is required !");
		}
		
		else if(!Pattern.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", email)){
			validate = false;
			request.setAttribute("error_email", "email is invalid !");
		}
		else if(speakersDAO.getSpeakerByEmail(email) != null){
			//If speaker already exist
			request.setAttribute("error_email", "This email already exist in database !");
		}
		
		if(password.isEmpty()){
			validate = false;
			request.setAttribute("error_password", "password is required !");
		}
		
		//Password Confirmation
		
		if(passwordConfirm.isEmpty()){
			validate = false;
			request.setAttribute("error_passwordConfirm", "password confirmation is required !");
		}
		
		else if(!passwordConfirm.equals(password)){
			validate = false;
			request.setAttribute("error_passwordConfirm", "Confirmation is not the same !");
		}
		
		if(validate){
			//Register Speaker
			Speakers speaker = new Speakers();
			speaker.setFirstName(FirstName);
			speaker.setLastName(LastName);
			speaker.setEmail(email);
			speaker.setPassword(speakersDAO.SHAPassword(password));
			
			//add this speaker
			speakersDAO.addSpeakers(speaker);
			
			response.sendRedirect("../login");
			
		}
		else{
			//Reafectation of values
			request.setAttribute("FirstName", FirstName);
			request.setAttribute("LastName", LastName);
			request.setAttribute("email", email);
			
			//Redirect to Register Form
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
		
	}

}

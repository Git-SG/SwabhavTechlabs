package com.aurionpro.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.data.BankDbUtil;

@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProfileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("email") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
		RequestDispatcher dispatcher = null;
		BankDbUtil bankDbUtil = new BankDbUtil();
		
		bankDbUtil.connectToDB();
		
		String email = (String)session.getAttribute("email");
		String firstName = bankDbUtil.getFirstName(email);
		String lastName = bankDbUtil.getLastName(email);
		
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		
		dispatcher = request.getRequestDispatcher("EditProfile.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

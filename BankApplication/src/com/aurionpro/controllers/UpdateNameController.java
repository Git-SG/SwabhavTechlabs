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

@WebServlet("/UpdateNameController")
public class UpdateNameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateNameController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		BankDbUtil bankDbUtil = new BankDbUtil();
		
		HttpSession session = request.getSession(false);
		String email = (String)session.getAttribute("email");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		bankDbUtil.connectToDB();
		bankDbUtil.UpdateName(firstName, lastName, email);
		
		firstName = bankDbUtil.getFirstName(email);
		lastName = bankDbUtil.getLastName(email);
		
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);

		dispatcher = request.getRequestDispatcher("CustomerHome.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

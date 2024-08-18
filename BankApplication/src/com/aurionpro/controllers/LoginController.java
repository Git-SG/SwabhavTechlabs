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

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		BankDbUtil bankDbUtil = new BankDbUtil();
		
		String email = request.getParameter("email").toLowerCase();
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		bankDbUtil.connectToDB();
		
		if(bankDbUtil.validateCredentials(email, password, role)) {
			HttpSession session = request.getSession();
	        session.setAttribute("email", email); 
	        session.setAttribute("password", password);
			session.setAttribute("role", role);
			
			if(role.equals("ADMIN")) {
				dispatcher = request.getRequestDispatcher("AdminHome.jsp");
			}
			if(role.equals("CUSTOMER")) {
				String firstName = bankDbUtil.getFirstName(email);
				request.setAttribute("firstName", firstName);
				String lastName = bankDbUtil.getLastName(email);
				request.setAttribute("lastName", lastName);
				dispatcher = request.getRequestDispatcher("CustomerHome.jsp");
			}
		}
		
		else {
			request.setAttribute("loginFailed", "Invalid credentials. Try again");
			dispatcher = request.getRequestDispatcher("Login.jsp");
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

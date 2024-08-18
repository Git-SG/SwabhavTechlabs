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

@WebServlet("/UpdatePasswordController")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdatePasswordController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
		RequestDispatcher dispatcher = null;
		BankDbUtil bankDbUtil = new BankDbUtil();
		
		HttpSession session = request.getSession(false);
		String email = (String)session.getAttribute("email");
		String password = request.getParameter("newPassword");
		
		bankDbUtil.connectToDB();
		bankDbUtil.changePassword(password, email);
		
		if (session != null) {
	        session.invalidate(); 
	    }
		
		dispatcher = request.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.aurionpro.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.data.BankDbUtil;
import com.aurionpro.entity.Customer;

@WebServlet("/SubmitNewCustomerController")
public class SubmitNewCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SubmitNewCustomerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("email") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
		BankDbUtil bankDbUtil = new BankDbUtil();
		RequestDispatcher dispatcher = null;
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email").toLowerCase();
		String password = request.getParameter("password");
		
		bankDbUtil.connectToDB();

	    if (bankDbUtil.isEmailExists(email)) {
	        request.setAttribute("message", "Email already exists");
	        request.setAttribute("messageType", "error");
	    } else {
	        bankDbUtil.addCustomer(firstName, lastName, email, password);
	        request.setAttribute("message", "Customer added successfully");
	        request.setAttribute("messageType", "success");
	    }
		
		dispatcher = request.getRequestDispatcher("AddNewCustomer.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

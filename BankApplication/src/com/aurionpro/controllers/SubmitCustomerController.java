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

@WebServlet("/SubmitCustomerController")
public class SubmitCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SubmitCustomerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		BankDbUtil bankDbUtil = new BankDbUtil();
		
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("email") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
		
		String customerId = request.getParameter("customerId");
		
		bankDbUtil.connectToDB();
		Customer customer = bankDbUtil.getCustomerById(Integer.parseInt(customerId));
		request.setAttribute("customer", customer);
		
		List<Customer> customers = bankDbUtil.getCustomerList();
		request.setAttribute("customers", customers);
		
		List<Customer> customersWithoutAccounts = bankDbUtil.getCustomerWithoutAccountsList();
		request.setAttribute("customersWithoutAccounts", customersWithoutAccounts);
		
		dispatcher = request.getRequestDispatcher("AddBankAccount.jsp");
		dispatcher.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

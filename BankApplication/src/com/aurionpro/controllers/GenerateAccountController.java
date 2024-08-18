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
import com.aurionpro.entity.CustomerInfo;

@WebServlet("/GenerateAccountController")
public class GenerateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GenerateAccountController() {
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
		
		String balance = request.getParameter("initialBalance");
		String customerId = request.getParameter("customerId");
		
		bankDbUtil.connectToDB();

	
		if (Integer.parseInt(balance) < 0) {
		    session.setAttribute("notificationMessage", "Initial Balance cannot be less than zero, Account creation failed");
		    session.setAttribute("notificationType", "error");
		    List<Customer> customers = bankDbUtil.getCustomerList();
			request.setAttribute("customers", customers);
			
			List<Customer> customersWithoutAccounts = bankDbUtil.getCustomerWithoutAccountsList();
			request.setAttribute("customersWithoutAccounts", customersWithoutAccounts);
		    dispatcher = request.getRequestDispatcher("AddBankAccount.jsp");
			dispatcher.forward(request, response);
		    return;
		} else {
		    session.setAttribute("notificationMessage", "Account creation successful");
		    session.setAttribute("notificationType", "success");
		}
		
		
		bankDbUtil.addAccount(Integer.parseInt(customerId), Double.parseDouble(balance));
		
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

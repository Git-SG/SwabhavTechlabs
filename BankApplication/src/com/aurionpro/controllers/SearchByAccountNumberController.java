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
import com.aurionpro.entity.CustomerInfo;


@WebServlet("/SearchByAccountNumberController")
public class SearchByAccountNumberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SearchByAccountNumberController() {
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
	    
	    String accountNumber = request.getParameter("accountNumber");
		
		bankDbUtil.connectToDB();
		List<CustomerInfo> customerInfoList = bankDbUtil.searchAccountNumber(Integer.parseInt(accountNumber));
		request.setAttribute("customerInfoList", customerInfoList);
		
		dispatcher = request.getRequestDispatcher("ViewCustomers.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

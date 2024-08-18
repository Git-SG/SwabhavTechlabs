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
import com.aurionpro.entity.Transaction;

@WebServlet("/SearchTransactionByAccountNumberController")
public class SearchTransactionByAccountNumberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchTransactionByAccountNumberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		BankDbUtil bankUtilDb = new BankDbUtil();
		
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("email") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
	    
	    int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		
		bankUtilDb.connectToDB();
		List<Transaction> transactionList = bankUtilDb.searchByAccountNumber(accountNumber);
		request.setAttribute("transactionList", transactionList);
		
		dispatcher = request.getRequestDispatcher("ViewTransactions.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

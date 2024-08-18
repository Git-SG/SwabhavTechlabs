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

@WebServlet("/SearchByTransactionTypeController")
public class SearchByTransactionTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchByTransactionTypeController() {
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
	    
	    String transactionType = request.getParameter("transactionType");
		
		bankUtilDb.connectToDB();
		List<Transaction> transactionList = bankUtilDb.searchByTransactionType(transactionType);
		request.setAttribute("transactionList", transactionList);
		
		dispatcher = request.getRequestDispatcher("ViewTransactions.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

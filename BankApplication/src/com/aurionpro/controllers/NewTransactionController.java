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
import com.aurionpro.entity.Account;

@WebServlet("/NewTransactionController")
public class NewTransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewTransactionController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("email") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
	    
	    String email = (String)session.getAttribute("email");
		RequestDispatcher dispatcher = null;
		BankDbUtil bankDbUtil = new BankDbUtil();
		
		if (session.getAttribute("notificationMessage") != null) {
	        session.removeAttribute("notificationMessage");
	        session.removeAttribute("notificationType");
	    }
		
		bankDbUtil.connectToDB();
		List<Account> accountList = bankDbUtil.getAccountsOfCustomer(email);
		request.setAttribute("accountList", accountList);
		dispatcher = request.getRequestDispatcher("NewTransaction.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

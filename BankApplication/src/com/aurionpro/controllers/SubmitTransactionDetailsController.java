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

@WebServlet("/SubmitTransactionDetailsController")
public class SubmitTransactionDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubmitTransactionDetailsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("email") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		RequestDispatcher dispatcher = null;
		BankDbUtil bankDbUtil = new BankDbUtil();

		String email = (String) session.getAttribute("email");
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));

		int senderAccountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		String transactionType = request.getParameter("transactionType");

		bankDbUtil.connectToDB();

		if ("CREDIT".equalsIgnoreCase(transactionType)) {
			double amount = Double.parseDouble(request.getParameter("amount"));
			if (amount < 0) {
				session.setAttribute("notificationMessage", "Amount cannot be less than zero, Transaction failed");
				session.setAttribute("notificationType", "error");
				List<Account> accountList = bankDbUtil.getAccountsOfCustomer(email);
				request.setAttribute("accountList", accountList);
				dispatcher = request.getRequestDispatcher("NewTransaction.jsp");
				dispatcher.forward(request, response);
				return;
			} 
			bankDbUtil.creditAmount(accountNumber, amount);
		} else if ("DEBIT".equalsIgnoreCase(transactionType)) {
			double amount = Double.parseDouble(request.getParameter("amount"));
			if (amount < 0) {
				session.setAttribute("notificationMessage", "Amount cannot be less than zero, Transaction failed");
				session.setAttribute("notificationType", "error");
				List<Account> accountList = bankDbUtil.getAccountsOfCustomer(email);
				request.setAttribute("accountList", accountList);
				dispatcher = request.getRequestDispatcher("NewTransaction.jsp");
				dispatcher.forward(request, response);
				return;
			} 
			bankDbUtil.debitAmount(accountNumber, amount);
			
		} else if ("TRANSFER".equalsIgnoreCase(transactionType)) {
			int receiverAccountNumber = Integer.parseInt(request.getParameter("toAccountNumber"));
			double transferAmount = Double.parseDouble(request.getParameter("transferAmount"));
			bankDbUtil.transferAmount(senderAccountNumber, receiverAccountNumber, transferAmount);
		}

		session.setAttribute("notificationMessage", "Transaction successful");
		session.setAttribute("notificationType", "success");

		dispatcher = request.getRequestDispatcher("NewTransaction.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

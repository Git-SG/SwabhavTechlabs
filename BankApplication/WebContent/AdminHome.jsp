<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%  
	if (session == null || session.getAttribute("email") == null){
	    response.sendRedirect("Login.jsp");
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
	    return;
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">

    <nav class="bg-blue-500 text-white py-4">
        <div class="container mx-auto flex justify-between items-center">
            <div class="text-lg font-semibold">
                Admin Dashboard
            </div>
            <ul class="flex space-x-6">
                <li class="relative group">
                    <a href="AddNewCustomerController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        Add New Customer
                    </a>
                </li>
                <li class="relative group">
                    <a href="AddBankAccountController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        Add Bank Account
                    </a>
                </li>
                <li class="relative group">
                    <a href="ViewCustomersController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        View Customers
                    </a>
                </li>
                <li class="relative group">
                    <a href="ViewTransactionsController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        View Transactions
                    </a>
                </li>
                <li class="relative group">
                    <a href="LogoutController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        Logout
                    </a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mx-auto mt-8">
        <h1 class="text-3xl font-bold text-center">Welcome to the Admin Dashboard</h1>
        <p class="text-center mt-4 text-gray-700">Use the navigation bar above to manage customers, accounts, and transactions.</p>
    </div>

</body>
</html>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function validateForm() {
            const firstName = document.getElementById('firstName').value;
            const lastName = document.getElementById('lastName').value;
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const errorSpan = document.getElementById('errorSpan');
            const passwordPattern = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;


            if (password !== confirmPassword) {
                errorSpan.textContent = "Passwords do not match!";
                errorSpan.style.display = "block";
                return false; 
            }

            const namePattern = /^[A-Za-z]+$/;
            if (!namePattern.test(firstName)) {
                errorSpan.textContent = "First Name can only contain alphabets!";
                errorSpan.style.display = "block";
                return false;
            }
            if (!namePattern.test(lastName)) {
                errorSpan.textContent = "Last Name can only contain alphabets!";
                errorSpan.style.display = "block";
                return false;
            }
            
            if (!passwordPattern.test(password)) {
                errorSpan.textContent = "Password must be at least 8 characters long, include at least one uppercase letter, one number, and one special character.";
                errorSpan.style.display = "block";
                return false;
            }
            
            errorSpan.style.display = "none";
            return true;
        }
    </script>
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
        <h1 class="text-3xl font-bold text-center mb-8">Add New Customer</h1>
        
        <form action="SubmitNewCustomerController" method="post" class="bg-white p-8 rounded shadow-md w-full max-w-md mx-auto" onsubmit="return validateForm()">
            <div class="mb-4">
                <label for="firstName" class="block text-gray-700 text-sm font-bold mb-2">First Name:</label>
                <input type="text" id="firstName" name="firstName" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>
            
            <div class="mb-4">
                <label for="lastName" class="block text-gray-700 text-sm font-bold mb-2">Last Name:</label>
                <input type="text" id="lastName" name="lastName" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>
            
            <div class="mb-4">
                <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Email:</label>
                <input type="email" id="email" name="email" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>
            
            <div class="mb-4">
                <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password:</label>
                <input type="password" id="password" name="password" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>

            <div class="mb-4">
                <label for="confirmPassword" class="block text-gray-700 text-sm font-bold mb-2">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" required>
                <span id="errorSpan" class="text-red-500 text-sm mt-1" style="display:none;"></span>
            </div>
            
            <c:choose>
                <c:when test="${not empty message}">
                    <c:choose>
                        <c:when test="${messageType == 'success'}">
                            <div class="bg-green-100 text-green-700 p-4 rounded mt-4">
                                ${message}
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="bg-red-100 text-red-700 p-4 rounded mt-4">
                                ${message}
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
            
            <div class="flex items-center justify-between">
                <input type="submit" value="Submit" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-blue-400">
                <input type="reset" value="Cancel" class="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-red-400">
            </div>
        </form>
    </div>

</body>
</html>


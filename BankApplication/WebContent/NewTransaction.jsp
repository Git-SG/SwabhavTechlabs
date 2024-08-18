<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    
    String notificationMessage = (String) session.getAttribute("notificationMessage");
	String notificationType = (String) session.getAttribute("notificationType");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
	<script>
	function validateForm() {
		var amount = document.getElementById('amount').value;
		var notificationBox = document.getElementById('notification-box');

		if (amount < 0) {
			notificationBox.innerText = 'Amount cannot be less than zero, transaction failed';
			notificationBox.classList.remove('bg-green-500');
			notificationBox.classList.add('bg-red-500');
			notificationBox.style.display = 'block';
			return false; 
		} else {
			notificationBox.innerText = 'Transaction successful';
			notificationBox.classList.remove('bg-red-500');
			notificationBox.classList.add('bg-green-500');
			notificationBox.style.display = 'block';
			return true; 
		}
	}
</script>
</head>
<body class="bg-gray-100">

    <!-- Navbar -->
    <nav class="bg-blue-500 text-white py-4">
        <div class="container mx-auto flex justify-between items-center">
            <div class="text-lg font-semibold">
                Customer Dashboard
            </div>
            <ul class="flex space-x-6">
                <li class="relative group">
                    <a href="ViewPassbookController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        View Passbook
                    </a>
                </li>
                <li class="relative group">
                    <a href="NewTransactionController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        New Transaction
                    </a>
                </li>
                <li class="relative group">
                    <a href="EditProfileController" class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
                        Edit Profile
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
    
    <c:if test="${not empty notificationMessage}">
		<div class="container mx-auto mt-4 text-center">
			<div
				class="${notificationType == 'success' ? 'bg-green-100 border border-green-400 text-green-700' : 'bg-red-100 border border-red-400 text-red-700'} px-4 py-3 rounded relative">
				<span class="block sm:inline">${notificationMessage}</span>
			</div>
		</div>
	</c:if>

    <div class="container mx-auto mt-8">
        <h1 class="text-3xl font-bold text-center mb-8">New Transaction</h1>

        <div class="bg-white p-6 rounded-lg shadow-md mb-6 max-w-md mx-auto">
            <form id="accountForm" action="SubmitAccountForTransactionController" method="post">
                <div class="mb-4">
                    <label for="accountNumber" class="block text-gray-700">Select Account Number:</label>
                    <select id="accountNumber" name="accountNumber" class="form-select mt-1 block w-full border-gray-300 rounded-md" required>
                        <option value="" disabled selected>Select your account</option>
                        <c:forEach var="account" items="${accountList}">
                            <option value="${account.accountNumber}">${account.accountNumber}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="flex justify-center">
                    <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Submit</button>
                </div>
            </form>
        </div>

        <c:if test="${not empty accountNumber}">
        <div class="bg-white p-6 rounded-lg shadow-md mb-6 max-w-md mx-auto">
                <h2 class="text-xl font-semibold mb-4">Account Information</h2>
                <table class="table-auto w-full text-left">
                    <thead>
                        <tr>
                            <th class="px-4 py-2">Account Number</th>
                            <th class="px-4 py-2">Balance</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="border px-4 py-2"><c:out value="${accountNumber}"/></td>
                            <td class="border px-4 py-2"><c:out value="${balance}"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
        <div class="bg-white p-6 rounded-lg shadow-md mb-6 max-w-md mx-auto">
            <form id="transactionForm" action="SubmitTransactionDetailsController" method="post">
                <input type="hidden" name="accountNumber" value="${accountNumber}" />
                <h2 class="text-xl font-semibold mb-4">Transaction Details</h2>
                <div class="mb-4">
                    <label for="transactionType" class="block text-gray-700">Transaction Type:</label>
                    <select id="transactionType" name="transactionType" class="form-select mt-1 block w-full border-gray-300 rounded-md" onchange="toggleTransactionFields()" required>
                        <option value="" disabled selected>Select transaction type</option>
                        <option value="CREDIT">CREDIT</option>
                        <option value="DEBIT">DEBIT</option>
                        <option value="TRANSFER">TRANSFER</option>
                    </select>
                </div>
                <div id="transactionDetails" class="hidden">
                    <div id="amountField" class="flex items-center mb-4 hidden">
                        <label for="amount" class="block text-gray-700 w-1/3">Amount:</label>
                        <input type="number" id="amount" name="amount" class="form-input mt-1 block w-2/3 bg-gray-200 border-gray-300 rounded-md" />
                    </div>
                    <div id="transferFields" class="space-y-4 hidden">
                        <div class="flex items-center">
                            <label for="toAccountNumber" class="block text-gray-700 w-1/3">To Account Number:</label>
                            <input type="number" id="toAccountNumber" name="toAccountNumber" class="form-input mt-1 block w-2/3 bg-gray-200 border-gray-300 rounded-md" />
                        </div>
                        <div class="flex items-center">
                            <label for="transferAmount" class="block text-gray-700 w-1/3">Amount:</label>
                            <input type="number" id="transferAmount" name="transferAmount" class="form-input mt-1 block w-2/3 bg-gray-200 border-gray-300 rounded-md" />
                        </div>
                    </div>
                    <div class="flex justify-center space-x-4 mt-6">
                        <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Submit</button>
                        <button type="button" class="px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600" onclick="cancelTransaction()">Cancel</button>
                    </div>
                </div>
            </form>
        </div>
        </c:if>
    </div>

    <script>
        function toggleTransactionFields() {
            var transactionType = document.getElementById("transactionType").value;
            var amountField = document.getElementById("amountField");
            var transferFields = document.getElementById("transferFields");
            var transactionDetails = document.getElementById("transactionDetails");

            if (transactionType === "CREDIT" || transactionType === "DEBIT") {
                amountField.classList.remove("hidden");
                transferFields.classList.add("hidden");
            } else if (transactionType === "TRANSFER") {
                amountField.classList.add("hidden");
                transferFields.classList.remove("hidden");
            }
            transactionDetails.classList.remove("hidden");
        }

        function cancelTransaction() {
            document.getElementById("transactionType").value = "";
            document.getElementById("amountField").classList.add("hidden");
            document.getElementById("transferFields").classList.add("hidden");
            document.getElementById("transactionDetails").classList.add("hidden");
        }
    </script>
</body>
</html>


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
    <title>View Transactions</title>
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
        <h1 class="text-3xl font-bold text-center mb-8 text-black">Transaction Details</h1>
		
		
        <div class="flex space-x-4 mb-8">
            <div class="flex-1">
                <form action="SearchByTransactionIdController" method="get" class="bg-white p-4 border border-gray-300 rounded-lg shadow-sm">
                    <label for="transactionId" class="block text-sm font-medium text-gray-700">Search by Transaction ID</label>
                    <input type="number" id="transactionId" name="transactionId" class="mt-1 block w-full p-2 border border-gray-300 rounded-md" placeholder="Enter Transaction ID">
                    <button type="submit" class="mt-2 w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">Search</button>
                </form>
            </div>

            <div class="flex-1">
                <form action="SearchTransactionByAccountNumberController" method="get" class="bg-white p-4 border border-gray-300 rounded-lg shadow-sm">
                    <label for="accountNumber" class="block text-sm font-medium text-gray-700">Search by Account Number</label>
                    <input type="number" id="accountNumber" name="accountNumber" class="mt-1 block w-full p-2 border border-gray-300 rounded-md" placeholder="Enter Account Number">
                    <button type="submit" class="mt-2 w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">Search</button>
                </form>
            </div>

            <div class="flex-1">
                <form action="SearchByTransactionTypeController" method="get" class="bg-white p-4 border border-gray-300 rounded-lg shadow-sm">
                    <label for="transactionType" class="block text-sm font-medium text-gray-700">Search by Transaction Type</label>
                    <select id="transactionType" name="transactionType" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        <option value="CREDIT">CREDIT</option>
                        <option value="DEBIT">DEBIT</option>
                    </select>
                    <button type="submit" class="mt-2 w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">Search</button>
                </form>
            </div>
        </div>
		
        <table class="min-w-full bg-white border border-gray-300">
            <thead class="bg-blue-500">
                <tr>
                    <th class="px-4 py-2 border border-gray-300 text-white">Transaction ID</th>
                    <th class="px-4 py-2 border border-gray-300 text-white">Sender Account</th>
                    <th class="px-4 py-2 border border-gray-300 text-white">Receiver Account</th>
                    <th class="px-4 py-2 border border-gray-300 text-white">Transaction Type</th>
                    <th class="px-4 py-2 border border-gray-300 text-white">Amount</th>
                    <th class="px-4 py-2 border border-gray-300 text-white">Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="transaction" items="${transactionList}">
                    <tr>
                        <td class="px-4 py-2 border border-gray-300"><c:out value="${transaction.transactionId}" /></td>
                        <td class="px-4 py-2 border border-gray-300">
                            <c:choose>
                                <c:when test="${transaction.transactionType == 'CREDIT'}">-</c:when>
                                <c:otherwise><c:out value="${transaction.senderAccountNumber}" /></c:otherwise>
                            </c:choose>
                        </td>
                        <td class="px-4 py-2 border border-gray-300">
                            <c:choose>
                                <c:when test="${transaction.transactionType == 'DEBIT'}">-</c:when>
                                <c:otherwise><c:out value="${transaction.receiverAccountNumber}" /></c:otherwise>
                            </c:choose>
                        </td>
                        <td class="px-4 py-2 border border-gray-300"><c:out value="${transaction.transactionType}" /></td>
                        <td class="px-4 py-2 border border-gray-300"><c:out value="${transaction.amount}" /></td>
                        <td class="px-4 py-2 border border-gray-300"><c:out value="${transaction.date}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>

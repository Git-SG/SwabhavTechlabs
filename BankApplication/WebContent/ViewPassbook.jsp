<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <title>View Passbook</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">

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

    <div class="container mx-auto mt-8">
        <h1 class="text-3xl font-bold text-center mb-8">View Passbook</h1>

        <div class="bg-white p-6 rounded-lg shadow-md mb-6">
            <form action="SubmitAccountController" method="post">
                <div class="mb-4">
                    <label for="accountNumber" class="block text-gray-700">Select Account Number:</label>
                    <select id="accountNumber" name="accountNumber" class="form-select mt-1 block w-full">
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
            <div class="bg-white p-6 rounded-lg shadow-md mb-6">
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

            <div class="bg-white p-6 rounded-lg shadow-md">
                <h2 class="text-xl font-semibold mb-4">Transaction History</h2>
                <table class="table-auto w-full text-left">
                    <thead>
                        <tr>
                            <th class="px-4 py-2">Transaction ID</th>
                            <th class="px-4 py-2">Sender Account</th>
                            <th class="px-4 py-2">Receiver Account</th>
                            <th class="px-4 py-2">Transaction Type</th>
                            <th class="px-4 py-2">Amount</th>
                            <th class="px-4 py-2">Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transaction" items="${transactionList}">
                            <tr>
                                <td class="border px-4 py-2"><c:out value="${transaction.transactionId}"/></td>
             
                                <td class="border px-4 py-2">
                                    <c:choose>
                                        <c:when test="${transaction.transactionType == 'CREDIT' && transaction.senderAccountNumber == transaction.receiverAccountNumber}">
                                            -
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${transaction.senderAccountNumber}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="border px-4 py-2">
                                    <c:choose>
                                        <c:when test="${transaction.transactionType == 'DEBIT' && transaction.senderAccountNumber == transaction.receiverAccountNumber}">
                                            -
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${transaction.receiverAccountNumber}"/>
                               
                                        </c:otherwise>
                                    </c:choose>
                                </td>                          
                               <!--
                               <td class="border px-4 py-2"><c:out value="${transaction.transactionType}"/></td> 
                               -->
                               <td class="border px-4 py-2">
                                    <c:choose>
                                        <c:when test="${transaction.transactionType == 'TRANSFER' && transaction.senderAccountNumber == accountNumber}">
                                            DEBIT
                                        </c:when>
                                        <c:when test="${transaction.transactionType == 'TRANSFER' && transaction.receiverAccountNumber == accountNumber}">
                                            CREDIT
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${transaction.transactionType}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="border px-4 py-2"><c:out value="${transaction.amount}"/></td>
                                <td class="border px-4 py-2"><c:out value="${transaction.date}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<title>Add Bank Account</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<script>
	function validateForm() {
		varinitialBalance = document.getElementById('initialBalance').value;
		varnotificationBox = document.getElementById('notification-box');

		if (initialBalance < 0) {
			notificationBox.innerText = 'Initial Balance cannot be less than zero, Account creation failed';
			notificationBox.classList.remove('bg-green-500');
			notificationBox.classList.add('bg-red-500');
			notificationBox.style.display = 'block';
			returnfalse; 
		} else {
			notificationBox.innerText = 'Account creation successful';
			notificationBox.classList.remove('bg-red-500');
			notificationBox.classList.add('bg-green-500');
			notificationBox.style.display = 'block';
			returntrue; 
		}
	}
</script>
</head>
<body class="bg-gray-100">

	<nav class="bg-blue-500 text-white py-4">
		<div class="container mx-auto flex justify-between items-center">
			<div class="text-lg font-semibold">Admin Dashboard</div>
			<ul class="flex space-x-6">
				<li class="relative group"><a href="AddNewCustomerController"
					class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
						Add New Customer </a></li>
				<li class="relative group"><a href="AddBankAccountController"
					class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
						Add Bank Account </a></li>
				<li class="relative group"><a href="ViewCustomersController"
					class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
						View Customers </a></li>
				<li class="relative group"><a href="ViewTransactionsController"
					class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
						View Transactions </a></li>
				<li class="relative group"><a href="LogoutController"
					class="px-4 py-2 border border-white rounded-lg text-white hover:bg-blue-600 hover:text-gray-100">
						Logout </a></li>
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
		<h1 class="text-3xl font-bold text-center mb-8">Add Bank Account</h1>

		<div class="flex justify-center space-x-8">
			<form action="SubmitCustomerController" method="post"
				class="bg-white p-8 rounded shadow-md w-full max-w-md">
				<div class="mb-4">
					<label for="customerId"
						class="block text-gray-700 text-sm font-bold mb-2">Search
						by Customer ID:</label> <select id="customerId" name="customerId"
						class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400"
						required>
						<c:forEach var="customer" items="${customers}">
							<option value="${customer.customerId}">
								${customer.customerId} - ${customer.firstName}
								${customer.lastName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="flex items-center justify-between">
					<input type="submit" value="Submit"
						class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-blue-400">
				</div>
			</form>

			<form action="SubmitCustomersWithoutAccountsController" method="post"
				class="bg-white p-8 rounded shadow-md w-full max-w-md">
				<div class="mb-4">
					<label for="customerIDWithoutAccounts"
						class="block text-gray-700 text-sm font-bold mb-2">Customers
						without accounts:</label> <select id="customerIDWithoutAccounts"
						name="customerIDWithoutAccounts"
						class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400"
						required>
						<c:forEach var="customer" items="${customersWithoutAccounts}">
							<option value="${customer.customerId}">
								${customer.customerId} - ${customer.firstName}
								${customer.lastName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="flex items-center justify-between">
					<input type="submit" value="Submit"
						class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-blue-400">
				</div>
			</form>
		</div>


		<c:if test="${not empty customer}">
			<div class="mt-8">
				<h2 class="text-2xl font-bold mb-4">Customer Details</h2>
				<table class="min-w-full bg-white border border-gray-300">
					<thead>
						<tr>
							<th class="px-4 py-2 border border-gray-300">Customer ID</th>
							<th class="px-4 py-2 border border-gray-300">First Name</th>
							<th class="px-4 py-2 border border-gray-300">Last Name</th>
							<th class="px-4 py-2 border border-gray-300">Email</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="px-4 py-2 border border-gray-300"><c:out
									value="${customer.customerId}" /></td>
							<td class="px-4 py-2 border border-gray-300"><c:out
									value="${customer.firstName}" /></td>
							<td class="px-4 py-2 border border-gray-300"><c:out
									value="${customer.lastName}" /></td>
							<td class="px-4 py-2 border border-gray-300"><c:out
									value="${customer.email}" /></td>
						</tr>
					</tbody>
				</table>

				<div class="mt-8 flex items-center">


					<form action="GenerateAccountController" method="post"
						onsubmit="return validateForm()">
						<label for="initialBalance"
							class="text-xl font-bold text-gray-700 mr-4">Enter
							Initial Balance:</label> <input type="number" id="initialBalance"
							name="initialBalance"
							class="p-3 w-1/3 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400"
							required> <input type="hidden" name="customerId"
							value="${customer.customerId}">
						<button type="submit"
							class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-green-400">
							Generate Account</button>
					</form>
				</div>
			</div>
		</c:if>
	</div>


</body>
</html>

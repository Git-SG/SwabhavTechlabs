<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<title>View Customers</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
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

	<div class="container mx-auto mt-8">
		<h1 class="text-3xl font-bold text-center mb-8">Customer Account
			Details</h1>

		<!-- Search Boxes -->
		<div class="grid grid-cols-1 md:grid-cols-2 gap-8 mb-8">
			<!-- Search by Name -->
			<div class="bg-white p-6 rounded-lg shadow-lg">
				<h2 class="text-xl font-semibold mb-4">Search by Name</h2>
				<form action="SearchByNameController" method="post">
					<div class="mb-4">
						<label for="firstName" class="block text-gray-700">First
							Name</label> <input type="text" id="firstName" name="firstName"
							class="mt-1 block w-full p-2 border border-gray-300 rounded-md"
							required>
					</div>
					<div class="mb-4">
						<label for="lastName" class="block text-gray-700">Last
							Name</label> <input type="text" id="lastName" name="lastName"
							class="mt-1 block w-full p-2 border border-gray-300 rounded-md"
							required>
					</div>
					<button type="submit"
						class="w-full bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600">Search</button>
				</form>
			</div>

			<!-- Search by Account Number -->
			<div class="bg-white p-6 rounded-lg shadow-lg">
				<h2 class="text-xl font-semibold mb-4">Search by Account Number</h2>
				<form action="SearchByAccountNumberController" method="post">
					<div class="mb-4">
						<label for="accountNumber" class="block text-gray-700">Account
							Number</label> <input type="number" id="accountNumber"
							name="accountNumber"
							class="mt-1 block w-full p-2 border border-gray-300 rounded-md"
							required>
					</div>
					<button type="submit"
						class="w-full bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600">Search</button>
				</form>
			</div>
		</div>

		<table class="min-w-full bg-white border border-gray-300">
			<thead class="bg-blue-500">
				<tr>
					<th class="px-4 py-2 border border-gray-300 text-white">First
						Name</th>
					<th class="px-4 py-2 border border-gray-300 text-white">Last
						Name</th>
					<th class="px-4 py-2 border border-gray-300 text-white">Account
						No</th>
					<th class="px-4 py-2 border border-gray-300 text-white">Balance</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="info" items="${customerInfoList}">
					<tr>
						<td class="px-4 py-2 border border-gray-300"><c:out
								value="${info.firstName}" /></td>
						<td class="px-4 py-2 border border-gray-300"><c:out
								value="${info.lastName}" /></td>
						<td class="px-4 py-2 border border-gray-300"><c:choose>
								<c:when test="${info.accountNumber == '0'}">-</c:when>
								<c:otherwise>
									<c:out value="${info.accountNumber}" />
								</c:otherwise>
							</c:choose></td>
						<td class="px-4 py-2 border border-gray-300"><c:choose>
								<c:when test="${info.accountNumber == 0}">-</c:when>
								<c:otherwise>
									<c:out value="${info.balance}" />
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>



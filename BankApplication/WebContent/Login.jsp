<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">

    <div class="bg-white p-8 rounded shadow-md w-full max-w-sm">
        <h2 class="text-2xl font-bold mb-6 text-center">Login</h2>
        
        <form action="LoginController" method="post">
            <div class="mb-4">
                <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Email:</label>
                <input type="text" name="email" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400">
            </div>
            
            <div class="mb-4">
                <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password:</label>
                <input type="password" name="password" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400">
            </div>
            
            <div class="mb-4">
                <label for="role" class="block text-gray-700 text-sm font-bold mb-2">Login as:</label>
                <select name="role" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400">
                    <option value="ADMIN">Admin</option>
                    <option value="CUSTOMER">Customer</option>
                </select>
            </div>
            
            <div class="flex items-center justify-between">
                <input type="submit" value="Submit" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-blue-400">
                <input type="reset" value="Cancel" class="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-red-400">
            </div>
        </form>

        <c:set var="loginFailed" scope="session" value=""></c:set>
        <c:if test="${not empty loginFailed}">
            <p class="text-red-500 text-sm mt-4 text-center"><c:out value="${loginFailed}"></c:out></p>
        </c:if>
    </div>

</body>
</html>


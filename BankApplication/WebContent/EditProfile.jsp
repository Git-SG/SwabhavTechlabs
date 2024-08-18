<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    String sessionPassword = (String) session.getAttribute("password");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function validateForm1() {
            const firstName = document.getElementById('firstName').value;
            const lastName = document.getElementById('lastName').value;
            const errorSpanName = document.getElementById('errorSpanName');
            const namePattern = /^[A-Za-z]+$/;
         
            if (!namePattern.test(firstName)) {
                errorSpanName.textContent = "First Name can only contain alphabets!";
                errorSpanName.style.display = "block";
                return false;
            }
            if (!namePattern.test(lastName)) {
                errorSpanName.textContent = "Last Name can only contain alphabets!";
                errorSpanName.style.display = "block";
                return false;
            }

            errorSpanName.style.display = "none";
            return true;
        }
        
        function validateForm2() {
            const oldPassword = document.getElementById('oldPassword').value;
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const errorSpanPassword = document.getElementById('errorSpanPassword');
            const sessionPassword = document.getElementById('sessionPassword').value;
            const passwordPattern = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

            if (oldPassword !== sessionPassword) {
                errorSpanPassword.textContent = "Old password is incorrect!";
                errorSpanPassword.style.display = "block";
                return false; 
            }

            if (newPassword !== confirmPassword) {
                errorSpanPassword.textContent = "Passwords do not match!";
                errorSpanPassword.style.display = "block";
                return false; 
            }

            if (!passwordPattern.test(newPassword)) {
                errorSpanPassword.textContent = "Password must be at least 8 characters long, include at least one uppercase letter, one number, and one special character.";
                errorSpanPassword.style.display = "block";
                return false;
            }

            errorSpanPassword.style.display = "none";
            return true;
        }
    </script>
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
        <h1 class="text-3xl font-bold text-center mb-8">Edit Profile</h1>

        <div class="bg-white p-6 rounded-lg shadow-md max-w-md mx-auto">
            <form action="UpdateNameController" method="post" class="mb-6" onsubmit="return validateForm1()">
                <h2 class="text-xl font-semibold mb-4 text-blue-500">Update Name</h2>
                <div class="mb-4 flex flex-col">
                    <label for="firstName" class="text-gray-700">Set First Name:</label>
                    <input type="text" id="firstName" name="firstName" value="<c:out value="${firstName}"/>" class="p-2 border border-gray-300 rounded-lg bg-gray-100" required>
                </div>
                <div class="mb-4 flex flex-col">
                    <label for="lastName" class="text-gray-700">Set Last Name:</label>
                    <input type="text" id="lastName" name="lastName" value="<c:out value="${lastName}"/>" class="p-2 border border-gray-300 rounded-lg bg-gray-100" required>
                    <span id="errorSpanName" class="text-red-500 text-sm mt-1" style="display:none;"></span>
                </div>
                <div class="flex justify-center space-x-4">
                    <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Submit</button>
                    <button type="reset" class="px-4 py-2 bg-gray-300 text-gray-800 rounded-lg hover:bg-gray-400">Clear</button>
                </div>
            </form>

            <form action="UpdatePasswordController" method="post" onsubmit="return validateForm2()">
                <h2 class="text-xl font-semibold mb-4 text-blue-500">Change Password</h2>
                <div class="mb-4 flex flex-col">
                    <label for="oldPassword" class="text-gray-700">Old Password:</label>
                    <input type="password" id="oldPassword" name="oldPassword" class="p-2 border border-gray-300 rounded-lg bg-gray-100" required>
                </div>
                <div class="mb-4 flex flex-col">
                    <label for="newPassword" class="text-gray-700">New Password:</label>
                    <input type="password" id="newPassword" name="newPassword" class="p-2 border border-gray-300 rounded-lg bg-gray-100" required>
                </div>
                <div class="mb-4 flex flex-col">
                    <label for="confirmPassword" class="text-gray-700">Confirm New Password:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" class="p-2 border border-gray-300 rounded-lg bg-gray-100" required>
                    <span id="errorSpanPassword" class="text-red-500 text-sm mt-1" style="display:none;"></span>
                </div>
                
                <!-- Hidden input to store session password -->
                <input type="hidden" id="sessionPassword" value="<%= sessionPassword %>">

                <div class="flex justify-center space-x-4">
                    <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Submit</button>
                    <button type="reset" class="px-4 py-2 bg-gray-300 text-gray-800 rounded-lg hover:bg-gray-400">Clear</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>


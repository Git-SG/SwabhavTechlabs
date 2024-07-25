package com.aurionpro.model;

public class Validator {
	 public boolean isValidEmail(String email) {
	        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	        return email.matches(emailRegex);
	    }

	    public boolean isValidPassword(String password) {
	        return isLongEnough(password) && containsUppercase(password) && containsSpecialChar(password) && containsDigit(password);
	    }

	    private boolean isLongEnough(String password) {
	        return password.length() >= 8;
	    }

	    private boolean containsUppercase(String password) {
	        for (char c : password.toCharArray()) {
	            if (Character.isUpperCase(c)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    private boolean containsSpecialChar(String password) {
	        String specialChars = "!@#$%^&*";
	        for (char c : password.toCharArray()) {
	            if (specialChars.indexOf(c) != -1) {
	                return true;
	            }
	        }
	        return false;
	    }

	    private boolean containsDigit(String password) {
	        for (char c : password.toCharArray()) {
	            if (Character.isDigit(c)) {
	                return true;
	            }
	        }
	        return false;
	    }

}

package com.aurionpro.exception;

public class RowNotValidException extends RuntimeException{
	
	public String getMessage() {
		return " Enter valid row number"; 
	}

}

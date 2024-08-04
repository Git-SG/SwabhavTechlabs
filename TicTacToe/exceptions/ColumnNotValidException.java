package com.aurionpro.exception;

public class ColumnNotValidException extends RuntimeException{
	
	public String getMessage() {
		return " Enter valid column number"; 
	}

}

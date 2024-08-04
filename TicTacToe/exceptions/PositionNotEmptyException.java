package com.aurionpro.exception;

public class PositionNotEmptyException extends RuntimeException{
	
	public String getMessage() {
		return " The position is already occupied"; 
	}

}

package com.aurionpro.list.exception;

public class NegativeAmountException extends RuntimeException{
	
	public String getMessage() {
		return "Amount cannot be negative";
	}

}

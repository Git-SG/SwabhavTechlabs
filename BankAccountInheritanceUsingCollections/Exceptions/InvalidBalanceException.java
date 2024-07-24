package com.aurionpro.list.exception;

public class InvalidBalanceException extends RuntimeException{
	
	private double balance;

	public InvalidBalanceException(double balance) {
		this.balance = balance;
	}
	
	public String getMessage() {
		return "Balance is invalid";
	}

}

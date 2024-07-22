package com.aurionpro.exception;

public class MinimumBalanceException extends RuntimeException{
	
	private static final Double MIN_BALANCE = 1000.0;
	
	public String getMessage() {
		return "Balance cannot be less than " + MIN_BALANCE;
	}

}

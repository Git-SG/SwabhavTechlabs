package com.aurionpro.list.exception;

public class OverdraftLimitException extends RuntimeException{
	
	private final double OVER_DRAFT_LIMIT = 10000.0;
		
	public String getMessage() {
		return "Over-Draft limit reached. Balance cannot be below -" + OVER_DRAFT_LIMIT;
	}

}

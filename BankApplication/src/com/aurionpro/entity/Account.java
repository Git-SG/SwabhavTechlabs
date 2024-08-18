package com.aurionpro.entity;

public class Account {
	
	private int accountNumber;
	private int customerId;
	private double balance;
	
	public Account(int accountNumber, int customerId, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public double getBalance() {
		return balance;
	}
	
	

}

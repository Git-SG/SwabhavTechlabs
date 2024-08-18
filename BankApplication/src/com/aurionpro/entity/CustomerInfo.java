package com.aurionpro.entity;

public class CustomerInfo {
	
	private String firstName;
	private String lastName;
	private int accountNumber;
	private double balance;
	
	public CustomerInfo(String firstName, String lastName, int accountNumber, double balance) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}
	
	

}

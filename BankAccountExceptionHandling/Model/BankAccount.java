package com.aurionpro.model;

import java.util.Scanner;

public abstract class BankAccount {
	
	private int accountNumber;
	private String name;
	private double balance;
	
	Scanner scanner = new Scanner(System.in);
	
	
	public BankAccount(int accountNumber, String name, double balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}
	
	public void credit() {
		System.out.println("Enter amount to be credited");
		double amount = scanner.nextDouble();
		balance += amount;
		System.out.println("After crediting balance is " + balance);
	}
	
	public abstract void debit();

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void openMenu() {
		System.out.println("Select an action to perform: \n1.Check Balance \n2.Credit \n3.Debit \n4.Exit");
		Integer action = scanner.nextInt();
		
		switch(action) {
		case 1: System.out.println("The balance of " + accountNumber + " is " + getBalance());
				openMenu();
				break;
		case 2: credit();
				openMenu();
				break;
		case 3: debit();
				openMenu();
				break;
		case 4: System.out.println("Thank you");
				break;
				
		default:System.out.println("Enter a valid option");
				openMenu();
		}
	}

}

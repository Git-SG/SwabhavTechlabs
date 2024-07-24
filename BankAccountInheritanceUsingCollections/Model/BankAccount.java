package com.aurionpro.list.model;

import java.util.Scanner;

import com.aurionpro.list.exception.NegativeAmountException;

public abstract class BankAccount {
	
	private static int nextAccountNumber = 1000;
	private int accountNumber;
	private String name;
	private double balance;	
	
	public BankAccount(String name, double balance) {
		this.accountNumber = nextAccountNumber++;
		this.name = name;
		this.balance = balance;
	}
	
	public void credit(Scanner scanner) {
		System.out.println("Enter amount to be credited");
		double amount = scanner.nextDouble();
		if (amount < 0)
			throw new NegativeAmountException();
		balance += amount;
		System.out.println("After crediting balance is " + balance);
	}
	
	public abstract void debit(Scanner scanner);

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
	
	public void openMenu(Scanner scanner) {
		System.out.println("Select an action to perform: \n1.Check Balance \n2.Credit \n3.Debit \n4.Exit");
		Integer action = scanner.nextInt();
		
		switch(action) {
		case 1: System.out.println("The balance of " + accountNumber + " is " + getBalance());
				openMenu(scanner);
				break;
		case 2: credit(scanner);
				openMenu(scanner);
				break;
		case 3: debit(scanner);
				openMenu(scanner);
				break;
		case 4: System.out.println("Thank you");
				break;
				
		default:System.out.println("Enter a valid option");
				openMenu(scanner);
		}
	}

}

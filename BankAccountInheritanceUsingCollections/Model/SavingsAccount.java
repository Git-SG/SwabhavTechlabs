package com.aurionpro.list.model;

import java.util.Scanner;

import com.aurionpro.list.exception.InvalidBalanceException;
import com.aurionpro.list.exception.MinimumBalanceException;
import com.aurionpro.list.exception.NegativeAmountException;
import com.aurionpro.list.model.AccountType;

public class SavingsAccount extends BankAccount{
	
	private static final Double MIN_BALANCE = 1000.0;
	private final AccountType accountType = AccountType.SAVINGS;

	public SavingsAccount(String name, double balance) {
		super(name, balance);
		if (balance < MIN_BALANCE)
			throw new InvalidBalanceException(balance);
	}
	
	public void setBalance(double balance) {
		if (balance < MIN_BALANCE)
			throw new InvalidBalanceException(balance);
		super.setBalance(balance);
	}

	@Override
	public void debit(Scanner scanner) {
		System.out.println("Enter amount to be debited: ");
		Double amount = scanner.nextDouble();
		if (amount < 0)
			throw new NegativeAmountException();
		if (amount > getBalance() - MIN_BALANCE) {
			throw new MinimumBalanceException();
		}
			
		setBalance(getBalance() - amount);	
		
		System.out.println("After debiting balance is " + getBalance());

	}

	@Override
	public String toString() {
		return "[accountNumber = " + getAccountNumber() + ", name =" + getName() + ", balance =" + getBalance() + ", accountType = " + accountType + "]";
	}

}

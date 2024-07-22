package com.aurionpro.model;

import com.aurionpro.exception.InvalidBalanceException;
import com.aurionpro.exception.OverdraftLimitException;

public class CurrentAccount extends BankAccount{
		
	private static final double OVER_DRAFT_LIMIT = 10000.0;
	private final AccountType accountType = AccountType.CURRENT;

	public CurrentAccount(int accountNumber, String name, double balance) {
		super(accountNumber, name, balance);
		if (balance < -(OVER_DRAFT_LIMIT))
			throw new InvalidBalanceException(balance);
	}
	
	public void setBalance(double balance) {
		if (balance < -(OVER_DRAFT_LIMIT))
			throw new InvalidBalanceException(balance);
		balance = getBalance();
	}


	@Override
	public void debit() {
		System.out.println("Enter amount to be debited: ");
		Double amount = scanner.nextDouble();

		if (amount > getBalance() + OVER_DRAFT_LIMIT) {
			throw new OverdraftLimitException();
		}
			
		setBalance(getBalance() - amount);
		
		System.out.println("After debiting balance is " + getBalance());
		
	}


	@Override
	public String toString() {
		return "CurrentAccount [accountType=" + accountType + ", getAccountNumber()="
				+ getAccountNumber() + ", getName()=" + getName() + ", getBalance()=" + getBalance() + "]";
	}

	
	
	
	
	

}

package com.aurionpro.model;

import com.aurionpro.exception.InvalidBalanceException;
import com.aurionpro.exception.MinimumBalanceException;

public class SavingsAccount extends BankAccount{
	
	private static final Double MIN_BALANCE = 1000.0;
	private final AccountType accountType = AccountType.SAVINGS;

	public SavingsAccount(int accountNumber, String name, double balance) {
		super(accountNumber, name, balance);
		if (balance < MIN_BALANCE)
			throw new InvalidBalanceException(balance);
	}
	
	public void setBalance(double balance) {
		if (balance < MIN_BALANCE)
			throw new InvalidBalanceException(balance);
		balance = getBalance();
	}

	@Override
	public void debit() {
		System.out.println("Enter amount to be debited: ");
		Double amount = scanner.nextDouble();

		if (amount > getBalance() - MIN_BALANCE) {
			throw new MinimumBalanceException();
		}
			
		setBalance(getBalance() - amount);	
		
		System.out.println("After debiting balance is " + getBalance());

	}

	@Override
	public String toString() {
		return "SavingsAccount [accountType=" + accountType +  ", getAccountNumber()="
				+ getAccountNumber() + ", getName()=" + getName() + ", getBalance()=" + getBalance() +  "]";
	}


	
	
	
	

}

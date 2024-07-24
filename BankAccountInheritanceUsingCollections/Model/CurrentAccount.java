package com.aurionpro.list.model;

import java.util.Scanner;

import com.aurionpro.list.exception.InvalidBalanceException;
import com.aurionpro.list.exception.NegativeAmountException;
import com.aurionpro.list.exception.OverdraftLimitException;
import com.aurionpro.list.model.AccountType;

public class CurrentAccount extends BankAccount{
	private static final double OVER_DRAFT_LIMIT = 10000.0;
	private final AccountType accountType = AccountType.CURRENT;

	public CurrentAccount(String name, double balance) {
		super(name, balance);
		if (balance < -(OVER_DRAFT_LIMIT))
			throw new InvalidBalanceException(balance);
	}
	
	public void setBalance(double balance) {
		if (balance < -(OVER_DRAFT_LIMIT))
			throw new InvalidBalanceException(balance);
		super.setBalance(balance);
	}


	@Override
	public void debit(Scanner scanner) {
		System.out.println("Enter amount to be debited: ");
		Double amount = scanner.nextDouble();
		if (amount < 0)
			throw new NegativeAmountException();
		if (amount > getBalance() + OVER_DRAFT_LIMIT) {
			throw new OverdraftLimitException();
		}
			
		setBalance(getBalance() - amount);
		
		System.out.println("After debiting balance is " + getBalance());
		
	}


	@Override
	public String toString() {
		return "[accountNumber = " + getAccountNumber() + ", name =" + getName() + ", balance =" + getBalance() + ", accountType = " + accountType + "]";
	}

}

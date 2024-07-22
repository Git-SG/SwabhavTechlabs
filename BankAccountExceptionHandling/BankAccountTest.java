package com.aurionpro.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.aurionpro.model.AccountType;
import com.aurionpro.model.BankAccount;
import com.aurionpro.model.CurrentAccount;
import com.aurionpro.model.SavingsAccount;

public class BankAccountTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Enter account number");
		int accountNumber = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter name");
		String name = scanner.nextLine();
		System.out.println("Enter balance");
		double balance= scanner.nextDouble();
		System.out.println("Enter account type (savings/current)");
		String accountType = scanner.next().toUpperCase();
		
		BankAccount account = null;
		
		try {
			if (AccountType.valueOf(accountType) == AccountType.SAVINGS) {
				account = new SavingsAccount(accountNumber, name, balance);
			}
			
			if (AccountType.valueOf(accountType) == AccountType.CURRENT) {
				account = new CurrentAccount(accountNumber, name, balance);
			}
			}

		catch(IllegalArgumentException exception) {
			System.out.println("Account type should be savings or current");
			return;
		}
		catch(Exception exception) {
			System.out.println("Somethign went wrong" + exception);
			return;
		}
		
		System.out.println(account);
		
		try {
			account.openMenu();
		}
		catch(InputMismatchException exception) {
			System.out.println("Enter a valid number");
		}
		
		System.out.println("Exiting main");
		
	}
}

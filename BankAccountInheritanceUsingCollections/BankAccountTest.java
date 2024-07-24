package com.aurionpro.list.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import com.aurionpro.list.exception.InvalidBalanceException;
import com.aurionpro.list.exception.MinimumBalanceException;
import com.aurionpro.list.exception.OverdraftLimitException;
import com.aurionpro.list.model.AccountType;
import com.aurionpro.list.model.BankAccount;
import com.aurionpro.list.model.CurrentAccount;
import com.aurionpro.list.model.SavingsAccount;

public class BankAccountTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter number of accounts ");
		int numberOfAccounts = scanner.nextInt();

		List<BankAccount> accounts = new ArrayList<BankAccount>();

		try {
			createAccounts(accounts, numberOfAccounts, scanner);
		} catch (IndexOutOfBoundsException exception) {
			System.out.println("List not formed");
			return;
		}
		
		System.out.println("\nThe accounts are: ");
		printAccounts(accounts);

		System.out.println("\nEnter account number you wish to access: ");
		int accountNumber = scanner.nextInt();
		try {
			accounts.get(getIndexforAccountNumber(accountNumber, accounts)).openMenu(scanner);
		} catch (IndexOutOfBoundsException exception) {
			System.out.println("Enter a valid account number");
		} catch (NegativeArraySizeException exception) {
			System.out.println(exception.getMessage());
		} catch (OverdraftLimitException exception) {
			System.out.println(exception.getMessage());
		} catch (MinimumBalanceException exception) {
			System.out.println(exception.getMessage());
		}

		System.out.println("\nExiting main");

		scanner.close();
	}

	private static void createAccounts(List<BankAccount> accounts, int numberOfAccounts, Scanner scanner) {
		for (int i = 0; i < numberOfAccounts; i++) {
			scanner.nextLine();
			System.out.println("Enter name");
			String name = scanner.nextLine();
			System.out.println("Enter balance");
			double balance = scanner.nextDouble();
			System.out.println("Enter account type (savings/current)");
			String accountType = scanner.next().toUpperCase();

			try {
				if (AccountType.valueOf(accountType) == AccountType.SAVINGS)
					accounts.add(new SavingsAccount(name, balance));

				if (AccountType.valueOf(accountType) == AccountType.CURRENT)
					accounts.add(new CurrentAccount(name, balance));
			} catch (InvalidBalanceException exception) {
				System.out.println(exception.getMessage());
			} catch (IllegalArgumentException exception) {
				System.out.println("Account type should be savings or current");
				return;
			} catch (Exception exception) {
				System.out.println("Something went wrong" + exception);
				return;
			}

			System.out.println("Account number is: " + accounts.get(i).getAccountNumber());
		}

	}

	private static int getIndexforAccountNumber(int accountNumber, List<BankAccount> accounts) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == accountNumber) {
				return i;
			}
		}

		return -1;
	}
	
	private static void printAccounts(List<BankAccount> accounts) {
		for (BankAccount account : accounts)
			System.out.println(account);
	}

}
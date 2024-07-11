package H3;

import java.util.*;

public class ATM {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Existing bank balance ");
		double balance = sc.nextDouble();
		
		boolean exit = false;
		
		while(!exit) {
			System.out.print("ATM menu: \n1. Check Balance \n2. Deposit Money \n3. Withdraw Money \n4. Exit \nPlease choose an option: ");
			int option = sc.nextInt();
			
			switch(option) {
			case 1: System.out.println("Your current balance is: $" + balance);
					break;
			case 2: {
				    System.out.print("Enter amount to deposit: ");
			        double depositAmount = sc.nextDouble();
			        if (depositAmount < 0) {
			        	System.out.println("Enter valid amount");
			        	break;
			        }
			        balance += depositAmount;
			        System.out.println("You have deposited $" + depositAmount + ". Your new balance is $" + balance);
			        break;
			        }
			case 3: {
				    System.out.print("Enter amount to withdraw: ");
			        double withdrawalAmount = sc.nextDouble();
			        if (withdrawalAmount > balance) {
			        	System.out.println("Not enough balance");
			        	break;
			        }
			        if (withdrawalAmount < 0) {
			        	System.out.println("Enter valid amount");
			        	break;
			        }
			        else {
			        balance -= withdrawalAmount;
			        System.out.println("You have withdrawn $" + withdrawalAmount + ". Your new balance is $" + balance);
			        break;
			         }
			        }
			case 4: {
					System.out.println("Thank you for using the ATM. Goodbye!");
					exit = true;
					break;
					}
			default: System.out.println("Please select a valid option");
			}
		}
	}

}

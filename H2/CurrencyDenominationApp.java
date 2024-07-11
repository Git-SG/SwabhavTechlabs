package H2;

import java.util.*;

public class CurrencyDenominationApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter withdrawal amount: ");
		int amount = sc.nextInt();

		if (amount > 50000) {
			System.out.println("Amount exceeds withdrawal limit of 50000");
		}

		else if (amount % 100 != 0 || amount == 0) {
			System.out.println("Amount should be in multiples of 100");
		}

		else {
			int numberOf2000 = amount / 2000;
			amount %= 2000;
			int numberOf500 = amount / 500;
			amount %= 500;
			int numberOf200 = amount / 200;
			amount %= 200;
			int numberOf100 = amount / 100;
			amount %= 100;

			if (numberOf2000 > 0)
				System.out.println("Two Thousand: " + numberOf2000);
			if (numberOf500 > 0)
				System.out.println("Five Hunred: " + numberOf500);
			if (numberOf200 > 0)
				System.out.println("Two Hundred: " + numberOf200);
			if (numberOf100 > 0)
				System.out.println("Hundred: " + numberOf100);

		}
	}
}
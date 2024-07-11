package H3;

import java.util.*;

import javax.print.attribute.standard.NumberOfInterveningJobs;

public class PrimeNumberPatternPrinter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of lines ");
		int numberOfLines = sc.nextInt();
		int number = 2;
		int countOfLine = 1;

		if (numberOfLines > 0) {
			for (int i = 1; i <= numberOfLines; i++) {
				int j = 0;
				while (j < countOfLine) {
					if (isPrime(number)) {
						System.out.print(number + " ");
						j++;
					}
					number++;
				}
				System.out.println();
				countOfLine++;
			}

		} else {
			System.out.println("Enter positive number of lines");
		}
	}

	public static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}

		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;

	}

}

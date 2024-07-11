package H2;

import java.util.*;

public class RideBillCalculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the height in centimeters");
		int height = sc.nextInt();

		if (height <= 120) {
			System.out.println("Cannot ride due to height restrictions");
		}

		else {
			System.out.println("Enter age");
			int age = sc.nextInt();
			System.out.println("Enter true for photos; false for no photos");
			Boolean wantPhotos = sc.nextBoolean();

			int totalBill = 0;

			if (age < 12) {
				totalBill += 5;
			}

			else if (age >= 18) {
				if (age <= 45 || age >= 55) {
					totalBill += 12;
				}
			}

			else {
				totalBill += 7;
			}

			if (wantPhotos) {
				totalBill += 3;
			}

			System.out.println("The total ride bill is $" + totalBill);

		}

	}

}

package H3;

import java.util.*;
import java.lang.Math;

public class NumberGuesser2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int attempt = 0;
		int randomNumber = 0;

		do {

			attempt++;

			if (attempt == 6) {
				System.out.println("Maximum attempts reached");
				System.out.print("\nDo you want to play the game again?: Type yes to continue -> ");
				String playAgain = sc.next();

				if (playAgain.equalsIgnoreCase("yes")) {
					attempt = 0;
					continue;
				}

				else {
					break;
				}
			}
			if (attempt == 1) {
				randomNumber = (int) (Math.random() * 100) + 1;
				System.out.println("Random number generated " + randomNumber);
			}

			System.out.println("Guess a number: ");
			int number = sc.nextInt();

			if (number > randomNumber) {
				System.out.println("Sorry, Too High");
			}

			if (number < randomNumber) {
				System.out.println("Sorry, Too Low");
			}

			if (number == randomNumber) {
				System.out.println("You won in " + attempt + " attempts");
				System.out.print("\nDo you want to play the game again?: Type yes to continue -> ");
				String playAgain = sc.next();

				if (playAgain.equalsIgnoreCase("yes")) {
					attempt = 0;
					continue;
				}

				else {
					break;
				}
			}
		} while (attempt <= 5);

	}

}

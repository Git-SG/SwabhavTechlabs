package H2;

import java.util.*;

public class TreasureFinder {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to Treasure Island. \nYour mission is to find the Treasure.");
		System.out.println("\nYou arrive at an intersection, would you go left or right? \nEnter direction:");
		String direction = sc.nextLine().toLowerCase();

		if (!direction.equals("left")) {
			System.out.println("You fell into a hole! \nGame Over.");
		}

		else {
			System.out.println("You encounter a river, do you swim or wait? \nEnter action:");
			String action = sc.nextLine().toLowerCase();

			if (!action.equals("wait")) {
				System.out.println("You got attacked by a trout! \nGame over.");
			}

			else {
				System.out.println(
						"You reach a path with three doors, Red, Blue and Yellow. Which do you choose? \nEnter the door colour: ");
				String doorColour = sc.nextLine().toLowerCase();

				if (doorColour.equals("red")) {
					System.out.println("You got burned by fire! \nGame over.");
				}

				else if (doorColour.equals("blue")) {
					System.out.println("You got eaten by beasts! \nGame over.");
				}

				else if (doorColour.equals("yellow")) {
					System.out.println("You found the treasure! \nYou Win!");
				}

				else {
					System.out.println("Not a door! \nGame Over.");
				}
			}

		}

	}

}

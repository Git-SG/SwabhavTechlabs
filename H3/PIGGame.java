package H3;

import java.util.*;
import java.lang.Math;

public class PIGGame {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int totalScore = 0;
		int i = 1;
		
		while (totalScore < 20) {
			System.out.println("\nTURN " + i);
			i++;
			int turnScore = 0;
			while (true) {
				System.out.print("Roll or hold? (r/h): ");
				String decision = sc.next();
				if (decision.equalsIgnoreCase("r")) {
					int rolledNumber = (int) ((Math.random() * 6) + 1);
					System.out.println("Die: " + rolledNumber);
					if (rolledNumber == 1) {
						System.out.println("Turn over. No score");
						break;
					}
					turnScore += rolledNumber;
					continue;
				}
				
				if (decision.equalsIgnoreCase("h")) {
					System.out.println("Score for turn: " + turnScore);
					totalScore += turnScore;
					System.out.println("Total score: " + totalScore);
					break;
				}
				
				else {
					System.out.println("Enter a valid action");
				}
			}
			
			if (totalScore >= 20) {
				System.out.println("You finished in " + (i-1) + " turns! \nGame over!");
			}

		}
	}

}

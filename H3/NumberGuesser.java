package H3;

import java.util.*;
import java.lang.Math;

public class NumberGuesser {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxAttempts = 5;
        
        while (true) {
            int attempt = 0;
            int randomNumber = (int) (Math.random() * 100) + 1;
            
            System.out.println("Random number generated: " + randomNumber); //I printed randomNumber for the sake of reference
            
            while (attempt < maxAttempts) {
                attempt++;
                System.out.print("Guess a number: ");
                int number = sc.nextInt();
                
                if (number > randomNumber) {
                    System.out.println("Sorry, Too High");
                } else if (number < randomNumber) {
                    System.out.println("Sorry, Too Low");
                } else {
                    System.out.println("You won in " + attempt + " attempts");
                    break;
                }
            }
            
            if (attempt == maxAttempts) {
                System.out.println("Maximum attempts reached.");
            }
            
            System.out.print("\nDo you want to play the game again? Type 'yes' to continue: ");
            String playAgain = sc.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }
}
package H3;

import java.util.*;
import java.lang.StringBuilder;

public class WordGuesserGame {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		String[] wordList = {"sarthak", "one", "two", "light", "hospital", "floor"};
		int index = random.nextInt(wordList.length);
		String word = wordList[index].toLowerCase();
		
		int wordLength = word.length();
		int totalLives = wordLength;
		StringBuilder blanks = new  StringBuilder();
		int i = 0;
		
		while (i < wordLength) {
			blanks.append("_");
			i++;
		}
		
		i = 0;
		while(totalLives > 0) {
			System.out.println(blanks + " word length is " + wordLength);
			
			System.out.print("Enter a letter: ");
			char letter = sc.next().toLowerCase().charAt(0);
			
			boolean correct = false;
			for (i = 0; i < wordLength; i++) {
				if (word.charAt(i) == letter) {
					blanks.setCharAt(i, letter);
					correct = true;
				}
			}
			
			if (!correct) {
				totalLives--;
				System.out.println("Incorrect letter. Lives Left: " + totalLives);
			}
			
			if (blanks.toString().equals(word)) {
				System.out.println("You Win!");
				break;
			}
		}
		
		if (totalLives == 0) {
			System.out.println("Game over.");
		}
	}
}

package H1;

import java.util.*;

public class MinutesToHoursAndMinutesConverter {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter minutes: ");
		int minutes = sc.nextInt();
		
		int hours = minutes / 60;
		int remainingMinutes = minutes % 60;
		
		System.out.println(minutes + " minutes is " + hours + " hours and " + remainingMinutes + " minutes");
		
	}

}

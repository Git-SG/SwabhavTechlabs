package H1;

import java.util.*;
import java.lang.Math;

public class CompundInterestCalculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the principal amount ");
		double principalAmount = sc.nextInt();
		System.out.println("Enter the Rate of interest in %");
		double rateOfInterest = sc.nextDouble();
		System.out.println("Enter the number of years ");
		double numberOfYears = sc.nextDouble();
		
		double finalAmount = principalAmount * Math.pow((1 + (rateOfInterest/(100))), numberOfYears);
		
		double compoundInterest = finalAmount - principalAmount;
		
		System.out.println("The compound interest for the given criteria is " + compoundInterest);
	}

}

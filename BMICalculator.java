package H1;

import java.util.*;

public class BMICalculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter weight in kg: ");
		double weight = sc.nextDouble();
		System.out.println("Enter height in m: ");
		double height = sc.nextDouble();
		
		double bmi = weight / (height * height);
		
		System.out.println("The BMI for weight " + weight + " and height " + height + " is " + bmi);
	}

}

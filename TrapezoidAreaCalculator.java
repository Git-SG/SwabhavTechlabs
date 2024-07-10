package H1;

import java.util.*;

public class TrapezoidAreaCalculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the lengths of first base (b1): ");
		double base1 = sc.nextDouble();
		System.out.println("Enter the lengths of second base (b2): ");
		double base2 = sc.nextDouble();
		System.out.println("Enter the height (h): ");
		double height = sc.nextDouble();
		
		double area = height * (base1 + base2) / 2;
		
		System.out.println("For the given trapezoid, the area is " + area);
	}

}

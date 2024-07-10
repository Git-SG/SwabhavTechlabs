package H1;

import java.util.*;
import java.lang.Math;

public class DistanceCalculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter x co-ordinate of 1st point(x1): ");
		double x1 = sc.nextDouble();
		System.out.println("Enter y co-ordinate of 1st point(y1): ");
		double y1 = sc.nextDouble();
		
		System.out.println("Enter x co-ordinate of 2nd point(x2): ");
		double x2 = sc.nextDouble();
		System.out.println("Enter y co-ordinate of 2nd point(y2): ");
		double y2 = sc.nextDouble();
		
		double squaredDistance = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
		double distance = Math.sqrt(squaredDistance);
		
		System.out.println("The distance between point (" + x1 + "," + y1 + ") "
				+ "and (" + x2 + "," + y2 + ") is " + distance);

	}

}

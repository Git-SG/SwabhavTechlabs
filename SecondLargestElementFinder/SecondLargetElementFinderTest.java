package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.SecondLargestElementFinder;

public class SecondLargetElementFinderTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SecondLargestElementFinder array = new SecondLargestElementFinder();

		System.out.println("Enter size of Array: ");
		int sizeOfArray = scanner.nextInt();

		int[] numbers = new int[sizeOfArray];
		createArray(numbers, scanner);

		System.out.println(array.findSecondLargestElement(numbers));

	}

	private static void createArray(int[] numbers, Scanner scanner) {
		System.out.println("Enter array elements");
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = scanner.nextInt();
	}

}

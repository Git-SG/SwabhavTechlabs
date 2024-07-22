package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.ArrayElementsProductCalculator;

public class ArrayElementsProductCalculatorTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayElementsProductCalculator array = new ArrayElementsProductCalculator();

		System.out.println("Enter Size of array");
		int sizeOfArray = scanner.nextInt();

		int[] numbers = new int[sizeOfArray];

		createArray(numbers, scanner);

		int[] productArray = array.getProductArray(numbers);

		System.out.println("\nThe resulting array is: ");
		array.printArray(productArray);

	}

	private static void createArray(int[] numbers, Scanner scanner) {
		System.out.println("Enter array elements");
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = scanner.nextInt();
	}

}

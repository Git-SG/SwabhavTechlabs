package com.aurionpro.test;

import java.util.Arrays;
import java.util.Scanner;

import com.aurionpro.model.SquaredArraySorter;

public class SquaredArraySorterTest {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SquaredArraySorter array = new SquaredArraySorter();
		
		System.out.println("Enter Size of array");
		int sizeOfArray = scanner.nextInt();
		
		int[] numbers = new int[sizeOfArray];
		createArray(numbers, scanner);
		
		System.out.println("Sorted Array is: ");
		int[] sortedArray = numbers.clone();
		Arrays.sort(sortedArray);
		array.printArray(sortedArray);
		
		System.out.println("Squared and sorted array is:");
		int[] squaredArray = array.getSquaredArray(sortedArray);
		array.printArray(squaredArray);
		
		
	}
	
	private static void createArray(int[] numbers, Scanner scanner) {
		System.out.println("Enter array elements");
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = scanner.nextInt();
	}

}

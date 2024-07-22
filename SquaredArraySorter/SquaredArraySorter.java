package com.aurionpro.model;

import java.util.Arrays;

public class SquaredArraySorter {
	
	public void printArray(int[] numbers) {
		for(int number: numbers)
			System.out.print(number + " ");
		System.out.println();
	}
	
	public int[] getSquaredArray(int[] numbers) {
		for(int i = 0; i < numbers.length; i ++)
			numbers[i] *= numbers[i];
		
		Arrays.sort(numbers);
		
		return numbers;
	}

}

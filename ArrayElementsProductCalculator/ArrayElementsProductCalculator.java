package com.aurionpro.model;

public class ArrayElementsProductCalculator {
	
	public void printArray(int[] numbers) {
		for(int number: numbers)
			System.out.print(number + " ");
	}
	
	public int[] getProductArray(int[] numbers) {
		int[] productArray = numbers.clone();
		
		calculateProductArray(productArray, numbers);
		
		return productArray;
	}
	
	public void calculateProductArray(int[] productArray, int[] numbers) {
		for(int i = 0; i < productArray.length; i++) {
			int product = 1;
			productArray[i] = calculateProduct(product, numbers, i);	
		}
	}
	
	public int calculateProduct (int product, int[] numbers, int i) {
		for(int j = 0; j < numbers.length; j++) {
			if (numbers[j] == numbers[i]) {
				continue;
			}
			product *= numbers[j];
		}
		
		return product;
	}

}

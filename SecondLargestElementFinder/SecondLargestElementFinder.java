package com.aurionpro.model;

public class SecondLargestElementFinder {

	public void printArray(int[] numbers) {
		for (int number : numbers)
			System.out.print(number + " ");
	}

	public String findSecondLargestElement(int[] numbers) {
		int secondLargestElement = getSecondLargestElement(numbers);
		String string = "";

		if (secondLargestElement == Integer.MIN_VALUE)
			return "Array doesn't have a second largest number";

		string = getPositions(numbers, secondLargestElement, string);

		return "The second largest element is " + secondLargestElement + " and it is present at postion(s) "
				+ string;

	}

	public String getPositions(int[] numbers, int secondLargestElement, String string) {
		for (int i = 0; i < numbers.length; i++)
			string = appendPositions(numbers, secondLargestElement, string, i);

		return string;
	}

	public String appendPositions(int[] numbers, int secondLargestElement, String string, int i) {
		if (numbers[i] == secondLargestElement)
			string += i + " ";

		return string;
	}

	public int getSecondLargestElement(int[] numbers) {
		int largestElement = Integer.MIN_VALUE;
		int secondLargestElement = Integer.MIN_VALUE;
		
		for(int number : numbers) {
			if(number > largestElement) {
				secondLargestElement = largestElement;
				largestElement = number;
			}
			else if (number > secondLargestElement && number != largestElement) {
				secondLargestElement = number;
			}
		}
		
		return secondLargestElement;
	}

}

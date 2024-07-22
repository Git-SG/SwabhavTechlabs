package H5_MultiDimensionalArrays_methods;

import java.util.Scanner;

public class MatrixMultiplier {


	public static int[][] createMatrix() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter number of rows");
		int rows = scanner.nextInt();

		System.out.println("Enter number of columns");
		int columns = scanner.nextInt();

		int[][] matrix = new int[rows][columns];

		System.out.println("Enter the elements of the matrix");

		for (int i = 0; i < rows; i++) {
			createRows(matrix, i, scanner);
		}

		return matrix;

	}
	
	public static void createRows(int[][] matrix, int i, Scanner scanner) {
		for (int j = 0; j < matrix[0].length; j++) {
			matrix[i][j] = scanner.nextInt();
		}
	}

	public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {

		int rows1 = matrix1.length;
		int columns1 = matrix1[0].length;
		int rows2 = matrix2.length;
		int columns2 = matrix2[0].length;

		if (columns1 == rows2) {
			int[][] product = new int[rows1][columns2];

			calculateMatrix(product, matrix1, matrix2);

			return product;
		}

		System.out.println("The matrices are not compatible for multiplication");
		return null;
	}
	
	public static void calculateElements(int[][] product, int[][] matrix1, int[][] matrix2, int i, int j) {
		for (int k = 0; k < matrix2.length; k++) {
			product[i][j] += matrix1[i][k] * matrix2[k][j];
		}
	}
	
	public static void calculateRows(int[][] product, int[][] matrix1, int[][] matrix2, int i) {
		for (int j = 0; j < matrix2[0].length; j++) {
			calculateElements(product, matrix1, matrix2, i, j);
		}
	}
	
	public static void calculateMatrix(int[][] product, int[][] matrix1, int[][] matrix2) {
		for (int i = 0; i < matrix1.length; i++) {
			calculateRows(product, matrix1, matrix2, i);
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			printRows(matrix, i);
			System.out.println();
		}
	}
	
	public static void printRows(int[][] matrix, int i) {
		for (int j = 0; j < matrix[0].length; j++) {
			System.out.print(matrix[i][j] + "\t");
		}
	}

}

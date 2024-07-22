package H5_MultiDimensionalArrays_test;

import java.util.Scanner;

import H5_MultiDimensionalArrays_methods.MatrixMultiplier;

public class MatrixMultiplierTest {
	public static void main(String[] args) {
		MatrixMultiplier matrix = new MatrixMultiplier();

		System.out.println("Matrix 1");

		int[][] matrix1 = matrix.createMatrix();

		System.out.println("Matrix 2");

		int[][] matrix2 = matrix.createMatrix();

		int[][] product = matrix.multiplyMatrices(matrix1, matrix2);

		if (product != null) {
			System.out.println("The resulting matrix is ");
			matrix.printMatrix(product);
		}

	}

}

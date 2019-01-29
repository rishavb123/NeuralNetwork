package io.bhagat.examples;

import io.bhagat.math.Matrix;
import io.bhagat.math.Vector;

/**
 * @author Bhagat
 * A class showing a demo the Matrix class in the math package
 */
public class MatrixDemo {
	
	/**
	 * The main method that will run
	 * @param args cmd arguments
	 */
	public static void main(String[] args) {
		
		/**
		 * A matrix m with numbers counting to nine
		 */
		Matrix a = new Matrix(new double[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		
		/**
		 * A matrix m with numbers counting from 10 to 18
		 */
		Matrix b = new Matrix(new double[][] {
			{10, 11, 12},
			{13, 14, 15},
			{16, 17, 18}
		});
		
		// Print the Matrix
		System.out.println(a);
		
		// Print the rows
		Vector.print(a.getVectorRows());
		
		// Print the columns
		Vector.print(a.getVectorColumns());
		
		// Print the Matrix product between the two Vectors
		System.out.println(Matrix.multiply(a, b));

		System.out.println(a);
		
		// Remove a row from the cloned matrix of a
		System.out.println(a.clone().removeRow(0));
		// Remove a column from the cloned matrix of a
		System.out.println(a.clone().removeColumn(0));
		
		/**
		 * Another matrix with one dimension
		 */
		Matrix c = new Matrix(new double[][] {{-1}});
		
		/**
		 * A 2 by 2 Matrix
		 */
		Matrix d = new Matrix(new double[][] {
			{1, 2}, {3, 4}
		});
		/**
		 * A matrix with higher (5) dimensions
		 */
		Matrix e = new Matrix(new double[][] {
			{1, 4, 11, 90, 30},
			{8, 2, 1, 7, 20},
			{10, 23, 21, 80, 1},
			{42, 69, 21, 6, 9},
			{2, 2, 4, 1, 3}
		});
		
		// Computes the determinant of the matrix
		//                 -1                      -2                      3045870
		System.out.println(c.determinant() + " " + d.determinant() + " " + e.determinant());
	}

}

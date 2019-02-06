package io.bhagat.examples.math;

import io.bhagat.math.AugmentedMatrix;
import io.bhagat.math.Matrix;
import io.bhagat.math.Vector;

/**
 * A class showing a demo the Matrix class in the math package
 * @author Bhagat
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
		
		
		Matrix m1 = new Matrix(new double[][] {
			{1, 3}
		});
		
		Matrix m2 = new Matrix(new double[][] {
			{1, 2, 3},
			{4, 5, 6}
		});
		

		// Print the Matrix product between the two Vectors
		System.out.println(Matrix.multiply(a, b));
		System.out.println(Matrix.multiply(m1, m2));

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
		
		// Prints the identity matrix with size 5
		System.out.println("\n\n"+Matrix.identityMatrix(5)+"\n\n");
		
		// Inverse of a matrix
		/**
		 * a matrix to compute the inverse of
		 */
		Matrix matrixToInverse = new Matrix(new double[][] {
			{1, 2},
			{3, 4}
		});
		/**
		 * a larger matrix to compute the inverse of
		 */
		Matrix matrixToInverse2 = new Matrix(new double[][] {
			{1, 2, 3},
			{0, 1, 4},
			{5, 6, 0}
		});
		System.out.println(matrixToInverse+"\n");
		System.out.println(Matrix.inverse(matrixToInverse)+"\n");
		System.out.println(matrixToInverse2+"\n");
		System.out.println(Matrix.inverse(matrixToInverse2)+"\n");
		
		/**
		 * An augmented Matrix
		 */
		AugmentedMatrix augmentedMatrix = new AugmentedMatrix(new double[][] {
			{1, 3, 2, 8},
			{3, 2, 8, 9},
			{6, 4, 1, 3}
		} );
		
		// Print Augmented Matrix
		System.out.println("\n\n"+augmentedMatrix + "\n\n");
		
		// Switch two rows
		augmentedMatrix.switchTwoRows(0, 2);
		System.out.println(augmentedMatrix);
	}

}

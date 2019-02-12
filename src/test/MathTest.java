/**
 * 
 */
package test;

import io.bhagat.math.Matrix;

public class MathTest {
	public static void main(String[] args) {
		Matrix m = new Matrix(new double[][] {
			{0,0,1},{0,1,0},{1,0,0}
		});
		Matrix m2 = new Matrix(new double[][] {
			{1, 0, 0},{-1,1,0},{0,0.2,1}
		});
		Matrix m3 = new Matrix(new double[][] {
			{1,3,3}, {0,5,4}, {0,0,16/5.0}
		});
		System.out.println("Mine:\n"+Matrix.multiply(Matrix.multiply(m, m2), m3));
		
		m = new Matrix(new double[][] {
			{0,1,0},{1,0,0},{0,0,1}
		});
		m2 = new Matrix(new double[][] {
			{1, 0, 0},{0,1,0},{-1,5,1}
		});
		m3 = new Matrix(new double[][] {
			{-1,2,1}, {0,1,4}, {0,0,-16}
		});
		System.out.println("\n\nCakir's: \n"+Matrix.multiply(Matrix.multiply(m, m2), m3));
	}

}

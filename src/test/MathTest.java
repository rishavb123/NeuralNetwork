/**
 * 
 */
package test;

import java.io.IOException;

import io.bhagat.math.Matrix;
import io.bhagat.util.SerializableUtil;

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
		
		try {
			SerializableUtil.serialize(new Matrix(new double[][] {
				{1, 10, 100},
				{2, 20, 200},
				{3, 30, 300}
			}), "test.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			m = SerializableUtil.deserialize("test.ser");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\nMatrix from disk:\n"+m);
		
	}

}

package io.bhagat.examples;

import io.bhagat.math.Vector;
import io.bhagat.math.Vector.InvalidLengthException;
import io.bhagat.math.Vector.OutOfDimensionsException;

/**
 * A test class that will run and test the different libraries
 * @author Bhagat
 */
public class VectorDemo {

	/**
	 * The main method that will run
	 * @param args cmd arguments
	 */
	public static void main(String[] args) {
		/**
		 * a vector with values <3, 4, 1>
		 */
		Vector a = new Vector(2, 6, 1);

		/**
		 * a vector with values <2, 8, 3>
		 */
		Vector b = new Vector(new double[] { 2, 8, 3 });
		
		// Prints out the vector
		System.out.println(a);
		
		// Multiply vector by two
		a.multiply(2);
		
		// Prints out the vector
		System.out.println(a);
		
		// Divide vector by two
		a.divide(2);
		
		// Prints out the vector
		System.out.println(a);
		
		// Prints out the magnitude
		System.out.println(a.getMagnitude());
		
		System.out.println("\n\n");
		
		// Prints the dot product of the Vectors
		try {
			System.out.println(a + " * " + b + " = " + Vector.dot(a, b));
		} catch (OutOfDimensionsException | InvalidLengthException e) {
			e.printStackTrace();
		}
	}

}

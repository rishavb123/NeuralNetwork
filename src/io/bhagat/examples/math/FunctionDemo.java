package io.bhagat.examples.math;

import io.bhagat.math.Function;
import io.bhagat.math.Vector;

/**
 * @author Bhagat
 * a class to display how the Function interface works
 */
public class FunctionDemo {

	/**
	 * the main function
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		/**
		 * define a function
		 */
		Function<Double, Double> doubleFunction = new Function<Double, Double>(){

			@Override
			public Double f(Double x) {
				return 3*x - 10;
			}
			
		};
		
		/**
		 * defines a function that takes in a vector
		 */
		Function<Vector, Double> vectorFunction = new Function<Vector, Double>() {

			@Override
			public Double f(Vector x) {
				return x.multiply(x).multiply(8.0).getMagnitude();
			}
			
		};

		/**
		 * defines an array with the numbers from 0 to 9
		 */
		double[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		/**
		 * defines a vector using the previous array
		 */
		Vector v = new Vector(arr);
		
		// loops through the array and performs the doubleFunction
		for(double a : arr)
		{
			System.out.print(doubleFunction.f(a) + " ");
		}
		
		// runs the vector function
		System.out.println("\n\n"+vectorFunction.f(v));
		
		
		
	}
	
}

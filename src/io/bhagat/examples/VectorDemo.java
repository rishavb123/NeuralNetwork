package io.bhagat.examples;

import io.bhagat.math.Vector;

/**
 * @author Bhagat
 * A class showing a demo the Vector class in the math package
 */
public class VectorDemo {

	/**
	 * The main method that will run
	 * @param args cmd arguments
	 */
	public static void main(String[] args) {
		/**
		 * a vector with values <2, 6, 1>
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
		System.out.println(a + " * " + b + " = " + Vector.dot(a, b));
		
		System.out.println("\n\n"); 
		
		/**
		 * 4D Vector
		 */
		Vector v1 = new Vector(2, 4, 6, 8);
		/**
		 * 4D Vector
		 */
		Vector v2 = new Vector(1, 3, 5, 7);
		/**
		 * 4D Vector
		 */
		Vector v3 = new Vector(9, 4, 5, 1);
		
		// Compute the cross product of two 3D Vectors and check if they are orthogonal
		System.out.println("A: "+a);
		System.out.println("B: "+b);
		System.out.println("AxB: "+Vector.cross(a,b));
		System.out.println("A _|_ AxB: "+a.orthogonal(Vector.cross(a, b)));
		System.out.println("B _|_ AxB: "+b.orthogonal(Vector.cross(a, b)));
		
		
		// Compute the cross product of three 4D Vectors and check if they are orthogonal
		System.out.println("\n\nV1: "+v1);
		System.out.println("V2: "+v2);
		System.out.println("V3: "+v3);
		System.out.println("V1xV2xV3: "+Vector.cross(v1, v2, v3));
		System.out.println("V1 _|_ V1xV2xV3: "+v1.orthogonal(Vector.cross(v1, v2, v3)));
		System.out.println("V2 _|_ V1xV2xV3: "+v2.orthogonal(Vector.cross(v1, v2, v3)));
		System.out.println("V3 _|_ V1xV2xV3: "+v3.orthogonal(Vector.cross(v1, v2, v3)));
	}

}

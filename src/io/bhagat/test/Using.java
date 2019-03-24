package io.bhagat.test;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;

public class Using {

	public static void main(String[] args)
	{
		
		Matrix A = new Matrix(new double[][] {
			{1/2.,-Math.sqrt(3)/2.},
			{Math.sqrt(3)/2.,1/2.}
		});
		System.out.println(A.inverse() + "\n\n" + Math.sqrt(3) / 2. + "\n\n");
		Vector x = new Vector(3, 2);
		System.out.println(A.inverse().multiply(x.toMatrix()));
	}
}

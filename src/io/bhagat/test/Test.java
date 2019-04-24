package io.bhagat.test;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.util.ArrayUtil;

public class Test {

	public static void main(String[] args)
	{
		Matrix A = new Matrix(new double[][]{
			{1, 1, 0},
			{0, 0, 1},
		});
				
		System.out.println(Matrix.QR(Matrix.multiply(A.transpose(), A).subtract(Matrix.identityMatrix(3).multiply(3)))[1]);
		
		double[] lambdas = Matrix.multiply(A.transpose(), A).eigenvalues(1000);
		ArrayUtil.printArr(lambdas);
		
		// look at page 314 for power method and shifted power method
		
//		Matrix[] SVD = Matrix.singularValueDecomposition(A, 1000);
//	
//		Matrix U = SVD[0];
//		Matrix S = SVD[1];
//		Matrix V = SVD[2];
//		
//		System.out.println(Matrix.multiply(Matrix.multiply(U, S), V.transpose()));
		
	}
	
}

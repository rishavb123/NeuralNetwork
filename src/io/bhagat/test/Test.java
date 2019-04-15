package io.bhagat.test;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.util.ArrayUtil;

public class Test {

	public static void main(String[] args)
	{
		Matrix A = new Matrix(new double[][]{
			{3, 2, 5},
			{1, 3, 1},
			{2, 4, 4}
		});
		
				
		Matrix.EigenSolution eigenSolution = A.eigenproblem(1000);
		
		ArrayUtil.printArr(eigenSolution.eigenvalues);
		ArrayUtil.printArr(eigenSolution.eigenvectors);
		System.out.println(eigenSolution.eigenvectors[0].getMagnitude());
	}
	
}

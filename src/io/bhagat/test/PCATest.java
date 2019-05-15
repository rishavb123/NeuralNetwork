package io.bhagat.test;

import io.bhagat.ai.unsupervised.PrincipalComponentAnalysis;
import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;

public class PCATest {

	public static void main(String[] args) {
		Matrix X = new Matrix(new double[][] {
			{-3.8, -3.9, -40.1, -50},
			{-2.7, -2.3, -3, 10},
			{-1, -1.1234, -10.223, -12},
			{1.2, 1, 1.1, 18},
			{2.111, 2.2, 20.3, 10},
			{4.0001, 3.9, 4, 9},
			{52, 10, 32, 36}
		});
		
		var pca = new PrincipalComponentAnalysis(X);
		System.out.println(pca.dimensionReduction());
		System.out.println();
		System.out.println(pca.dimensionReduction(new Vector(3, 4, 3, 18)));
	}

}

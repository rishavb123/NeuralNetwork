package io.bhagat.test;

import io.bhagat.ai.unsupervised.PrincipalComponentAnalysis;
import io.bhagat.math.linearalgebra.Matrix;

public class PCATest {

	public static void main(String[] args) {
		Matrix X = new Matrix(new double[][] {
			{1, 2, 0.5111, 0.1},
			{2, 4, 1, 0},
			{3, 6.02, 1.51, 0},
			{6, 12.52, 2, 0},
			{2, 4.01, 2.5, 0},
			{1, 1.15, 3.01, 0}
		});
		
		var pca = new PrincipalComponentAnalysis(X);
		System.out.println(pca.dimensionReduction());
	}

}

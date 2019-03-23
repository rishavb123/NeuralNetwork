package io.bhagat.test;

import io.bhagat.artificialintelligence.NeuralNetwork;
import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;

public class NNTest {

	public static void main(String[] args) {
		NeuralNetwork nn = new NeuralNetwork(2, 2, 1);
//		System.out.println(nn.feedForward(new Vector(1, 0).toMatrix()));
		
		double[] inputs = new double[] { 1, 0};
		double[] targets = new double[] { 1 };
		
		nn.train(inputs, targets);
		
		Vector u = new Vector(1, 3, 5);
		Vector v = new Vector(2, 3, 4);
		
		System.out.println(Matrix.outer(u.toMatrix(), v.toMatrix()));
		
	}

}

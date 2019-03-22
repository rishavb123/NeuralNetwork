package io.bhagat.test;

import io.bhagat.artificialintelligence.NeuralNetwork;
import io.bhagat.math.linearalgebra.Vector;

public class NNTest {

	public static void main(String[] args) {
		NeuralNetwork nn = new NeuralNetwork(3, 4, 1);
		System.out.println(nn.feedForward(new Vector(1, 4, 5)));
		
		double[] inputs = new double[] { 1, 4, 5 };
		double[] targets = new double[] { 0.5 };
		
		nn.train(inputs, targets);
		
	}

}

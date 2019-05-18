package io.bhagat.test;

import io.bhagat.ai.supervised.NeuralNetwork;
import io.bhagat.ai.unsupervised.PrincipalComponentAnalysis;
import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.util.ArrayUtil;
import io.bhagat.util.Timer;

public class Using {

	public static void main(String[] args) {	
		
		int epoch = 100;
		
		Timer t = new Timer();
		System.out.println("Generating inputs . . . ");
		t.start();
		
		double[][] inputs = new double[10000][3];
		
		for(int i = 0; i < 100; i++)
			for(int j = 0; j < 100; j++)
				inputs[i * 100 + j] = new double[]{ i / 100.0, j / 100.0, 0.5 + (Math.random() - 0.5) / 1000 };
		
		System.out.println("Done generating inputs: " + t.elapsed() + " ms");
		System.out.println("Generating outputs . . . ");
		t.restart();
		
		double[][] outputs = new double[inputs.length][3];
		for(int i = 0; i < inputs.length; i++)
			outputs[i] = function(inputs[i]);
		
		System.out.println("Done generating outputs: " + t.elapsed() + " ms");
		System.out.println("Applying principal component analysis . . .");
		t.restart();
		
		PrincipalComponentAnalysis pca = new PrincipalComponentAnalysis(new Matrix(inputs));
		double[][] pcaInputs = pca.dimensionReduction().getData();

		System.out.println("Done applying principal component analysis (Reduced to " + pcaInputs[0].length + " dimensions): " + t.elapsed() + " ms");
		System.out.println("Creating neural networks . . .");
		t.restart();
		
		NeuralNetwork nn1 = new NeuralNetwork(3, 3, 3, 3);
		NeuralNetwork nn2 = new NeuralNetwork(pcaInputs[0].length, 3, 3, 3);
		
		System.out.println("Done creating neural networks: " + t.elapsed() + " ms");
		System.out.println("Training neural network with original data . . .");
		t.restart();
		
		for(int i = 0; i < epoch * inputs.length; i++) {
			nn1.train(inputs[i%inputs.length], outputs[i%inputs.length]);
		}
		
		System.out.println("Done training neural network with " + epoch * inputs.length + " samples from original data: " + t.elapsed() + " ms");
		System.out.println("Training neural network with principal components . . .");
		t.restart();
		
		for(int i = 0; i < epoch * inputs.length; i++) {
			nn2.train(pcaInputs[i%inputs.length], outputs[i%inputs.length]);
		}

		System.out.println("Done training neural network with " + epoch * inputs.length + " samples from principal component data: " + t.elapsed() + " ms");
		System.out.println("Testing neural networks . . .\n");
		t.restart();
		
		double[] input = { 0.9, 0.88, 0.52 };
		
		System.out.print("Expected: ");
		ArrayUtil.printArr(function(input));
		
		System.out.print("Observed 1: ");
		ArrayUtil.printArr(nn1.feedForward(input));
		
		System.out.print("Observed 2 ");
		ArrayUtil.printArr(nn2.feedForward(pca.dimensionReduction(new Vector(input), pcaInputs[0].length).transpose()).toVector().getData());
		
		System.out.println("\nDone testing neural networks: " + t.elapsed() + " ms");

	}

	public static double[] function(double... x)
	{
		return new double[]{ Math.sqrt(x[0]) * 2*x[1]*x[0] * x[2]*x[2], x[0] * x[2] > 0.3? 1: 0, x[1] / (x[0] * x[2]) > 0.8? 1 : 0 };
	}
	
}

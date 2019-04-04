package io.bhagat.projects.iris;

import java.io.Serializable;

public class Perceptron implements Serializable {

	private static final long serialVersionUID = 5399676748008483267L;

	double[] weights;
	double bias;
	
	double learningRate;
	
	public Perceptron(int n)
	{
		weights = new double[n];
		
		for(int i = 0; i < weights.length; i++)
			weights[i] = Math.random()*2 - 1;
				
		bias = Math.random()*2 - 1;
		
		learningRate = 0.1;
	}
	
	public void train(double[] inputs, int target)
	{
		int guess = guess(inputs);
		int err = target - guess;
		for(int i = 0; i < weights.length; i++)
			weights[i] += inputs[i] * err * learningRate;
		bias += err * learningRate;
	}
	
	public int guess(double[] inputs)
	{
		int sum = 0;
		for(int i = 0; i < inputs.length; i++)
			sum += weights[i] * inputs[i];
		return activation(sum);
	}
	
	public int activation(double x)
	{
		return (x > 0)? 1 : 0;
	}
	
}

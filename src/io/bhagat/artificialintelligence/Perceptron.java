package io.bhagat.artificialintelligence;

import io.bhagat.math.Function;
import io.bhagat.math.Vector;

/**
 * @author Bhagat
 * A class that will act as a Perceptron 
 */
public class Perceptron {

	private Vector weights;
	private double learningRate;
	private Function<Double, Integer> activationFunction;
	private double learningRateFactor;
	
	public static final int defaultN = 2;
	public static final double defaultLearningRate = 0.2;
	public static final Function<Double, Integer> defaultActivationFunction = new Function<Double, Integer>() {

		@Override
		public Integer f(Double x) {
			return (x > 0)? 1 : 0;
		}
		
	};
	public static final double defaultLearningRateFactor = 1;
	
	public Perceptron(int n, double learningRate, Function<Double, Integer> activationFunction, double learningRateFactor) {

		double[] weightsArr = new double[n];
		this.learningRate = learningRate;
		
		for(int i = 0; i < weightsArr.length; i++)
			weightsArr[i] = Math.random()*2 - 1;
		
		weights = new Vector(weightsArr);
		this.activationFunction = activationFunction;
		this.learningRateFactor = learningRateFactor;
	}
	
	public Perceptron(int n, double learningRate, Function<Double, Integer> activationFunction)
	{
		this(n, learningRate, activationFunction, defaultLearningRateFactor);
	}
	
	public Perceptron(int n, double learningRate)
	{
		this(n, learningRate, defaultActivationFunction);
	}
	
	public Perceptron(int n) {
		this(n, defaultLearningRate);
	}
	
	public Perceptron() {
		this(defaultN);
	}
	
	public int guess(Vector inputs) {
		return activation(weights.dot(inputs));
	}
	
	public int guess(double[] inputs) {
		return guess(new Vector(inputs));
	}
	
	public void train(Vector inputs, int target)
	{
		int guess = guess(inputs);
		float err = target - guess;
		weights.add(inputs.multiply(err * learningRate));
	}
	
	public void train(double[] inputs, int target)
	{
		train(new Vector(inputs), target);
		learningRate *= learningRateFactor;
	}
	
	public int activation(double x)
	{
		return activationFunction.f(x);
	}

}

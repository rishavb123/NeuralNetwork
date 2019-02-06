package io.bhagat.artificialintelligence;

import io.bhagat.math.Function;
import io.bhagat.math.Vector;

/**
 * A class that will act as a Perceptron that takes in inputs and then generates an outputs based on previous training
 * @author Bhagat
 */
public class Perceptron {

	/**
	 * A vector that holds the weights for the inputs (for the weighted sum)
	 */
	private Vector weights;
	/**
	 * The step that it takes in the direction of the error
	 */
	private double learningRate;
	/**
	 * The activation function that is run on the output data to constrain the data to known limits
	 */
	private Function<Double, Integer> activationFunction;
	/**
	 * A factor that the learning rate is multiplied with each time the perceptron is trained
	 */
	private double learningRateFactor;
	/**
	 * The bias, or the weight for the invisible input of 1
	 */
	private double bias;
	/**
	 * The "input" for the bias weight
	 */
	private double biasLearningRateFactor;
	
	/**
	 * default number of inputs
	 */
	public static final int defaultN = 2;
	/**
	 * default learning rate
	 */
	public static final double defaultLearningRate = 0.2;
	/**
	 * default activation function to use if something is positive or negative to 1 or 0
	 */
	public static final Function<Double, Integer> defaultActivationFunction = new Function<Double, Integer>() {

		@Override
		public Integer f(Double x) {
			return (x > 0)? 1 : 0;
		}
		
	};
	/**
	 * default learning rate factor
	 */
	public static final double defaultLearningRateFactor = 1;
	/**
	 * default bias learning rate factor
	 */
	public static final double defaultBiasLearningRateFactor = 1;
	
	
	/**
	 * constructs the Perceptron with custom parameters
	 * @param n size of input and weight vectors
	 * @param learningRate the learning rate
	 * @param learningRateFactor the factor for the learning rate to multiply on training
	 * @param biasLearningRateFactor the factor which will be multiplied with the learning rate when adding to the bias
	 * @param activationFunction the activation function
	 */
	public Perceptron(int n, double learningRate, double learningRateFactor, double biasLearningRateFactor, Function<Double, Integer> activationFunction) {

		double[] weightsArr = new double[n];
		this.learningRate = learningRate;
		
		for(int i = 0; i < weightsArr.length; i++)
			weightsArr[i] = Math.random()*2 - 1;
		
		weights = new Vector(weightsArr);
		
		bias = Math.random()*2 - 1;
		
		this.activationFunction = activationFunction;
		this.learningRateFactor = learningRateFactor;
		this.biasLearningRateFactor = biasLearningRateFactor;
	}
	
	/**
	 * constructs a Perceptron with a default activation function
	 * @param n size of input and weight vectors
	 * @param learningRate the learning rate
	 * @param learningRateFactor the learning rate factor
	 * @param biasLearningRateFactor the bias learning rate
	 */
	public Perceptron(int n, double learningRate, double learningRateFactor, double biasLearningRateFactor)
	{
		this(n,  learningRate, learningRateFactor, biasLearningRateFactor, defaultActivationFunction);
	}
	
	/**
	 * constructs a Perceptron with the default bias learning rate, and activation function
	 * @param n size of input and weight vectors
	 * @param learningRate the learning rate
	 * @param learningRateFactor the learning rate factor
	 */
	public Perceptron(int n, double learningRate, double learningRateFactor)
	{
		this(n, learningRate, learningRateFactor, defaultBiasLearningRateFactor);
	}
	
	/**
	 * constructs a Perceptron with the default learning rate factor, bias learning rate, and activation function
	 * @param n size of input and weight vectors
	 * @param learningRate the learning rate
	 */
	public Perceptron(int n, double learningRate)
	{
		this(n, learningRate, defaultLearningRateFactor);
	}
	
	/**
	 * constructs a Perceptron with the default learning rate factor, activation function, bias learning rate, and learning rate
	 * @param n size of input and weight vectors
	 */
	public Perceptron(int n) {
		this(n, defaultLearningRate);
	}
	
	/**
	 * constructs a Perceptron with all the default parameters
	 */
	public Perceptron() {
		this(defaultN);
	}
	
	/**
	 * takes a Vector for inputs and then guesses the output
	 * @param inputs the inputs
	 * @return the guess of what the output should be
	 */
	public int guess(Vector inputs) {
		return activationFunction.f(weights.dot(inputs) + bias);
	}
	
	/**
	 * takes in an input array and converts it to a vector and sends it to guess(Vector)
	 * @param inputs the input array
	 * @return the guess of what the output should be
	 */
	public int guess(double[] inputs) {
		return guess(new Vector(inputs));
	}
	
	/**
	 * takes in a Vector for the inputs and then compares the guess and the target output and adjusts the weights and bias accordingly
	 * @param inputs the inputs
	 * @param target the target output
	 */
	public void train(Vector inputs, int target)
	{
		int guess = guess(inputs);
		float err = target - guess;
		weights.add(inputs.multiply(err * learningRate));
		bias += biasLearningRateFactor * err * learningRate;
		learningRate *= learningRateFactor;
	}
	
	/**
	 * converts the input array into a Vector and sends it to the train(Vector, integer) method
	 * @param inputs the inputs array
	 * @param target the target output
	 */
	public void train(double[] inputs, int target)
	{
		train(new Vector(inputs), target);
	}

	/**
	 * @return the learningRate
	 */
	public double getLearningRate() {
		return learningRate;
	}

	/**
	 * @param learningRate the learningRate to set
	 */
	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	/**
	 * @return the activationFunction
	 */
	public Function<Double, Integer> getActivationFunction() {
		return activationFunction;
	}

	/**
	 * @param activationFunction the activationFunction to set
	 */
	public void setActivationFunction(Function<Double, Integer> activationFunction) {
		this.activationFunction = activationFunction;
	}

	/**
	 * @return the learningRateFactor
	 */
	public double getLearningRateFactor() {
		return learningRateFactor;
	}

	/**
	 * @param learningRateFactor the learningRateFactor to set
	 */
	public void setLearningRateFactor(double learningRateFactor) {
		this.learningRateFactor = learningRateFactor;
	}

	/**
	 * @return the weights
	 */
	public Vector getWeights() {
		return weights;
	}

	/**
	 * @return the bias
	 */
	public double getBias() {
		return bias;
	}

}

package io.bhagat.artificialintelligence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import io.bhagat.math.Function;
import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.math.statistics.QuantitativeDataList;

/**
 * a class for a Neural Network that will take in inputs and send them through difference layers and generate outputs
 * @author Bhagat
 */
public class NeuralNetwork implements Serializable{

	private static final long serialVersionUID = 140349252948361896L;
	
	/**
	 * an array defining the shape of the network
	 */
	private int[] shape;
	/**
	 * the number of inputs
	 */
	private int numOfInputs;
	/**
	 * the number of outputs
	 */
	private int numOfOutputs;
	/**
	 * the number of hidden nodes in each hidden layer
	 */
	private int[] numsOfHiddens;
	
	/**
	 * the weights for each layer
	 */
	private Matrix[] weights;
	/**
	 * the bias for each layer
	 */
	private Matrix[] bias;
	
	/**
	 * the learning rate
	 */
	private double learingRate;
	
	/**
	 * the activation function
	 */
	private transient Function<Double, Double> activationFunction;
	
	/**
	 * the default activation function
	 * the sigmoid function
	 * takes any numbers and puts it between 0 and 1
	 */
	public static Function<Double, Double> defaultActivationFunction = new Function<Double, Double>() {

		@Override
		public Double f(Double x) {
			return 1.0/(1 + Math.exp( -x));
		}
		
	};
		
	/**
	 * Creates a NeuralNetwork with a specified shape
	 * @param shape an array defining the shape of the NeuralNetwork
	 * @param activationFunction the activation function
	 */
	public NeuralNetwork(Function<Double, Double> activationFunction, int... shape)
	{
		this.activationFunction = activationFunction;
		switch(shape.length)
		{
			case 0:
				this.shape = new int[2];
				this.shape[0] = 1;
				this.shape[1] = 1;
				break;
			case 1:
				this.shape = new int[2];
				this.shape[0] = shape[0];
				this.shape[1] = shape[0];
				break;
			default:
				this.shape = shape;
				break;
		}
		
		numOfInputs = this.shape[0];
		numOfOutputs = this.shape[this.shape.length - 1];
		numsOfHiddens = new int[this.shape.length - 2];
		
		weights = new Matrix[this.shape.length - 1];
		bias = new Matrix[this.shape.length - 1];
		
		for(int i = 0; i < weights.length; i++)
		{
			weights[i] = new Matrix(this.shape[i + 1], this.shape[i]);
			weights[i].randomize();
			
			bias[i] = new Matrix(this.shape[i + 1], 1);
			bias[i].randomize();
			
		}
		
		for(int i = 0; i < numsOfHiddens.length; i++)
			numsOfHiddens[i] = this.shape[i+1];
		
		learingRate  = 0.1;
		
	}
	
	/**
	 * Creates a NeuralNetwork with a specified shape
	 * @param shape an array defining the shape of the NeuralNetwork
	 */
	public NeuralNetwork(int... shape)
	{
		this(defaultActivationFunction, shape);
	}
	
	/**
	 * sets the output of feedForward into the dataPoint
	 * @param dataPoint the data point
	 */
	public void feedForward(DataPoint dataPoint)
	{
		dataPoint.setOutputs(feedForward(dataPoint.getInputs()));
	}
	
	/**
	 * the feed forward algorithm for making a guess based on the inputs
	 * @param inputs an array of inputs
	 * @return an array for the outputs
	 */
	public double[] feedForward(double... inputs)
	{
		return feedForward(new Vector(inputs)).getData();
	}
	
	/**
	 * the feed forward algorithm for making a guess based on the inputs
	 * @param inputs a Vector that hold the inputs
	 * @return a Vector for the outputs
	 */
	public Vector feedForward(Vector inputs)
	{
		return feedForward(inputs.toMatrix()).toVector();
	}
	
	/**
	 * the feed forward algorithm for making a guess based on the inputs
	 * @param inputs a Matrix that hold the inputs
	 * @return a Matrix for the outputs
	 */
	public Matrix feedForward(Matrix inputs)
	{
		Matrix[] layers = new Matrix[shape.length];
		layers[0] = inputs;
		
		for(int i = 1; i < layers.length; i++)
		{
			layers[i] = (Matrix.multiply(weights[i - 1], layers[i - 1]).add(bias[i - 1])).map(activationFunction);
		}
		
		return layers[layers.length - 1];
	}
	
	/**
	 * trains the network using all the data points in the data set
	 * @param dataSet
	 */
	public void train(DataSet dataSet)
	{
		for(DataPoint dataPoint: dataSet)
			train(dataPoint);
	}
	
	/**
	 * trains the network using a data point
	 * @param dataPoint the data point
	 */
	public void train(DataPoint dataPoint)
	{
		train(dataPoint.getInputs(), dataPoint.getOutputs());
	}
	
	/**
	 * trains the network based on certain inputs and the known targets for those inputs
	 * @param inputs the inputs
	 * @param targets the targets
	 */
	public void train(double[] inputs, double[] targets)
	{
		train(new Vector(inputs), new Vector(targets));
	}
	
	/**
	 * trains the network based on certain inputs and the known targets for those inputs
	 * @param inputs the inputs
	 * @param targets the targets
	 */
	public void train(Vector inputs, Vector targets)
	{
		train(inputs.toMatrix(), targets.toMatrix());
	}
	
	/**
	 * trains the network based on certain inputs and the known targets for those inputs
	 * @param inputs the inputs
	 * @param targets the targets
	 */
	public void train(Matrix inputs, Matrix targets)
	{
		Matrix[] layers = new Matrix[shape.length];
		layers[0] = inputs;
		
		for(int i = 1; i < layers.length; i++)
		{
			layers[i] = (Matrix.multiply(weights[i - 1], layers[i - 1]).add(bias[i - 1])).map(activationFunction);
		}
		
		Matrix outputs = layers[layers.length - 1];
		
		Matrix E = targets.clone().subtract(outputs);
		Matrix[] errors = new Matrix[shape.length];
		errors[shape.length - 1] = E;			
		
		Matrix[] dWeights = new Matrix[weights.length];
		Matrix[] dBias = new Matrix[bias.length];
		
		for(int i = weights.length; i > 0; i--)
		{
			errors[i - 1] = Matrix.multiply(weights[i - 1].transpose(), errors[i]);
			Matrix temp = Matrix.hadamardProduct(errors[i], Matrix.hadamardProduct(layers[i], layers[i].clone().map(new Function<Double, Double>() {

				@Override
				public Double f(Double x) {
					return 1 - x;
				}
				
			})));
			dBias[i - 1] = temp.multiply(learingRate);
			dWeights[i - 1] = Matrix.outer(temp, layers[i - 1]).multiply(learingRate);
		}
		
		for(int i = 0; i < weights.length; i++)
		{
			bias[i].add(dBias[i]);
			weights[i].add(dWeights[i]);
		}
	}
	
	/**
	 * tests the data
	 * @param dataSet the testing data set
	 * @return the accuracy
	 */
	public double test(DataSet dataSet)
	{
		Vector totalTargetOutput = new Vector(new double[dataSet.get(0).getOutputs().length]);
		Vector totalError = new Vector(new double[dataSet.get(0).getOutputs().length]);
		
		for(DataPoint dataPoint: dataSet)
		{
			Vector targetOutput = new Vector(dataPoint.getOutputs());
			totalTargetOutput.add(targetOutput);
			feedForward(dataPoint);			
			totalError.add(targetOutput.clone().subtract(new Vector(dataPoint.getOutputs())));	
		}
		
		return 1 - new QuantitativeDataList(totalError.divide(totalTargetOutput).getData()).average();
	}
	
	/**
	 * tests the data
	 * @param dataSet the testing data set
	 * @param log whether or not to log out the tests
	 * @return the accuracy
	 */
	public double test(DataSet dataSet, boolean log)
	{
		Vector totalTargetOutput = new Vector(new double[dataSet.get(0).getOutputs().length]);
		Vector totalError = new Vector(new double[dataSet.get(0).getOutputs().length]);
		
		for(DataPoint dataPoint: dataSet)
		{
			if(log) {
				System.out.println("Test " + (dataSet.indexOf(dataPoint) + 1));
				System.out.println(dataPoint);
			}
			Vector targetOutput = new Vector(dataPoint.getOutputs());
			totalTargetOutput.add(targetOutput);
			feedForward(dataPoint);			
			totalError.add(targetOutput.clone().subtract(new Vector(dataPoint.getOutputs())));	
			
			if(log)
				System.out.println(dataPoint + "\n\n");
		}
		
		return 1 - new QuantitativeDataList(totalError.divide(totalTargetOutput).map(new Function<Double, Double> () {

			@Override
			public Double f(Double x) {
				return Math.abs(x);
			}
			
		}).getData()).average();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		activationFunction = defaultActivationFunction;
	}
	
	/**
	 * @return the numOfInputs
	 */
	public int getNumOfInputs() {
		return numOfInputs;
	}

	/**
	 * @return the numOfOutputs
	 */
	public int getNumOfOutputs() {
		return numOfOutputs;
	}

	/**
	 * @return the numsOfHiddens
	 */
	public int[] getNumsOfHiddens() {
		return numsOfHiddens;
	}

	/**
	 * @return the activationFunction
	 */
	public Function<Double, Double> getActivationFunction() {
		return activationFunction;
	}

	/**
	 * @param activationFunction the activationFunction to set
	 */
	public void setActivationFunction(Function<Double, Double> activationFunction) {
		this.activationFunction = activationFunction;
	}
	
}

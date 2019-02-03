package io.bhagat.artificialintelligence;

import io.bhagat.math.Matrix;
import io.bhagat.math.Vector;

/**
 * @author Bhagat
 * a class for a Neural Network that will take in inputs and send them through difference layers and generate outputs
 */
public class NeuralNetwork {

	private int[] shape;
	private int numOfInputs;
	private int numOfOutputs;
	private int[] numsOfHiddens;
	
	private Matrix[] weights;
	private Matrix[] bias;
	
	public NeuralNetwork(int... shape)
	{
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
		
	}
	
	public double[] feedForward(double[] inputs)
	{
		return feedForward(new Vector(inputs)).getData();
	}
	
	public Vector feedForward(Vector inputs)
	{
		return feedForward(inputs.toMatrix()).toVector();
	}
	
	public Matrix feedForward(Matrix inputs)
	{
		Matrix[] layers = new Matrix[shape.length];
		layers[0] = inputs;
		
		for(int i = 1; i < layers.length; i++)
		{
			layers[i] = /*activationFunction.f(*/Matrix.multiply(weights[i - 1], layers[i - 1]).add(bias[i - 1]);
		}
		
		return layers[layers.length - 1];
	}
	
}

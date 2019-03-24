package io.bhagat.server;

import io.bhagat.artificialintelligence.NeuralNetwork;
import io.bhagat.math.Function;
import py4j.GatewayServer;

/**
 * Serves a neural network to python through a py4j gateway server
 * @author Bhagat
 */
public class Py4jNeuralNetworkServer extends GatewayServer{

	/**
	 * Creates a NeuralNetwork with a specified shape
	 * @param shape an array defining the shape of the NeuralNetwork
	 * @param activationFunction the activation function
	 */
	public Py4jNeuralNetworkServer(Function<Double, Double> activationFunction, int... shape)
	{
		super(new NeuralNetwork(activationFunction, shape));		
	}
	
	/**
	 * Creates a NeuralNetwork with a specified shape
	 * @param shape an array defining the shape of the NeuralNetwork
	 */
	public Py4jNeuralNetworkServer(int... shape)
	{
		this(NeuralNetwork.defaultActivationFunction, shape);
	}

	/**
	 * @see py4j.GatewayServer#start()
	 */
	@Override
	public void start() {
		System.out.println("Starting Gateway Server . . .");
		super.start();
	}

	
	
}

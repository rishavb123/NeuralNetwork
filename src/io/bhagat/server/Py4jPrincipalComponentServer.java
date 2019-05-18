package io.bhagat.server;

import io.bhagat.ai.unsupervised.PrincipalComponentAnalysis;
import io.bhagat.math.linearalgebra.Matrix;
import py4j.GatewayServer;

/**
 * Serves a neural network to python through a py4j gateway server
 * @author Bhagat
 */
public class Py4jPrincipalComponentServer extends GatewayServer{

	/**
	 * Creates and serves a NeuralNetwork with a specified shape and activation
	 * @param shape an array defining the shape of the NeuralNetwork
	 * @param activationFunction the activation function
	 */
	public Py4jPrincipalComponentServer()
	{
		super(new PrincipalComponentAnalysis(new Matrix(2, 2)));		
	}

	/**
	 * @see py4j.GatewayServer#start()
	 */
	@Override
	public void start() {
		System.out.println("Starting Gateway Server . . .");
		super.start();
	}
	
	public static void main(String[] args) {
		new Py4jPrincipalComponentServer().start();
	}
	
}

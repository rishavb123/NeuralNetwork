package io.bhagat.test;

import java.io.IOException;

import io.bhagat.artificialintelligence.DataPoint;
import io.bhagat.artificialintelligence.DataSet;
import io.bhagat.artificialintelligence.NeuralNetwork;
import io.bhagat.math.Function;
import io.bhagat.util.ArrayUtil;
import io.bhagat.util.SerializableUtil;
import io.bhagat.util.Timer;

public class NNTest {

	public static final boolean trainNewNetwork = false;
	
	public static void main(String[] args) {
		
		String saveTo = "random_neural_network/neural_network_4.ser";
		String readFrom = "random_neural_network/neural_network_4.ser";
		
		NeuralNetwork nn = new NeuralNetwork(3, 9, 8, 2);
		Timer timer = new Timer();
		
		Function<double[], double[]> function = new Function<double[], double[]> () {

			@Override
			public double[] f(double[] x) {
				return new double[] { (x[0] * x[1] * x[2] > 0.3 && Math.pow(x[0], x[1]) * x[2] > 0.4) ? 1 : 0, ((x[0] * x[1] > 0.4 && Math.pow(x[0], x[2]) > 0.25) ? 1 : 0) };
			}
			
		};
		
		if(trainNewNetwork)
		{
			DataSet dataSet = new DataSet();
			
		
			for(int i = 0; i < 5000000; i++)
			{
				double[] inputs = { Math.random(), Math.random(), Math.random() };
				dataSet.add(new DataPoint(inputs, function.f(inputs)));
			}
			
			System.out.println("Training . . .");
			timer.start();
			nn.train(dataSet);
			System.out.println("Done Training: " + timer.elapsed() + " ms\n\n");
			
			System.out.println("Serializing . . . ");
			timer.start();
			try {
				SerializableUtil.serialize(nn, saveTo);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Done Serializing: " + timer.elapsed() + " ms\n\n");
		}
		else
		{
			System.out.println("Deserializing . . .");
			timer.start();
			try {
				nn = SerializableUtil.deserialize(readFrom);
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Done Deserializing: " + timer.elapsed() + " ms\n\n");
		}
		
		System.out.println("Testing . . .\n");
		timer.start();
		
		DataSet testData = new DataSet();
		
		for(int i = 0; i < 250; i++)
		{
			double[] inputs = { Math.random(), Math.random(), Math.random() };
			testData.add(new DataPoint(inputs, function.f(inputs)));
		}
		
		System.out.println(nn.test(testData, true));
		
		System.out.println("Done Testing: " + timer.elapsed() + " ms\n\n");
		
	}

	public static void main2(String[] args)
	{
		NeuralNetwork nn = new NeuralNetwork(2, 4, 1);
		if(trainNewNetwork)
		{
			double[][] inputs = new double[][] { 
				{1, 0},
				{0, 1},
				{1, 1},
				{0, 0}
			};
			double[][] targets = new double[][] { 
				{1},
				{1},
				{0},
				{0}
			};
			
			for(int i = 0; i < 10000000; i++)
			{
				int index = (int)(Math.random()*4);
				nn.train(inputs[index], targets[index]);
			}
			
			try {
				SerializableUtil.serialize(nn, "xor_neural_network.ser");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else
			try {
				nn = SerializableUtil.deserialize("xor_neural_network.ser");
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		
		
		ArrayUtil.printArr(nn.feedForward(1, 0));
		ArrayUtil.printArr(nn.feedForward(0, 1));
		ArrayUtil.printArr(nn.feedForward(1, 1));
		ArrayUtil.printArr(nn.feedForward(0, 0));
		
		
	}
	
}

package io.bhagat.test;

import java.io.IOException;

import io.bhagat.artificialintelligence.DataPoint;
import io.bhagat.artificialintelligence.DataSet;
import io.bhagat.artificialintelligence.NeuralNetwork;
import io.bhagat.util.ArrayUtil;
import io.bhagat.util.SerializableUtil;
import io.bhagat.util.Timer;

public class NNTest {

	public static final boolean trainNewNetwork = false;
	
	public static void main(String[] args) {
		
		String saveTo = "random_neural_network2.ser";
		String readFrom = "random_neural_network2.ser";
		
		NeuralNetwork nn = new NeuralNetwork(3, 9, 8, 2);
		Timer timer = new Timer();
		
		if(trainNewNetwork)
		{
			DataSet dataSet = new DataSet();
			
		
			for(int i = 0; i < 5000000; i++)
			{
				double x1 = Math.random();
				double x2 = Math.random();
				double x3 = Math.random();
	
				dataSet.add(new DataPoint(new double[] {x1, x2, x3}, new double[] { (x1 * x2 * x3 > 0.3 && Math.pow(x1, x2) * x3 > 0.4) ? 1 : 0, ((x1 * x2 > 0.4 && Math.pow(x1, x3) > 0.25) ? 1 : 0) }));
				
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
		
		for(int i = 0; i < 25; i++)
		{
			double x1 = Math.random();
			double x2 = Math.random();
			double x3 = Math.random();
			
			DataPoint dataPoint = new DataPoint(new double[] {x1, x2, x3}, new double[] { (x1 * x2 * x3 > 0.3 && Math.pow(x1, x2) * x3 > 0.4) ? 1 : 0, ((x1 * x2 > 0.4 && Math.pow(x1, x3) > 0.25) ? 1 : 0) });
			System.out.println("Test " + (i + 1));
			System.out.println(dataPoint);
			nn.feedForward(dataPoint);
			System.out.println(dataPoint + "\n\n");
			
		}
		
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

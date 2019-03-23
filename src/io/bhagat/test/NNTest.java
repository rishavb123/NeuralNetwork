package io.bhagat.test;

import java.io.IOException;

import io.bhagat.artificialintelligence.NeuralNetwork;
import io.bhagat.util.ArrayUtil;
import io.bhagat.util.SerializableUtil;

public class NNTest {

	public static void main(String[] args) {
		NeuralNetwork nn = new NeuralNetwork(2, 4, 1);
		
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

		ArrayUtil.printArr(nn.feedForward(1, 0));
		ArrayUtil.printArr(nn.feedForward(0, 1));
		ArrayUtil.printArr(nn.feedForward(1, 1));
		ArrayUtil.printArr(nn.feedForward(0, 0));
		
//		try {
//			NeuralNetwork nn = SerializableUtil.deserialize("xor_neural_network.ser");
//			ArrayUtil.printArr(nn.feedForward(1, 0));
//			ArrayUtil.printArr(nn.feedForward(0, 1));
//			ArrayUtil.printArr(nn.feedForward(1, 1));
//			ArrayUtil.printArr(nn.feedForward(0, 0));
//		} catch (ClassNotFoundException | IOException e) {
//			e.printStackTrace();
//		}
		
		
		
	}

}

package io.bhagat.projects.handwrittendigits;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import io.bhagat.artificialintelligence.NeuralNetwork;
import io.bhagat.math.SerializableFunction;
import io.bhagat.util.Timer;

public class TrainNetwork {

	public static void main(String[] args) throws FileNotFoundException {
		
		Timer t = new Timer();
		
		ArrayList<double[]> images = new ArrayList<>();
		ArrayList<double[]> labels = new ArrayList<>();
		
		System.out.println("Reading data . . .");
		t.start();
		
        ReadData.read("csv/train/images.csv", "csv/train/labels.csv", images, labels);
        
        System.out.println("Done Reading data: " + t.elapsed() + " ms");
        
        NeuralNetwork neuralNetwork = new NeuralNetwork(new SerializableFunction<Double, Double> () {
        	
			private static final long serialVersionUID = -1135730787063088645L;

			@Override
			public Double f(Double x) {
				return (Math.atan(x) / Math.PI) + 0.5;
			}
        	
        }, 784, 64, 10);
        
        System.out.println("Training . . .");
        t.restart();
        for(int i = 0; i < images.size(); i++)
        	neuralNetwork.train(images.get(i), labels.get(i));
        System.out.println("Done Training: " + t.elapsed() + " ms");
        
        System.out.println("Serializing . . .");
        t.restart();
        neuralNetwork.serialize("mnist/network5.ser");
        System.out.println("Done Serializing: " + t.elapsed() + " ms");
        
	}

}

package io.bhagat.projects.iris;

import java.io.IOException;

import io.bhagat.util.FileHandler;

public class Runner {

	static String[] possibilities = {"Iris-setosa", "Iris-versicolor"};
	
	static double[][] values;
	static String[] classes;
	
	static double[][] testValues;
	static String[] testClasses;
	
	public static void readData()
	{
		String s = FileHandler.read("irisdata/irisdata.csv");
		String[] rows = s.split("\n");
		values = new double[rows.length - 1][4];
		classes = new String[rows.length - 1];
		for(int i = 0; i < rows.length - 1; i++)
		{
			String[] arr = rows[i + 1].split(","); 
			for(int j = 0; j < 4; j++)
				values[i][j] = Double.parseDouble(arr[j]);
			classes[i] = arr[4];
		}
		
		for(int i = 0; i < 1000; i++)
		{
			int j = (int) (Math.random()*values.length);
			int k = (int) (Math.random()*values.length);
			
			double[] temp = values[j];
			values[j] = values[k];
			values[k] = temp;
			
			String tempString = classes[j];
			classes[j] = classes[k];
			classes[k] = tempString;
		}
		
		String testS = FileHandler.read("irisdata/iristestdata.csv");
		String[] testRows = testS.split("\n");
		testValues = new double[testRows.length - 1][4];
		testClasses = new String[testRows.length - 1];
		for(int i = 0; i < testRows.length - 1; i++)
		{
			String[] arr = testRows[i + 1].split(","); 
			for(int j = 0; j < 4; j++)
				testValues[i][j] = Double.parseDouble(arr[j]);
			testClasses[i] = arr[4];
		}
		
		try {
			SerializableUtil.serialize(values, "iris/values.ser");
			SerializableUtil.serialize(classes, "iris/classes.ser");
			SerializableUtil.serialize(testValues, "iris/testValues.ser");
			SerializableUtil.serialize(testClasses, "iris/testClasses.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}		
			
	}
	
	public static void loadData()
	{
		try {
			values = SerializableUtil.deserialize("iris/values.ser");
			classes = SerializableUtil.deserialize("iris/classes.ser");
			testValues = SerializableUtil.deserialize("iris/testValues.ser");
			testClasses = SerializableUtil.deserialize("iris/testClasses.ser");		
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		loadData();
		
		Perceptron p = new Perceptron(4);
		
		System.out.println("Training . . .\n\n");
		
		for(int i = 0; i < values.length; i++)
		{
			p.train(values[i], getIndex(classes[i]));
		}
		
		System.out.println("Testing . . . \n");
		
		int numCorrect = 0;
		
		for(int i = 0; i < testValues.length; i++) {
			System.out.println("Test #" + (i + 1));
			System.out.println("Target: "+ testClasses[i]);
			System.out.println("Guess: " + possibilities[p.guess(testValues[i])] + "\n");
			
			if(testClasses[i].equals(possibilities[p.guess(testValues[i])]))
				numCorrect++;
			
		}
		
		System.out.println("\n" + numCorrect + " correct out of 10");
		
	}
	
	public static int getIndex(String classs)
	{
		for(int i = 0; i < possibilities.length; i++)
			if(possibilities[i].equals(classs))
				return i;
		return -1;
	}
	
}

package io.bhagat.test;

import io.bhagat.ai.supervised.DataPoint;
import io.bhagat.ai.supervised.DataSet;

public class DataSetTest {

	public static void main(String[] args)
	{
		DataSet dataSet = new DataSet();
		for(int i = 0; i < 5; i++)
			dataSet.add(new DataPoint(new double[] {i, 0}, new double[] {0, i+2}));
		System.out.println(dataSet + "\n\n");
		dataSet.shuffle();
		System.out.println(dataSet + "\n\n");
	}
	
}

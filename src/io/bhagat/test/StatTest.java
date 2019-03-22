package io.bhagat.test;

import java.io.IOException;

import io.bhagat.math.statistics.QuantitativeDataList;
import io.bhagat.util.ArrayUtil;
import io.bhagat.util.SerializableUtil;

public class StatTest {

	public static void main(String[] args) {
		
		QuantitativeDataList list = new QuantitativeDataList(1, 2, 3, 4, 5, 6, 7, -111, 8, 9, 100);
		
		System.out.println(list);
		System.out.println();
		
		System.out.println("the average is " + list.mean());
		System.out.println("the median is " + list.median());
		System.out.println("the variance is " + list.variance());
		System.out.println("the standard deviation is " + list.standardDeviation());
		System.out.println();
		
		System.out.print("the 5 point summary is ");
		Double[] ds = new Double[5];
		double[] summary = list.fivePointSummary();
		for(int i = 0; i < 5; i++)
			ds[i] = summary[i];
		ArrayUtil.printArr(ds);
		System.out.println(); 
		
		System.out.println("the outliers are " + list.outliers());
		System.out.println("the list cleaned is " + list.clean());
		System.out.println();
		
		try {
			System.out.println("Saving to file system . . .");
			SerializableUtil.serialize(list, "fakedataset.ser");
			System.out.println("Retrieving from file system . . .");
			list = SerializableUtil.deserialize("fakedataset.ser");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("the list is " + list);
		
	}

}

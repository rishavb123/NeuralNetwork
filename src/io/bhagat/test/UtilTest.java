package io.bhagat.test;

import io.bhagat.math.statistics.QuantitativeDataList;
import io.bhagat.util.FileHandler;

public class UtilTest {

	public static void main(String[] args) {

		FileHandler fileHandler = new FileHandler("test.txt");
		
		QuantitativeDataList list = new QuantitativeDataList(1, 2, 3, 6);
		
		fileHandler.write("the average is " + list.mean());
		fileHandler.append("\nthe median is " + list.median());
		fileHandler.append("\nthe variance is " + list.variance());
		fileHandler.append("\nthe standard deviation is " + list.standardDeviation());
		System.out.println(fileHandler.read());
		
	}

}

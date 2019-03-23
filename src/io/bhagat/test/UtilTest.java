package io.bhagat.test;

import java.io.IOException;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.math.statistics.QuantitativeDataList;
import io.bhagat.util.FileHandler;
import io.bhagat.util.SerializableUtil;

public class UtilTest {

	public static void main(String[] args) {

		FileHandler fileHandler = new FileHandler("test.txt");
		
		QuantitativeDataList list = new QuantitativeDataList(1, 2, 3, 6);
		
		fileHandler.write("the average is " + list.mean());
		fileHandler.append("\nthe median is " + list.median());
		fileHandler.append("\nthe variance is " + list.variance());
		fileHandler.append("\nthe standard deviation is " + list.standardDeviation());
		System.out.println(fileHandler.read());
		
		try {
			
			SerializableUtil.serialize(new Vector(1,2,3), "vector.ser");
			Vector v = SerializableUtil.deserialize("vector.ser");
			System.out.println(v);
			
			SerializableUtil.serialize(new Vector(1,2,3).toMatrix(), "matrix.ser");
			Matrix m = SerializableUtil.deserialize("matrix.ser");
			System.out.println(m);
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

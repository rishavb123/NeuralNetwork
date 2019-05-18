/**
 * 
 */
package io.bhagat.ai.supervised;

import java.util.ArrayList;

/**
 * A class to hold multiple data points
 * @author Bhagat
 */
public class DataSet extends ArrayList<DataPoint>{

	private static final long serialVersionUID = -165470935915737585L;

	/**
	 * creates a data set
	 */
	public DataSet() {
		super();
	}

	/**
	 * randomizes the order of the data set
	 */
	public void shuffle() {
		for(int i_ = 0; i_ < size() * 2; i_++)
		{
			int i = (int)(Math.random()*size());
			int j = (int)(Math.random()*size());
			set(j, set(i, get(j)));
			
		}
	}
	
	/**
	 * creates a dataset from arrays of inputs and outputs
	 * @param inputs the inputs
	 * @param outputs the outputs
	 * @return the data set
	 */
	public static DataSet create(double[][] inputs, double[][] outputs)
	{
		DataSet dataSet = new DataSet();
		for(int i = 0; i < inputs.length; i++)
			dataSet.add(new DataPoint(inputs[i], outputs[i]));
		return dataSet;
	}
	
	/**
	 * string representation of the data set
	 */
	@Override
	public String toString()
	{
		String s = "";
		for(DataPoint dp: this)
			s += dp.toString() + "\n";
		return s;
	}

}

/**
 * 
 */
package io.bhagat.artificialintelligence;

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

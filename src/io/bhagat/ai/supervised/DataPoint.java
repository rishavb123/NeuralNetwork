package io.bhagat.ai.supervised;

import io.bhagat.util.ArrayUtil;

/**
 * @author Bhagat
 * A data points holding the inputs and target into one object
 */
public class DataPoint {

	/**
	 * the inputs
	 */
	private double[] inputs;
	/**
	 * the outputs
	 */
	private double[] outputs;
	
	/**
	 * creates a data points using inputs and a target
	 * @param inputs the inputs
	 * @param outputs the outputs
	 */
	public DataPoint(double[] inputs, double[] outputs) {
		
		this.inputs = inputs;
		this.outputs = outputs;
	
	}

	/**
	 * @return the inputs
	 */
	public double[] getInputs() {
		return inputs;
	}

	/**
	 * @param inputs the inputs to set
	 */
	public void setInputs(double[] inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the outputs
	 */
	public double[] getOutputs() {
		return outputs;
	}

	/**
	 * @param outputs the outputs to set
	 */
	public void setOutputs(double[] outputs) {
		this.outputs = outputs;
	}
	
	/**
	 * string representation of the data points
	 */
	@Override
	public String toString()
	{
		return ArrayUtil.newArrayList(inputs).toString() + " -> " + ArrayUtil.newArrayList(outputs);
	}

}

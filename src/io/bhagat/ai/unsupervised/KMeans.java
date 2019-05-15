package io.bhagat.ai.unsupervised;

import java.io.Serializable;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;

/**
 * a model of the K Means Algorithm
 * @author Bhagat
 */
public class KMeans implements Serializable{

	private static final long serialVersionUID = 6680228612577440517L;

	public static final int DEFAULT_ITERATIONS = 100;
	
	/**
	 * the number of clusters to find
	 */
	private int k;
	private Vector[] clusters;
	/**
	 * the number of iterations to run the algorithm
	 */
	private int iterations;
	
	/**
	 * creates a K means model
	 * @param k the number of clusters
	 * @param iterations the number of iterations to run the algorithm
	 */
	public KMeans(int k, int iterations) {
		this.k = k;
		clusters = new Vector[k];
		this.iterations = iterations;
	}

	/**
	 * creates a K means model
	 * @param k the number of clusters
	 */
	public KMeans(int k) {
		this(k, DEFAULT_ITERATIONS);
	}

	private int[] minmax(double[] arr) {
		int minIndex = 0;
		int maxIndex = 0;
		for(int i = 1; i < arr.length; i++)
		{
			if(arr[i] < arr[minIndex])
				minIndex = i;
			if(arr[i] > arr[maxIndex])
				maxIndex = i;
		}
		return new int[] {minIndex, maxIndex};
	}
	
	/**
	 * trains the model to find the clusters
	 * @param inputs the input data
	 */
	public void train(double[][] inputs) {
		Vector[] vs = new Vector[inputs.length];
		for(int i = 0; i < inputs.length; i++)
			vs[i] = new Vector(inputs[i]);
		train(vs);
	}
	
	/**
	 * trains the model to find the clusters
	 * @param inputs the input data
	 */
	public void train(Matrix inputs) {
		train(inputs.getVectorRows());
	}
	
	/**
	 * trains the model to find the clusters
	 * @param inputs the input data
	 */
	public void train(Vector[] inputs) {
		int dimensions = inputs[0].getSize();
		for(Vector input: inputs)
			if(input.getSize() != dimensions)
				throw new RuntimeException("Data Points must be the same size");
		double[][] ds = new double[k][dimensions];
		for(int i = 0; i < dimensions; i++) {
			double[] arr = new double[inputs.length];
			for(int j = 0; j < arr.length; j++)
				arr[j] = inputs[j].get(i);
			int[] minmax = minmax(arr);
			double min = arr[minmax[0]];
			double max = arr[minmax[1]];
			for(int j = 0; j < k; j++) {
				ds[j][i] = (max - min) * j / k + min;
			}
		}
		
		for(int i = 0; i < k; i++)
			clusters[i] = new Vector(ds[i]);
		int[] classifier = new int[inputs.length];
		for(int i_ = 0; i_ < iterations; i_++) {
			for(int i = 0; i < inputs.length; i++)
			{
				int minIndex = 0;
				for(int j = 1; j < k; j++)
					if(clusters[j].clone().subtract(inputs[i]).getMagnitude() < clusters[minIndex].clone().subtract(inputs[i]).getMagnitude())
						minIndex = j;
				classifier[i] = minIndex;
			}
			int[] counts = new int[k];
			for(int i = 0; i < k; i++) {
				clusters[i] = new Vector(dimensions);
				counts[i] = 0;
			}
			
			for(int i = 0; i < inputs.length; i++) {
				clusters[classifier[i]].add(inputs[i]);
				counts[classifier[i]]++;
			}
			
			for(int i = 0; i < k; i++) {
				clusters[i].divide(counts[i]);
			}
			
		}
			
	}
	
	/**
	 * predicts which cluster the input belongs to
	 * @param input the input array
	 * @return the mean array of the cluster
	 */
	public double[] predict(double[] input) {
		return predict(new Vector(input)).getData();
	}
	
	/**
	 * predicts which cluster the input belongs to
	 * @param input the input vector
	 * @return the mean vector of the cluster
	 */
	public Vector predict(Vector input) {
		int minIndex = 0;
		for(int j = 1; j < k; j++)
			if(clusters[j].clone().subtract(input).getMagnitude() < clusters[minIndex].clone().subtract(input).getMagnitude())
				minIndex = j;
		return clusters[minIndex];
	}

	/**
	 * @return the iterations
	 */
	public int getIterations() {
		return iterations;
	}

	/**
	 * @param iterations the iterations to set
	 */
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	/**
	 * @return the k
	 */
	public int getK() {
		return k;
	}
	
}

package io.bhagat.ai.unsupervised;

import java.io.Serializable;

import io.bhagat.math.Function;
import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.math.statistics.QuantitativeDataList;
import io.bhagat.util.ArrayUtil;

/**
 * A class containing methods for Principal Component Analysis
 * @author Bhagat
 */
public class PrincipalComponentAnalysis implements Serializable{

	private static final long serialVersionUID = -9142869462576885711L;

	/**
	 * the default threshold to decide whether a component has enough variance
	 */
	public double defaultThreshold = 0.05;
	/**
	 * the current number of dimensions
	 */
	public int curNumOfDimensions;
	
	public Matrix data;
	
	private Matrix X;
	private Matrix C;
	private double[] eigenvalues;
	private Vector[] eigenvectors;
	
	/**
	 * Creates an object that will perform principal component analysis
	 * @param X the data matrix
	 */
	public PrincipalComponentAnalysis(Matrix X) {
		this.X = centerData(X);
		C = covarianceMatrix(this.X);
		curNumOfDimensions = X.getRows();
		Matrix.EigenSolution eigenSolution = C.eigenproblem(10);
		eigenvalues = eigenSolution.eigenvalues;
		eigenvectors = eigenSolution.eigenvectors;
		double max = 0;
		for(int i = 0; i < eigenvalues.length; i++)
			if(max < eigenvalues[i])
				max = eigenvalues[i];
		defaultThreshold = 0.01 * max;
	}
	
	/**
	 * sets the data of the object
	 * @param X the data matrix
	 */
	public void setDataMatrix(Matrix X) {
		this.X = centerData(X);
		C = covarianceMatrix(this.X);
		Matrix.EigenSolution eigenSolution = C.eigenproblem(10);
		eigenvalues = eigenSolution.eigenvalues;
		eigenvectors = eigenSolution.eigenvectors;
		double max = 0;
		for(int i = 0; i < eigenvalues.length; i++)
			if(max < eigenvalues[i])
				max = eigenvalues[i];
		defaultThreshold = 0.01 * max;
	}
	
	/**
	 * @return gets the standardized data matrix X
	 */
	public Matrix getCenteredData() {
		return X;
	}

	/**
	 * creates a matrix from a byte array
	 * @param data the byte data
	 * @return the matrix
	 */
	public static Matrix createMatrix(byte[] data) {
		var buf = java.nio.ByteBuffer.wrap(data);
		int n = buf.getInt(), m = buf.getInt();
		double[][] matrix = new double[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				matrix[i][j] = buf.getDouble();
		return new Matrix(matrix);
	}
	
	/**
	 * Reduces the dimensions of the data matrix into D dimensions
	 * @param X the data to reduce the dimensions of
	 * @param D the number of ending dimensions
	 * @return the transformed data
	 */
	public Matrix dimensionReduction(Matrix X, int D) {
		curNumOfDimensions = D;
		if(D >= X.getColumns())
			return X;
		Matrix Z = new Matrix(X.getRows(), D);
		Z.setRows(ArrayUtil.map(X.getVectorRows(), new Function<Vector, Vector> () {

			@Override
			public Vector f(Vector x) {
				Vector result = new Vector(D);
				for(int i = 0; i < D; i++)
					result.add(Vector.generateUnitVector(i, D).multiply(x.dot(eigenvectors[i]) / eigenvectors[i].dot(eigenvectors[i])));
				return result;
			}
			
		}));
		return Z;
	}
	
	/**
	 * Reduces the dimensions of the data matrix so only the important dimensions remain
	 * @param X the data matrix
	 * @param threshhold the threshold for the eigenvalues defining what is important
	 * @return the reduced data matrix
	 */
	public Matrix dimensionReduction(Matrix X, double threshhold) {
		int D = 0;
		while(D < eigenvalues.length && eigenvalues[D] > threshhold)
			D++;
		return dimensionReduction(X, D);
	}

	/**
	 * Reduces the dimensions of the data matrix so only the important dimensions remain
	 * @param X the data matrix
	 * @return the reduced data matrix
	 */
	public Matrix dimensionReduction(Matrix X) {
		return dimensionReduction(X, defaultThreshold);
	}
	
	
	/**
	 * Reduces the dimensions of the data matrix into D dimensions
	 * @param D the number of ending dimensions
	 * @return the transformed data
	 */
	public Matrix dimensionReduction(int D) {
		return dimensionReduction(X, D);
	}
	
	/**
	 * Reduces the dimensions of the data matrix so only the important dimensions remain
	 * @param threshold the threshold for the eigenvalues defining what is important
	 * @return the reduced data matrix
	 */
	public Matrix dimensionReduction(double threshold) {
		return dimensionReduction(X, threshold);
	}
	
	/**
	 * Reduces the dimensions of the data matrix so only the important dimensions remain
	 * @return the reduced data matrix
	 */
	public Matrix dimensionReduction() {
		return dimensionReduction(X, defaultThreshold);
	}
	
	/**
	 * Reduces the dimensions of the data point into D dimensions
	 * @param x a vector data point
	 * @param D the number of ending dimensions
	 * @return the transformed data
	 */
	public Matrix dimensionReduction(Vector x, int D) {
		return dimensionReduction(x.toMatrixRow(), D);
	}
	
	/**
	 * Reduces the dimensions of the data point into D dimensions
	 * @param x a vector data point
	 * @param threshold the threshold for the eigenvalues defining what is important
	 * @return the transformed data
	 */
	public Matrix dimensionReduction(Vector x, double threshold) {
		return dimensionReduction(x.toMatrixRow(), threshold);
	}
	
	/**
	 * Reduces the dimensions of the data point into D dimensions
	 * @param x a vector data point
	 * @return the transformed data
	 */
	public Matrix dimensionReduction(Vector x) {
		return dimensionReduction(x.toMatrixRow(), defaultThreshold);
	}
	
	/**
	 * Centers the data from the matrix X at the column mean
	 * @param X the data
	 * @return the centered data
	 */
	public static Matrix centerData(Matrix X) {
		Vector[] cols = X.getVectorColumns();
		ArrayUtil.map(cols, new Function<Vector, Vector>() {

			@Override
			public Vector f(Vector x) {
				QuantitativeDataList list = new QuantitativeDataList(x.getData());
				Vector meanVector = new Vector(x.getSize());
				double mean = list.mean();
				for(int i = 0; i < x.getSize(); i++)
					meanVector.set(i, mean);
				return x.subtract(meanVector);
			}
			
		});		
		
		return X.clone().setColumns(cols);
	}
	
	/**
	 * creates the covariance matrix for the data
	 * @param X the data matrix
	 * @return the covariance matrix
	 */
	public static Matrix covarianceMatrix(Matrix X) {
		return Matrix.multiply(X.transpose(), X).divide(X.getRows());	
	}

}

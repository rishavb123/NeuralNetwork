package io.bhagat.math;

/**
 * @author Bhagat
 * An augmented matrix that has an extra right column for the augment
 */
public class AugmentedMatrix extends Matrix {

	private Matrix augment;
	
	/**
	 * Create an augmented matrix
	 * @param data the data including the augment as well as the matrix
	 */
	public AugmentedMatrix(double[][] data) {
		super(createInnerMatrixData(data));
		augment = new Matrix(createRightMatrixData(data));
	}
	
	/**
	 * Performs the augmented matrix operation of switching two rows
	 * @param i1 index of the first row
	 * @param i2 index of the second row
	 */
	public void switchTwoRows(int i1, int i2)
	{
		double[][] data = getData();
		double[] rowCopy = data[i1];
		data[i1] = data[i2];
		data[i2] = rowCopy;
		double temp = augment.get(0, i1);
		augment.set(i1, 0, augment.get(i2, 0));
		augment.set(i2, 0, temp);
	}

	/**
	 * splices the data to get the part for the matrix
	 * @param data the full data
	 * @return the spliced part of the data for the matrix
	 */
	private static double[][] createInnerMatrixData(double[][] data) {
		double[][] dataCopy = new double[data.length][data[0].length - 1];
		for(int i = 0; i < dataCopy.length; i++)
			for(int j = 0; j < dataCopy.length; j++)
				dataCopy[i][j] = data[i][j];
		return dataCopy;
	}
	
	/**
	 * splices the data to get only the part for the augment
	 * @param data the full data
	 * @return the spliced part of the data for the augment
	 */
	private static double[][] createRightMatrixData(double[][] data) {
		double[][] dataCopy = new double[data.length][1];
		for(int i = 0; i < dataCopy.length; i++)
			dataCopy[i][0] = data[i][data[i].length - 1];
		return dataCopy;
	}

	/**
	 * @return String representation of the augmented matrix
	 */
	@Override
	public String toString()
	{
		String s = "";
		for(int i = 0; i < getRows(); i++)
		{
			s += "|\t";
			for(int j = 0; j < getColumns(); j++)
				s += get(i, j) + ",\t";
			s = s.substring(0, s.length() - 2) + "\t|\t" + augment.get(i, 0) + "\t|\n";
		}
		
		return s;
	}
	
}

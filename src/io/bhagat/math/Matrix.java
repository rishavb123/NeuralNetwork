package io.bhagat.math;

/**
 * @author Bhagat
 *
 */
public class Matrix {

	/**
	 * a 2D double array storing to internal data of the Matrix
	 */
	private double[][] data;
	/**
	 * the number of rows in the matrix;
	 */
	private int rows;
	/**
	 * the number of columns in the matrix;
	 */
	private int columns;
	
	/**
	 * Create a matrix with a specified number of rows and columns
	 * @param rows the rows
	 * @param columns the columns
	 */
	public Matrix(int rows, int columns) {
		data = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}
	
	/**
	 * Create a matrix with specified data
	 * @param data the data in the form of a two dimensional double array
	 */
	public Matrix(double[][] data)
	{
		this.data = data;
		rows = data.length;
		columns = (rows == 0)? 0 : data[0].length;
	}
	
	/**
	 * @return a list of vectors representing the rows of the matrix
	 */
	public Vector[] getVectorRows()
	{
		Vector[] vs = new Vector[rows];
		for(int i = 0; i < rows; i++)
		{
			vs[i] = new Vector(data[i]);
		}
		return vs;
	}
	
	/**
	 * @return a list of vectors representing the columns of the matrix
	 */
	public Vector[] getVectorColumns()
	{
		if(data.length == 0)
			return new Vector[0];
		Vector[] vs = new Vector[columns];
		for(int j = 0; j < rows; j++)
		{
			double[] column = new double[rows];
			
			for(int i = 0; i < columns; i++)
				column[i] = data[i][j];
			
			vs[j] = new Vector(column);
			
		}
		return vs;
	}
	
	/**
	 * Get a value at a specified row and column
	 * @param i the row
	 * @param j the column
	 * @return the value
	 * @throws IndexNotInMatrixException when the indicies accessed are not in the matrix
	 */
	public double get(int i, int j) throws IndexNotInMatrixException
	{
		try {
			return data[i][j];
		} catch(IndexOutOfBoundsException e) {
			throw new IndexNotInMatrixException(i, j);
		}
	}
	
	/**
	 * Set a value at a specified row and column
	 * @param i the row
	 * @param j the column
	 * @param value the value
	 * @throws IndexNotInMatrixException if the index is invalid
	 */
	public void set(int i, int j, double value) throws IndexNotInMatrixException
	{
		try {
			data[i][j] = value;
		} catch(IndexOutOfBoundsException e)
		{
			throw new IndexNotInMatrixException(i, j);
		}
	}
	
	/**
	 * Clones this matrix with a new memory allocation
	 * @return the cloned matrix
	 */
	public Matrix clone()
	{
		return new Matrix(data);
	}
	
	/**
	 * Removes a row in the Matrix
	 * @param index the index to remove the row at
	 * @return the current matrix with the removed row
	 * @throws IndexNotInMatrixException if the removing index is out of bounds
	 */
	public Matrix removeRow(int index) throws IndexNotInMatrixException
	{
		if(index < 0 || index >= rows)
			throw new IndexNotInMatrixException();
		rows--;
		double[][] newData = new double[rows][columns];
		boolean foundRow = false;
		
		for(int i = 0; i < data.length; i++)
		{
			if(i == index)
				foundRow = true;
			else
				newData[i - (foundRow? 1 : 0)] = data[i];
		}
		
		data = newData;
		
		return this;
	}
	
	/**
	 * Removes a columns in the Matrix
	 * @param index the index to remove the row at
	 * @return the current matrix with the removed columns
	 * @throws IndexNotInMatrixException if the removing index is out of bounds
	 */
	public Matrix removeColumn(int index) throws IndexNotInMatrixException
	{
		if(index < 0 || index >= columns)
			throw new IndexNotInMatrixException();
		columns--;
		double[][] newData = new double[rows][columns];
		
		for(int i = 0; i < data.length; i++)
		{
			boolean foundCol = false;
			for(int j = 0; j < data[i].length; j++)
			{
				if(j == index)
					foundCol = true;
				else
					newData[i][j - (foundCol? 1 : 0)] = data[i][j];
			}
		}
		
		data = newData;
		
		return this;
	}
	
	public double determinant() throws InvalidShapeException
	{
		return determinant(this);
	}
	
	/**
	 * @return the data
	 */
	public double[][] getData() {
		return data;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return a string representation of the Matrix
	 */
	@Override
	public String toString()
	{
		if(data.length == 0 || data[0].length == 0)
			return "||";
		String s = "";
		for(int i = 0; i < data.length; i++)
		{
			s += "|\t";
			for(int j = 0; j < data[i].length; j++)
			{
				s += data[i][j] + ",\t";
			}
			
			s = s.substring(0, s.length() - 2) + "\t|\n";
		}
		return s;
	}

	/**
	 * Performs Matrix multiplication
	 * @param a first matrix
	 * @param b second matrix
	 * @return the resultant matrix
	 * @throws InvalidShapeException when a and b are invalid size for multiplication
	 */
	public static Matrix multiply(Matrix a, Matrix b) throws InvalidShapeException
	{
		if(a.getRows() != b.getColumns())
			throw a.new InvalidShapeException("The rows of matrix a does not match the columns of matrix b");
		Matrix m = new Matrix(a.getRows(), b.getColumns());
		Vector[] aRows = a.getVectorRows();
		Vector[] bCols = b.getVectorColumns();
		
		for(int i = 0; i < m.getRows(); i++)
			for(int j = 0; j < m.getColumns(); j++)
				m.set(i, j, aRows[i].dot(bCols[j]));
		
		return m;
	}
	
	
	/**
	 * @return the determinant
	 * @throws InvalidShapeException if the matrix is not a square matrix
	 */
	public static double determinant(Matrix m) throws InvalidShapeException
	{
		if(m.getRows() != m.getColumns())
			throw m.new InvalidShapeException("A matrix must be a square matrix to compute the determinant");
		
		if(m.getRows() == 1)
			try {
				return m.get(0, 0);
			} catch (IndexNotInMatrixException e) {
				e.printStackTrace();
			}
		
		double sum = 0;
		
		for(int i = 0; i < m.getRows(); i++)
		{
			try {
				sum += Math.pow(-1, i) * m.get(0, i) * determinant(m.clone().removeRow(0).removeColumn(i));
			} catch (IndexNotInMatrixException e) {
				e.printStackTrace();
			}
		}
		
		return sum;
		
	}
	
	/**
	 * @author Bhagat
	 * An exception for if a matrix is not the proper size for a certain operation
	 */
	public class InvalidShapeException extends RuntimeException {
		
		private static final long serialVersionUID = -2816000331326375159L;

		private InvalidShapeException() {
			super("The shape of the matrix(s) used do not work with the operation");
		}
		
		private InvalidShapeException(String msg) {
			super(msg);
		}
		
	}
	
	/**
	 * @author Bhagat
	 * An exception for handling if a user attempts to access an index that does not exist in the matrix
	 */
	public class IndexNotInMatrixException extends RuntimeException {

		private static final long serialVersionUID = 3791970450162818362L;
		
		private IndexNotInMatrixException()
		{
			super("Index not in matrix");
		}
		
		private IndexNotInMatrixException(int i, int j)
		{
			super("Index (" + i + ", " + j + ") not in the matrix");
		}
		
	}
}

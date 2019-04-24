package io.bhagat.math.linearalgebra;

import java.io.Serializable;
import java.util.ArrayList;

import io.bhagat.math.Function;
import io.bhagat.util.ArrayUtil;


//TODO implement the new linear algebra concepts like eigenvalues and vectors and spaces for E
/**
 * A class representing a Matrix
 * @author Bhagat
 */
public class Matrix implements Serializable, Comparable<Matrix>{

	private static final long serialVersionUID = -2338642716375039134L;
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
	
	public static final double EPSILON = 0.00000001;
	
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
		for(int j = 0; j < columns; j++)
		{
			double[] column = new double[rows];
			
			for(int i = 0; i < rows; i++)
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
	 * @throws IndexNotInMatrixException when the indices accessed are not in the matrix
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
	 * Get a value using a matrixIndex and store that into the matrix index
	 * @param matrixIndex the matrix index object
	 * @throws IndexNotInMatrixException when the indices are not in the matrix
	 */
	public void get(MatrixIndex matrixIndex) throws IndexNotInMatrixException {
		matrixIndex.setValue(get(matrixIndex.getI(), matrixIndex.getJ()));
		matrixIndex.setParent(this);
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
	 * sets the column of the matrix to the values from the input matrix
	 * @param m the matrix to put into the column
	 * @param j the column index
	 * @return a reference to this matrix
	 * @throws IndexNotInMatrixException if the sizes are not compatible
	 */
	public Matrix setColumn(Matrix m, int j) throws IndexNotInMatrixException
	{
		for(int i = 0; i < rows; i++)
			set(i, j, m.get(i, 0));
		return this;
	}
		
	/**
	 * sets the column of the matrix to the values from the vector
	 * @param v the vector to put into the column
	 * @param j the column index
	 * @return a reference to this matrix
	 * @throws IndexNotInMatrixException if the sizes are not compatible
	 */
	public Matrix setColumn(Vector v, int j) throws IndexNotInMatrixException
	{
		return setColumn(v.toMatrixColumn(), j);
	}
	
	/**
	 * sets all the columns from an array of vectors
	 * @param vs an array of the vectors to set into the Matrix
	 * @return a reference to this matrix
	 */
	public Matrix setColumns(Vector[] vs)
	{
		for(int i = 0; i < columns; i++)
			setColumn(vs[i], i);
		return this;
	}
	
	/**
	 * sets all the columns from an array of matrices
	 * @param ms an array of the matrices to set into the Matrix
	 * @return a reference to this matrix
	 */
	public Matrix setColumns(Matrix[] ms)
	{
		for(int i = 0; i < columns; i++)
			setColumn(ms[i], i);
		return this;
	}

	
	/**
	 * sets the row of the matrix to the values from the input matrix
	 * @param m the matrix to put into the row
	 * @param i the row index
	 * @return a reference to this matrix
	 * @throws IndexNotInMatrixException if the sizes are not compatible
	 */
	public Matrix setRow(Matrix m, int i) throws IndexNotInMatrixException
	{
		for(int j = 0; j < columns; j++)
			set(i, j, m.get(0, j));
		return this;
	}
	
	/**
	 * sets the row of the matrix to the values from the vector
	 * @param v the vector to put into the row
	 * @param i the row index
	 * @return a reference to this matrix
	 * @throws IndexNotInMatrixException if the sizes are not compatible
	 */
	public Matrix setRow(Vector v, int i) throws IndexNotInMatrixException
	{
		return setRow(v.toMatrixRow(), i);
	}
	
	/**
	 * sets all the rows from an array of vectors
	 * @param vs an array of the vectors to set into the Matrix
	 * @return a reference to this matrix
	 */
	public Matrix setRows(Vector[] vs)
	{
		for(int i = 0; i < rows; i++)
			setRow(vs[i], i);
		return this;
	}
	
	/**
	 * sets all the rows from an array of matrices
	 * @param ms an array of the matrices to set into the Matrix
	 * @return a reference to this matrix
	 */
	public Matrix setRows(Matrix[] ms)
	{
		for(int i = 0; i < rows; i++)
			setRow(ms[i], i);
		return this;
	}
	
	/**
	 * Set a value using a matrix index object
	 * @param matrixIndex the matrix index object that holds all the index information
	 * @throws IndexNotInMatrixException if the index is invalid
	 */
	public void set(MatrixIndex matrixIndex) throws IndexNotInMatrixException {
		set(matrixIndex.getI(), matrixIndex.getJ(), matrixIndex.getValue());
	}
	
	/**
	 * maps a function onto each element in the matrix
	 * @param function the function to map
	 * @return a reference of this matrix after the mapping
	 */
	public Matrix map(Function<Double, Double> function)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] = function.f(data[i][j]);
		return this;
	}
	
	/**
	 * maps a function onto each element in the matrix
	 * @param function the function to map that receives MatrixIndex object
	 * @return a reference of this matrix after the mapping
	 */
	public Matrix mapWithIndex(Function<MatrixIndex, Double> function)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] = function.f(new MatrixIndex(i, j, data[i][j], this));
		return this;
	}
	
	/**
	 * Clones this matrix with a new memory allocation
	 * @return the cloned matrix
	 */
	public Matrix clone()
	{
		double[][] dataCopy = new double[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				dataCopy[i][j] = data[i][j];
		return new Matrix(dataCopy);
	}
	
	/**
	 * Fills the Matrix with random values from -1 to 1
	 */
	public void randomize()
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] = Math.random()*2 - 1;
	}

	/**
	 * Fills the Matrix with random values from min to max
	 * @param min the minimum random number
	 * @param max the maximum random number
	 */
	public void randomize(double min, double max)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] = Math.random()*(max - min) + min;
	}
	
	/**
	 * Transposes the matrix
	 * @return the transposed matrix
	 */
	public Matrix transpose()
	{
		return transpose(this);
	}

	/**
	 * Finds the inverse of the matrix
	 * @return the inverse
	 */
	public Matrix inverse()
	{
		return inverse(this);
	}
	
	/**
	 * Finds the cofactor of the matrix
	 * @return the cofactor
	 */
	public Matrix cofactor()
	{
		return cofactor(this);
	}
	
	/**
	 * performs matrix multiplication
	 * @param m the matrix to multiply with
	 * @return the resultant matrix
	 */
	public Matrix multiply(Matrix m)
	{
		return multiply(this, m);
	}
	
	/**
	 * performs matrix multiplication
	 * @param v the vector to multiply with
	 * @return the resultant matrix
	 */
	public Matrix multiply(Vector v)
	{
		return multiply(this, v);
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
	
	/**
	 * Solves the eigen problem
	 * @param iterations the number of iterations for the QR algorithm
	 * @return an object containing both the eigenvalues and eigenvectors
	 */
	public EigenSolution eigenproblem(int iterations)
	{
		ArrayList<Double> eigenvalues = ArrayUtil.newArrayList(eigenvalues(iterations));
		ArrayList<Vector> eigenvectors = new ArrayList<>();
		for(int i = 0; i < eigenvalues.size(); i++)
		{
			double eigenvalue = eigenvalues.get(i);
			Vector eigenvector = eigenvector(eigenvalue);
			boolean add = true;
			for(double d: eigenvector.getData())
				if(Double.isNaN(d))
				{
					add = false;
					break;
				}
			if(add && !Double.isNaN(eigenvalue))
				eigenvectors.add(eigenvector);
			else
			{
				eigenvalues.remove(eigenvalue);
				i--;
			}
		}
		double[] eigenvaluesArr = new double[eigenvalues.size()];
		for(int i = 0; i < eigenvaluesArr.length; i++)
			eigenvaluesArr[i] = eigenvalues.get(i);
		Vector[] eigenvectorsArr = ArrayUtil.newArrayFromArrayList(eigenvectors, new Vector[eigenvectors.size()]);
		return new EigenSolution(eigenvaluesArr, eigenvectorsArr);
	}
	
	public double[] eigenvaluestest(int iterations)
	{
		//TODO Use power method and shifted inverted power method
		return eigenvalues(iterations);
	}
	
	/**
	 * computes the eigenvalues of the matrix
	 * <p> Note: this method may return extra eigenvalues that do not have a valid eigenvector. Use eigenproblem() for a better solution </p>
	 * @param iterations the number of iterations for the QR algorithm
	 * @return the eigenvalues
	 */
	public double[] eigenvalues(int iterations)
	{
		int scalar = -2;
		if(!isSquare())
			throw new InvalidShapeException("Cannot find eigenvalues of a matrix that is not a square");
		
		double[] lambdas = new double[rows];
		
		Matrix shift = identityMatrix(getRows()).multiply(scalar);
		Matrix A = this.subtract(shift);		
				
		for(int i = 0; i < iterations - 1; i++)
		{
			Matrix[] qr = Matrix.QR(A);
			A = Matrix.multiply(qr[1], qr[0]);
		}
		
		A = A.add(shift);
		
		for(int i = 0; i < rows; i++)
		{
			lambdas[i] = A.get(i, i);
		}
		
		for(int i = 1; i < lambdas.length; i++)
		{
			int j = i;
			while(j > 0 && lambdas[j] > lambdas[j-1])
			{
				double temp = lambdas[j];
				lambdas[j] = lambdas[j - 1];
				lambdas[j - 1] = temp;
				j--;
			}
		}
		
		return lambdas;
	}
	
	/**
	 * calculates the eigenvectors based on the eigenvalues provided
	 * @param eigenvalues the eigenvalues
	 * @return the eigenvectors
	 */
	public Vector[] eigenvectors(double[] eigenvalues)
	{
		ArrayList<Vector> eigenvectors = new ArrayList<>();
		for(double eigenvalue: eigenvalues)
		{
			Vector eigenvector = eigenvector(eigenvalue);
			boolean add = true;
			for(double d: eigenvector.getData())
				if(Double.isNaN(d))
				{
					add = false;
					break;
				}
			if(add && !Double.isNaN(eigenvalue))
				eigenvectors.add(eigenvector);
		}
		return ArrayUtil.newArrayFromArrayList(eigenvectors, new Vector[eigenvectors.size()]);
	}
	
	private Vector eigenvector(double eigenvalue)
	{
		Vector[] rows = clone().subtract(identityMatrix(getRows()).multiply(eigenvalue)).rowEchelonForm().getVectorRows();
		Vector eigenvector = new Vector(rows.length);
		
		for(int i = rows.length - 1; i >= 0; i--)
		{
			boolean ignore = true;
			Vector row = rows[i];
			for(int j = 0; j < row.getSize(); j++)
				if(!Double.isNaN(row.get(j)) && row.get(j) > EPSILON)
				{
					ignore = false;
					break;
				}
			if(ignore)
			{
				eigenvector.set(i, 1);
				continue;
			}
			eigenvector.set(i, -eigenvector.dot(row) / row.get(i));
		}
		eigenvector = eigenvector.normalize();
		for(int i = 0; i < eigenvector.getSize(); i++)
			if(Math.abs(eigenvector.get(i)) < EPSILON)
			{
				eigenvector.set(i, 0);
			}
		return eigenvector;
	}
	
	/**
	 * Find the singular values of a matrix
	 * @param iterations the number of iterations for the QR algorithm
	 * @return the singular values of the matrix
	 */
	public double[] singularValues(int iterations)
	{
		double[] values = Matrix.multiply(this, transpose()).eigenvalues(iterations);
		for(int i = 0; i < values.length; i++)
			values[i] = Math.sqrt(values[i]);
		return values;
	}
	
	/**
	 * Finds both the singular values and corresponding eigenvectors
	 * @param iterations the number of iterations for the QR algorithm
	 * @return an object containing the singular values and eigenvectors
	 */
	public SingularSolution singularSolution(int iterations)
	{
		EigenSolution eigenSolution = Matrix.multiply(transpose(), this).eigenproblem(iterations);
		for(int i = 0; i < eigenSolution.eigenvalues.length; i++)
			eigenSolution.eigenvalues[i] = Math.sqrt(eigenSolution.eigenvalues[i]);
		return new SingularSolution(eigenSolution.eigenvalues, eigenSolution.eigenvectors);
	}
	
	/**
	 * uses the static method to compute the determinant of the matrix
	 * @return the determinant
	 * @throws InvalidShapeException gets thrown if it is not a square matrix
	 */
	public double determinant() throws InvalidShapeException
	{
		return determinant(this);
	}
	
	/**
	 * compares two matrices by determinant
	 * @param m the matrix to compare to
	 * @return the integer representing which determinant is greater
	 */
	@Override
	public int compareTo(Matrix m) {
		return (determinant() > m.determinant())? 1: (determinant() == m.determinant())? 0 : -1;
	}
	
	/**
	 * checks if two matrices have the same data
	 * @param m the matrix to compare with
	 * @return whether or not they are the same
	 */
	public boolean equals(Matrix m) {
		if(getRows() != m.getRows() || getColumns() != m.getColumns())
			return false;
		for(int i = 0; i < getRows(); i++)
			for(int j = 0; j < getColumns(); j++)
				if(data[i][j] != m.get(i, j))
					return false;
		return true;
	}
	
	/**
	 * performs the hadamard product which is element wise multiplication
	 * @param m the matrix to multiply
	 * @return a reference to this matrix
	 */
	public Matrix hadamardProduct(Matrix m)
	{
		if(rows != m.getRows() || columns != m.getColumns())
			throw new InvalidShapeException("Matricies must has the same shape in order to perform the hadamard product");
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] *= m.get(i, j);
		return this;
	}
	
	/**
	 * performs the scalar multiplication on the matrix
	 * @param d the scalar to multiply with
	 * @return a reference to this matrix
	 */
	public Matrix multiply(double d)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] *= d;
		return this;
	}
	
	/**
	 * performs the scalar division on the matrix
	 * @param d the scalar to divide with
	 * @return a reference to this matrix
	 */
	public Matrix divide(double d)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] /= d;
		return this;
	}
	
	/**
	 * converts the matrix to row echelon form
	 * @return the matrix in row echelon form
	 */
	public Matrix rowEchelonForm()
	{
		Matrix ref = clone();
		Vector[] rows = ref.getVectorRows();
		for(int i = 0; i < columns - 1; i++)
			for(int j = i + 1; j < rows.length; j++)
				rows[j].subtract(rows[i].clone().divide(rows[i].get(i)).multiply(rows[j].get(i)));
			
		return ref.setRows(rows);
	}
	
	/**
	 * converts the matrix to reduced row echelon form
	 * @return the matrix in reduced row echelon form
	 */
	public Matrix reducedRowEchelonForm()
	{
		Matrix ref = rowEchelonForm();
		Vector[] rows = ref.getVectorRows();
		for(int i = 0; i < getRows(); i++)
			rows[i].divide(rows[i].get(i));
		return ref;
	}
	
	/**
	 * performs element wise addition
	 * @param m the matrix to add with
	 * @return a reference to this matrix
	 */
	public Matrix add(Matrix m)
	{
		if(rows != m.getRows() || columns != m.getColumns())
			throw new InvalidShapeException("Matricies must has the same shape in order to perform the addition");
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] += m.get(i, j);
		return this;
	}
	
	/**
	 * performs element wise subtraction
	 * @param m the matrix to subtract with
	 * @return a reference to this matrix
	 */
	public Matrix subtract(Matrix m)
	{
		if(rows != m.getRows() || columns != m.getColumns())
			throw new InvalidShapeException("Matricies must has the same shape in order to perform the addition");
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] -= m.get(i, j);
		return this;
	}
	
	/**
	 * performs element wise division
	 * @param m the matrix to divide with
	 * @return a reference to this matrix
	 */
	public Matrix divide(Matrix m)
	{
		if(rows != m.getRows() || columns != m.getColumns())
			throw new InvalidShapeException("Matricies must has the same shape in order to perform the division");
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				data[i][j] /= m.get(i, j);
		return this;
	}
	
	/**
	 * converts this matrix into a vector
	 * @return the vector
	 */
	public Vector toVector()
	{
		if(rows == 1)
			return new Vector(data[0]);
		if(columns == 1)
			return new Vector(transpose().getData()[0]);
		throw new InvalidShapeException("Matrix must have either one row or one column to be converted into a Vector");
	}
	
	/**
	 * @return true if it is a square matrix
	 */
	public boolean isSquare()
	{
		return rows == columns;
	}
	
	/**
	 * @return the data
	 */
	public double[][] getData() {
		return data;
	}
	
	/**
	 * @param data the data
	 */
	public void setData(double[][] data) {
		this.data = data;
		rows = data.length;
		columns = (rows > 0)? data[0].length : 0;
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
		if(a.getColumns() != b.getRows())
			throw a.new InvalidShapeException("The columns of matrix a does not match the rows of matrix b");
		Matrix m = new Matrix(a.getRows(), b.getColumns());
		Vector[] aRows = a.getVectorRows();
		Vector[] bCols = b.getVectorColumns();
		
		for(int i = 0; i < m.getRows(); i++)
			for(int j = 0; j < m.getColumns(); j++)
				m.set(i, j, aRows[i].dot(bCols[j]));
		
		return m;
	}
	
	/**
	 * Performs Matrix multiplication with a matrix and a vector
	 * @param A the matrix
	 * @param x the vector
	 * @return the resultant matrix
	 * @throws InvalidShapeException when A and x are invalid size for multiplication
	 */
	public static Matrix multiply(Matrix A, Vector x) throws InvalidShapeException
	{
		return multiply(A, x.toMatrix());
	}
	
	/**
	 * performs the inner product on two matrices
	 * @param a a matrix
	 * @param b another matrix
	 * @return the matrix result
	 */
	public static Matrix inner(Matrix a, Matrix b)
	{
		return a.transpose().multiply(b);
	}
	
	/**
	 * performs the outer product on two matrices
	 * @param a a matrix
	 * @param b another matrix
	 * @return the matrix result
	 */
	public static Matrix outer(Matrix a, Matrix b)
	{
		return a.multiply(b.transpose());
	}
	
	/**
	 * Computes the hadamard product, which is element wise multiplication
	 * @param a the first matrix
	 * @param b the second matrix
	 * @return the resultant matrix
	 * @throws InvalidShapeException if the matrices do not have the same shape
	 */
	public static Matrix hadamardProduct(Matrix a, Matrix b) throws InvalidShapeException {
		return a.clone().hadamardProduct(b);
	}
	
	/**
	 * transposes the rows and columns of a matrix to the columns and rows
	 * @param m the matrix to transpose
	 * @return the transposed matrix
	 */
	public static Matrix transpose(Matrix m)
	{
		Matrix t = new Matrix(m.getColumns(), m.getRows());
		for(int i = 0; i < m.getRows(); i++)
			for(int j = 0; j < m.getColumns(); j++)
				t.set(j, i, m.get(i, j));
		return t;
	}
	
	/**
	 * Performs singular value decomposition on a matrix
	 * @param A the matrix to find the SVD decomposition of
	 * @param iterations the number of iterations for the QR algorithm
	 * @return an array of matrices that contain {U, Sigma, V}
	 */
	public static Matrix[] singularValueDecomposition(Matrix A, int iterations)
	{
		Matrix U = new Matrix(A.getRows(), A.getRows());
		Matrix S = new Matrix(A.getRows(), A.getColumns());
		Matrix V = new Matrix(A.getColumns(), A.getColumns());
		
		Matrix ATA = Matrix.multiply(A.transpose(), A);
		
		double[] eigenvalues = ATA.eigenvalues(iterations);
		Vector[] eigenvectors = ATA.eigenvectors(eigenvalues);
		double[] singularValues = new double[eigenvalues.length];
		for(int i = 0; i < eigenvalues.length; i++)
		{
			singularValues[i] = Math.sqrt(eigenvalues[i]);
			try {
				S.set(i, i, singularValues[i]);	
			} catch(IndexNotInMatrixException e) {}
		}
		V.setColumns(eigenvectors);		
		U.setColumns(ArrayUtil.map(eigenvectors, new Function<Vector, Vector> () {

			@Override
			public Vector f(Vector x) {
				return Matrix.multiply(A, x).toVector().divide(singularValues[ArrayUtil.indexOf(eigenvectors, x)]);
			}
			
		}));
		return new Matrix[] { U, S, V };
		
	}
	
	/**
	 * performs singular value decomposition and returns the output in outer product form
	 * @param A the matrix to factor
	 * @param iterations the number of iterations for the QR algorithm
	 * @return an object holding the solutions for the factorization
	 */
	public static OuterProductSVD singularValueDecompositionOuter(Matrix A, int iterations)
	{
		SingularSolution solution = A.singularSolution(iterations);
		Vector[] us = new Vector[solution.eigenvectors.length];
		for(int i = 0; i < us.length; i++)
			us[i] = Matrix.multiply(A, solution.eigenvectors[i]).toVector().divide(solution.singularValues[i]);
		return new OuterProductSVD(solution.singularValues, us, solution.eigenvectors);
	}
	
	/**
	 * Computes the QR factorization of a matrix
	 * @param A the matrix
	 * @return an array of matrices where the first matrix is Q and the second one is R
	 */
	public static Matrix[] QR(Matrix A)
	{
		Vector[] columns = A.getVectorColumns();
		Vector[] q = ArrayUtil.map(Vector.orthogonalize(columns), new Function<Vector, Vector>() {

			@Override
			public Vector f(Vector x) {
				return x.normalize();
			}
			
		});
		Matrix Q = new Matrix(A.getRows(), A.getColumns());
		Q.setColumns(q);
		Matrix R = Matrix.multiply(Matrix.transpose(Q), A);
		if(Double.isNaN(Q.get(0, 0)) || Double.isNaN(R.get(0, 0)))
		{
			Q = identityMatrix(A.getRows());
			R = A.clone();
		}
		Matrix[] arr = { Q, R };
		return arr;
	}
	
	/**
	 * @param m the Matrix to take the determinant of
	 * @return the determinant
	 * @throws InvalidShapeException if the matrix is not a square matrix
	 */
	public static double determinant(Matrix m) throws InvalidShapeException
	{
		if(!m.isSquare())
			throw m.new InvalidShapeException("A matrix must be a square matrix to compute the determinant");
		
		if(m.getRows() == 1)
			return m.get(0, 0);
		
		double sum = 0;
		
		for(int i = 0; i < m.getRows(); i++)
		{
			sum += Math.pow(-1, i) * m.get(0, i) * determinant(m.clone().removeRow(0).removeColumn(i));
		}
		
		return sum;
		
	}
	
	/**
	 * finds the cofactor of a matrix
	 * @param A the matrix
	 * @return the cofactor matrix
	 */
	public static Matrix cofactor(Matrix A)
	{
		if(!A.isSquare())
			throw A.new InvalidShapeException("The matrix is not a square matrix");
		Matrix C = new Matrix(A.getRows(), A.getColumns());
		for(int i = 0; i < A.getRows(); i++)
			for(int j = 0; j < A.getColumns(); j++)
				C.set(i, j, (((i + j) % 2 == 0)? 1 : -1) * A.clone().removeRow(i).removeColumn(j).determinant());
				
		return C;
	}
	
	/**
	 * computes the inverse matrix of the input matrix
	 * @param A the input matrix
	 * @return the inverse of the input matrix
	 */
	public static Matrix inverse(Matrix A)
	{
		return cofactor(A).transpose().divide(A.determinant());
	}
	
	
	/**
	 * generates an identity matrix with a specified size
	 * @param size the size is the number of rows and columns
	 * @return the generated matrix
	 */
	public static Matrix identityMatrix(int size)
	{
		Matrix m = new Matrix(size, size);
		for(int i = 0; i < size; i++)
			m.set(i, i, 1);
		return m;
	}
	
	/**
	 * A class to hold all the parts of the outer product form of the SVD
	 * @author Bhagat
	 */
	public static class OuterProductSVD implements Serializable{
		private static final long serialVersionUID = 820930237908595734L;
		public double[] singularValues;
		public Vector[] us;
		public Vector[] vs;
		
		/**
		 * creates a new SVD solution
		 * @param singularValues the singular values
		 * @param us the U vectors
		 * @param vs the V vectors
		 */
		public OuterProductSVD(double[] singularValues, Vector[] us, Vector[] vs)
		{
			this.singularValues = singularValues;
			this.us = us;
			this.vs = vs;
		}
		
	}
	
	/**
	 * A class that holds both eigenvalues and eigenvectors
	 * @author Bhagat
	 */
	public static class EigenSolution {
		public double[] eigenvalues;
		public Vector[] eigenvectors;
		
		/**
		 * Creates a new EigenSolution
		 * @param eigenvalues the eigenvalues
		 * @param eigenvectors the eigenvectors
		 */
		public EigenSolution(double[] eigenvalues, Vector[] eigenvectors)
		{
			this.eigenvalues = eigenvalues;
			this.eigenvectors = eigenvectors;
		}
		
	}
	
	/**
	 * A class that holds both singular values and eigenvectors of Matrix.multiply(transpose(), this)
	 * @author Bhagat
	 */
	public static class SingularSolution {
		public double[] singularValues;
		public Vector[] eigenvectors;
		
		/**
		 * Creates a new EigenSolution
		 * @param eigenvalues the eigenvalues
		 * @param eigenvectors the eigenvectors
		 */
		public SingularSolution(double[] singularValues, Vector[] eigenvectors)
		{
			this.singularValues = singularValues;
			this.eigenvectors = eigenvectors;
		}
		
	}
	
	/**
	 * An object that stores all the information about one index of the matrix
	 * @author Bhagat
	 */
	public static class MatrixIndex {
		
		/**
		 * row index
		 */
		private int i;
		/**
		 * column index
		 */
		private int j;
		/**
		 * value
		 */
		private double value;
		/**
		 * parent matrix
		 */
		private Matrix parent;
		
		/**
		 * constructs a new MatrixIndex
		 * @param i the row index
		 * @param j the column index
		 */
		public MatrixIndex(int i, int j)
		{
			this.i = i;
			this.j = j;
		}
		
		/**
		 * constructs a new MatrixIndex
		 * @param i the row index
		 * @param j the column index
		 * @param value the value at this index
		 */
		public MatrixIndex(int i, int j, double value)
		{
			this.i = i;
			this.j = j;
			this.value = value;
		}
		
		/**
		 * constructs a new MatrixIndex
		 * @param i the row index
		 * @param j the column index
		 * @param value the value at this index
		 * @param parent the parent matrix
		 */
		public MatrixIndex(int i, int j, double value, Matrix parent)
		{
			this.i = i;
			this.j = j;
			this.value = value;
			this.parent = parent;
		}

		/**
		 * @return the i
		 */
		public int getI() {
			return i;
		}

		/**
		 * @param i the i to set
		 */
		public void setI(int i) {
			this.i = i;
		}

		/**
		 * @return the j
		 */
		public int getJ() {
			return j;
		}

		/**
		 * @param j the j to set
		 */
		public void setJ(int j) {
			this.j = j;
		}

		/**
		 * @return the value
		 */
		public double getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(double value) {
			this.value = value;
		}

		/**
		 * @return the parent
		 */
		public Matrix getParent() {
			return parent;
		}

		/**
		 * @param parent the parent to set
		 */
		public void setParent(Matrix parent) {
			this.parent = parent;
		}
	}
	
	/**
	 * An exception for if a matrix is not the proper size for a certain operation
	 * @author Bhagat
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
	 * An exception for handling if a user attempts to access an index that does not exist in the matrix
	 * @author Bhagat
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

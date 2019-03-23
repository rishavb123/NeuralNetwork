/**
 * 
 */
package io.bhagat.math.linearalgebra;

import java.io.Serializable;

import io.bhagat.math.Function;

/**
 * This class is a class representing a Vector of any number of dimensions
 * @author Bhagat
 */
public class Vector implements Serializable, Comparable<Vector>{
	
	private static final long serialVersionUID = 2667761682263393533L;

	/**
	 * the array storing the data of the vector
	 */
	private double[] data;
	
	/**
	 * the length of the stored data
	 */
	private int size;
	
	/**
	 * constructs a new Vector using components
	 * @param args The components to set into the Vector
	 */
	public Vector(double...args)
	{
		data = args;
		size = args.length;
	}
	
	/**
	 * constructs a new Vector using components
	 * @param args The components to set into the Vector
	 */
	public Vector(Double...args)
	{
		data = new double[args.length];
		for(int i = 0; i < args.length; i++)
			data[i] = args[i];
		size = args.length;
	}
	
	/**
	 * constructs a new Vector using int components
	 * @param args The components to set into the Vector
	 */
	public Vector(int...args)
	{
		size = args.length;
		data = new double[size];
		for(int i = 0; i < size; i++)
			data[i] = args[i];
	}
	
	/**
	 * constructs a new Vector with a set number of dimensions
	 * @param length The number of dimensions in the Vector
	 */
	public Vector(int length)
	{
		data = new double[length];
		size = length;
	}
	
	/**
	 * performs the dot product with another vector
	 * @return returns scalar result
	 * @param vector the vector to dot with
	 * @throws OutOfDimensionsException thrown when a value in the specified vector does not have a value
	 * @throws InvalidLengthException thrown when the two vectors have unequal lengths
	 */
	public double dot(Vector vector) throws OutOfDimensionsException, InvalidLengthException
	{
		if(size != vector.getSize())
		{
			throw new InvalidLengthException(this, vector);
		}
		double sum = 0;
		for(int i = 0; i < size; i++)
			sum += data[i] * vector.get(i);
		
		return sum;
	}
	
	/**
	 * Performs element wise addition
	 * @param v the vector to add to this one
	 * @return a reference to this vector
	 */
	public Vector add(Vector v)
	{
		if(size != v.getSize())
			throw new InvalidLengthException(this, v);
		for(int i = 0; i < size; i++)
			data[i] += v.get(i);
		return this;
	}
	
	/**
	 * Performs element wise subtraction
	 * @param v the vector to subtract from this one
	 * @return a reference to this vector
	 */
	public Vector subtract(Vector v)
	{
		if(size != v.getSize())
			throw new InvalidLengthException(this, v);
		for(int i = 0; i < size; i++)
			data[i] -= v.get(i);
		return this;
	}
	
	/**
	 * adds a scalar to each value in the Vector
	 * @param x the scalar to add
	 * @return this vector
	 */
	public Vector add(double x) {
		for(int i = 0; i < size; i++)
			data[i] += x;
		return this;
	}
	
	/**
	 * subtracts a scalar to each value in the Vector
	 * @param x the scalar to subtract
	 * @return this vector
	 */
	public Vector subtract(double x) {
		for(int i = 0; i < size; i++)
			data[i] -= x;
		return this;
	}
	
	/**
	 * Performs scalar multiplication (element wise) on the Vector
	 * @param scalar the scalar to multiply with
	 * @return returns this vector after the multiplication
	 */
	public Vector multiply(double scalar)
	{
		for(int i = 0; i < size; i++)
			data[i] *= scalar;
		
		return this;
	}
	
	/**
	 * element wise multiplication
	 * @param v vector to multiply with
	 * @return returns this vector after the multiplication
	 */
	public Vector multiply(Vector v)
	{
		if(size != v.getSize())
			throw new InvalidLengthException(this, v);
		for(int i = 0; i < size; i++)
			data[i] *= v.get(i);
		return this;
	}
	
	/**
	 * Performs scalar division (element wise) on the Vector
	 * @param scalar the scalar to divide with
	 * @return returns this vector after the division
	 */
	public Vector divide(double scalar)
	{
		for(int i = 0; i < size; i++)
			data[i] /= scalar;

		return this;
	}
	
	/**
	 * Element-wise Vector division
	 * @param vector the vector to divide by
	 * @return this vector after the division
	 */
	public Vector divide(Vector vector)
	{
		
		for(int i = 0; i < size; i++)
			set(i, get(i) / vector.get(i));
		
		return this;
	}
	
	
	/**
	 * Fills the Vector with random values from min to max
	 * @param min the minimum random number
	 * @param max the maximum random number
	 */
	public void randomize(double min, double max)
	{
		for(int i = 0; i < data.length; i++)
			data[i] = Math.random()*(max - min) + min;
	}
	
	/**
	 * Fills the Vector with random values from -1 to 1
	 */
	public void randomize()
	{
		for(int i = 0; i < data.length; i++)
			data[i] = Math.random()*2 - 1;
	}
	
	/**
	 * @return the magnitude of the vector
	 */
	public double getMagnitude() 
	{
		double sum = 0;
		for(double x: data)
			sum += x*x;
		return Math.sqrt(sum);
	}
	
	/**
	 * compares two vectors by determinant
	 * @param v the vector to compare to
	 * @return the integer representing which vector's magnitude is greater
	 */
	@Override
	public int compareTo(Vector v) {
		return (getMagnitude() > v.getMagnitude())? 1: (getMagnitude() == v.getMagnitude())? 0 : -1;
	}
	
	/**
	 * checks if two matrices have the same data
	 * @param v the vector to compare with
	 * @return whether or not they are the same
	 */
	public boolean equals(Vector v) {
		if(size != v.getSize())
			return false;
		for(int i = 0; i < getSize(); i++)
			if(data[i] != v.get(i))
				return false;
		return true;
	}
	
	/**
	 * @return the sum of each component
	 */
	public double getSum()
	{
		double sum = 0;
		for(double d: data)
		{
			sum += d;
		}
		return sum;
	}
	
	/**
	 * Gets the data at a particular index
	 * @param index the index
	 * @return the data at the index
	 * @throws OutOfDimensionsException when the index is invalid
	 */
	public double get(int index) throws OutOfDimensionsException
	{
		try {
			return data[index];
		} 
		catch(IndexOutOfBoundsException e)
		{
			throw new OutOfDimensionsException(this, index);
		}
	}
	
	/**
	 * Gets data from an index using the VectorIndex object and stores the value into this object
	 * @param vectorIndex the vector index object
	 * @throws OutOfDimensionsException when the index is invalid
	 */
	public void get(VectorIndex vectorIndex) throws OutOfDimensionsException
	{
		vectorIndex.setParent(this);
		vectorIndex.setValue(get(vectorIndex.getIndex()));
	}
	
	/**
	 * sets the value at a certain index
	 * @param index the index
	 * @param value the value
	 * @throws OutOfDimensionsException if the index is out of the dimensions of the vector
	 */
	public void set(int index, double value) throws OutOfDimensionsException
	{
		try {
			data[index] = value;
		} 
		catch(IndexOutOfBoundsException e)
		{
			throw new OutOfDimensionsException(this, index);
		}
	}
	
	/**
	 * Set a value in the vector using a VectorIndex
	 * @param vectorIndex the VectorIndex object holding the index and value
	 * @throws OutOfDimensionsException if the index is out of the dimensions of the vector
	 */
	public void set(VectorIndex vectorIndex) throws OutOfDimensionsException
	{
		set(vectorIndex.getIndex(), vectorIndex.getValue());
	}
	
	/**
	 * maps a function onto each element in the vector
	 * @param function the function to map
	 * @return a reference of this vector after the mapping
	 */
	public Vector map(Function<Double, Double> function)
	{
		for(int i = 0; i < size; i++)
			data[i] = function.f(data[i]);
		return this;
	}
	
	/**
	 * maps a function onto each element in the vector
	 * @param function the function to map that receives a VectorIndex
	 * @return a reference of this vector after the mapping
	 */
	public Vector mapWithIndex(Function<VectorIndex, Double> function)
	{
		for(int i = 0; i < size; i++)
			data[i] = function.f(new VectorIndex(i, data[i], this));
		return this;
	}
	
	/**
	 * converts the vector into a matrix column
	 * @return the matrix with one column
	 */
	public Matrix toMatrixColumn()
	{
		return new Matrix(new double[][] { data }).transpose();
	}
	
	/**
	 * converts the vector into a matrix row
	 * @return the matrix with one row
	 */
	public Matrix toMatrixRow()
	{
		return new Matrix(new double[][] { data });
	}
	
	/**
	 * converts the vector into a matrix column
	 * @return the matrix with one column
	 */
	public Matrix toMatrix()
	{
		return toMatrixColumn();
	}
	
	/**
	 * @return a copy with a new memory allocation
	 */
	public Vector clone()
	{
		double[] dataCopy = new double[size];
		for(int i = 0; i < size; i++)
			dataCopy[i] = data[i];
		return new Vector(dataCopy);
	}
	
	/**
	 * Checks if this vector and the one sent in are orthogonal
	 * @param v the second vector
	 * @return a boolean that is true if they are orthogonal
	 */
	public boolean orthogonal(Vector v)
	{
		return orthogonal(this, v);
	}
	
	/**
	 * @return the unit vector with the same direction as this vector
	 */
	public Vector unitVector()
	{
		return clone().divide(getMagnitude());
	}
	
	/**
	 * @return the data
	 */
	public double[] getData() {
		return data;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return a string representing this Vector object in the form &lt;x1, x2, . . . xn&gt;
	 */
	@Override
	public String toString()
	{
		if(size == 0)
		{
			return "<>";
		}
		String s = "<";
		for(double x: data)
		{
			s += Double.toString(x) + ", ";
		}
		return s.substring(0, s.length() - 2) + ">";
	}
	
	/**
	 * static clone function that calls the clone function of a vector
	 * @param v the vector to clone
	 * @return the cloned vector with a new memory allocation
	 */
	public static Vector clone(Vector v)
	{
		return v.clone();
	}
	
	/**
	 * static dot product function that calls the dot function of a vector
	 * @param a first vector
	 * @param b vector to dot with
	 * @return the dot product of the two
	 * @throws OutOfDimensionsException when the vectors are not the same length but is not caught by InvalidLengthException
	 * @throws InvalidLengthException when the vectors are not the same length
	 */
	public static double dot(Vector a, Vector b) throws OutOfDimensionsException, InvalidLengthException
	{
		return a.clone().dot(b);
	}
	
	/**
	 * static multiply scalar function
	 * @param a the vector to multiply
	 * @param scalar the scalar to multiply
	 * @return the resultant vector
	 */
	public static Vector multiply(Vector a, double scalar)
	{
		return a.clone().multiply(scalar);
	}
	
	/**
	 * static multiply scalar function
	 * @param scalar the scalar to multiply
	 * @param a the vector to multiply
	 * @return the resultant vector
	 */
	public static Vector multiply(double scalar, Vector a)
	{
		return a.clone().multiply(scalar);
	}
	
	/**
	 * static divide scalar function
	 * @param a the vector to divide
	 * @param scalar the scalar to divide
	 * @return the resultant vector
	 */
	public static Vector divide(Vector a, double scalar)
	{
		return a.clone().divide(scalar);
	}
	
	/**
	 * static divide scalar function
	 * @param scalar the scalar to divide
	 * @param a the vector to divide
	 * @return the resultant vector
	 */
	public static Vector divide(double scalar, Vector a)
	{
		return a.clone().divide(scalar);
	}
	
	/**
	 * computes the cross product of the input vectors
	 * @param vectors the vectors to cross
	 * @return the resultant vector
	 */
	public static Vector cross(Vector... vectors)
	{
		for(Vector v: vectors)
			if(vectors.length != v.getSize() -1)
				throw new InvalidLengthException();
		
		double[][] data = new double[vectors.length][vectors.length + 1];
				
		for(int i = 0; i < data.length; i++)
				data[i] = vectors[i].clone().getData();
		
		return new CrossProductMatrix(data).determinant();		
		
	}
	
	/**
	 * performs the inner product on two vectors
	 * @param a a vector
	 * @param b another vector
	 * @return the inner product
	 */
	public static double inner(Vector a, Vector b)
	{
		return a.dot(b);
	}
	
	/**
	 * performs the outer product on two vectors
	 * @param a a vector
	 * @param b another vector
	 * @return the matrix result
	 */
	public static Matrix outer(Vector a, Vector b)
	{
		return Matrix.outer(a.toMatrix(), b.toMatrix());
	}
	
	public static Vector generateUnitVector(int index, int size)
	{
		double[] data = new double[size];
		try {
			data[index] = 1;
		} catch(IndexOutOfBoundsException e)
		{
			throw new OutOfDimensionsException(new Vector(size), index);
		}
		return new Vector(data);
	}
	
	/**
	 * prints any number of vectors
	 * @param vectors the vectors to print
	 */
	public static void print(Vector... vectors)
	{
		for(Vector v : vectors)
			System.out.println(v);
		System.out.println();
	}
	
	/**
	 * Checks if two vectors are orthogonal
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return boolean that if true if they are orthogonal
	 */
	public static boolean orthogonal(Vector v1, Vector v2)
	{
		return v1.dot(v2) == 0;
	}
	
	private static class CrossProductMatrix {
		
		private Matrix internalMatrix;
		private Vector[] topRow;
		
		private CrossProductMatrix(double[][] data)
		{
			topRow = new Vector[data[0].length];
			for(int i = 0; i < data[0].length; i++)
				topRow[i] = Vector.generateUnitVector(i, data[0].length);

			internalMatrix = new Matrix(data);
		}
		
		private Vector determinant()
		{
			Vector sum = new Vector(topRow[0].getSize());
			
			for(int i = 0; i < internalMatrix.getColumns(); i++)
			{
				sum.add(topRow[i].clone().multiply(Math.pow(-1, i) * Matrix.determinant(internalMatrix.clone().removeColumn(i))));
			}
			
			return sum;
		}
		
	}

	/**
	 * @author Bhagat
	 * class representing the index and value at a vector
	 */
	public static class VectorIndex {
		
		/**
		 * index in the vector
		 */
		private int index;
		/**
		 * value
		 */
		private double value;
		/**
		 * the parent vector
		 */
		private Vector parent;
		
		/**
		 * Creates a new Vector Index
		 * @param index the index
		 */
		public VectorIndex(int index)
		{
			this.index = index;
		}
		
		/**
		 * Creates a new Vector Index
		 * @param index the index
		 * @param value the value at said index
		 */
		public VectorIndex(int index, double value)
		{
			this.index = index;
			this.value = value;
		}
		
		/**
		 * Creates a new Vector Index
		 * @param index the index
		 * @param value the value at said index
		 * @param parent the vector holding the VectorIndex
		 */
		public VectorIndex(int index, double value, Vector parent)
		{
			this.index = index;
			this.value = value;
			this.parent = parent;
		}
		
		/**
		 * Creates a new Vector Index
		 * @param index the index
		 * @param parent the vector holding the VectorIndex
		 */
		public VectorIndex(int index, Vector parent)
		{
			this.index = index;
			value = parent.get(index);
			this.parent = parent;
		}

		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * @param index the index to set
		 */
		public void setIndex(int index) {
			this.index = index;
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
		public Vector getParent() {
			return parent;
		}

		/**
		 * @param parent the parent to set
		 */
		public void setParent(Vector parent) {
			this.parent = parent;
		}
		
	}
	
	/**
	 * @author Bhagat
	 * An exception for when two vectors are not compatible due to their lengths
	 */
	public static class InvalidLengthException extends RuntimeException{

	 	private static final long serialVersionUID = 5801480752473538001L;
		
	 	private InvalidLengthException() {
	 		super("The two vectors have lengths that are not compatible with another");
		}
		 
		private InvalidLengthException(Vector vector1, Vector vector2) {
			super("The two vectors have lengths that are not compatible with another\nSizes: " + vector1.getSize() + ", " + vector2.getSize() );
		}
		
	}
	
	/**
	 * @author Bhagat
	 * An exception that occurs when you try to access a dimension of the vector that does not exist
	 */
	public static class OutOfDimensionsException extends RuntimeException{
		
		private static final long serialVersionUID = -8587068956721809878L;

		private OutOfDimensionsException() {
	 		super("The vector does not have a value at the specified index");
		}
		 
		private OutOfDimensionsException(Vector v, int index) {
			super("The vector has a size of " + v.getSize() + " and does not have a value with an index of " + index);
		}
		
	}
	
}

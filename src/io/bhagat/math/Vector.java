/**
 * 
 */
package io.bhagat.math;

/**
 * @author Bhagat
 * This class is a class representing a Vector of any number of dimensions
 */
public class Vector {
	
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
		if(this.size != vector.getSize())
		{
			throw new InvalidLengthException(this, vector);
		}
		double sum = 0;
		for(int i = 0; i < size; i++)
			sum += data[i] * vector.get(i);
		
		return sum;
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
	 * @returns the sum of each component
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
	 * sets the value at a certain index
	 * @param index the index
	 * @param value the value
	 */
	public void set(int index, double value)
	{
		data[index] = value;
	}
	
	/**
	 * @return a copy with a new memory allocation
	 */
	public Vector clone()
	{
		return new Vector(data);
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
	 * @return a string representing this Vector object in the form <x1, x2, . . . xn>
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
	 * static clone function
	 * @see the clone function of a vector
	 * @param v the vector to clone
	 * @return the cloned vector with a new memory allocation
	 */
	public static Vector clone(Vector v)
	{
		return v.clone();
	}
	
	/**
	 * static dot product function
	 * @param a first vector
	 * @param b vector to dot with
	 * @return the dot product of the two
	 * @see the dot function of a vector
	 * @throws OutOfDimensionsException
	 * @throws InvalidLengthException
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
	
	// TODO implement this using the determinant of a matrix
	/**
	 * computes the cross product of the input vectors
	 * @param vectors the vectors to cross
	 * @return the resultant vector
	 */
	public static Vector cross(Vector... vectors)
	{
		return vectors[0];
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

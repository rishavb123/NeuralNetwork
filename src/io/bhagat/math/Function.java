package io.bhagat.math;

/**
 * An interface that can hold functions
 * @author Bhagat
 */
public interface Function<E, T> {

	/**
	 * The function this interface contains
	 * @param x the parameter of type specified by Java Generics
	 * @return the return of the function specified by Java Generics 
	 */
	public T f(E x);
	
	/**
	 * function with multiple parameters and multiple returns
	 * @param xs the parameters of type specified by Java Generics
	 * @return the return of objects of type specified by Java Generics in an array
	 */
	default public T[] g(@SuppressWarnings("unchecked") E...xs) { return null; }
}

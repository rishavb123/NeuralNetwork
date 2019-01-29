package io.bhagat.math;

/**
 * @author Bhagat
 * An interface that can hold functions
 */
public interface Function<E, T> {

	/**
	 * The function that this interface is containing
	 */
	public T f(E e);
	
	/**
	 * function with multiple parameters and multiple returns
	 */
	default public T[] g(@SuppressWarnings("unchecked") E...es) { return null; }
}

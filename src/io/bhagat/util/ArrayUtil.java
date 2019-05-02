package io.bhagat.util;

import java.util.ArrayList;

import io.bhagat.math.Function;

/**
 * A few of Utility methods for Arrays and ArrayLists
 * @author Bhagat
 */
public final class ArrayUtil {
	
	/**
	 * Copies the list into a new {@link java.util.ArrayList ArrayList} at a different memory location
	 * @param list the list to be copied
	 * @param <E> the array type
	 * @return the copied list
	 */
	public static <E> ArrayList<E> copy(ArrayList<E> list)
	{
		ArrayList<E> list2 = new ArrayList<>();
		list2.addAll(list);
		return list2;
	}
	
	/**
	 * Copies one array into another
	 * @param arr the array to be copied
	 * @param arr2 the array to copy to
	 * @param <E> the array type
	 * @return the copied array
	 */
	public static <E> E[] copy(E[] arr, E[] arr2)
	{
		if(arr.length != arr2.length)
			return null;
		for(int x=0; x<arr.length; x++)
		{
			arr2[x] = arr[x];
		}
		return arr;
	}
	
	/**
	 * Reshapes a one dimensional array into a two dimensional array
	 * @param arr the 1D array
	 * @param arr2 the 2D array
	 * @param <E> the array type
	 * @return the 2D array
	 * @throws InvalidSizeException thrown when the total number of elements are not the same in each array
	 */
	public static <E> E[][] reshape(E[] arr, E[][] arr2) throws InvalidSizeException
	{
		if(arr.length != arr2.length * arr2[0].length)
			throw new InvalidSizeException("Cannot reshape an array of size " + arr.length + " to " + arr2.length + " x " + arr2[0].length);
	
		for(int i = 0; i < arr2.length; i++)
			for(int j = 0; j < arr2[i].length; j++)
				arr2[i][j] = arr[i * arr2[i].length + j];
		return arr2;
	}
		
	/**
	 * Reshapes a two dimensional array into a one dimensional array
	 * @param arr the 2D array
	 * @param arr2 the 1D array
	 * @param <E> the array type
	 * @return the 1D array
	 * @throws InvalidSizeException thrown when the total number of elements are not the same in each array
	 */
	public static <E> E[] reshape(E[][] arr, E[] arr2) throws InvalidSizeException
	{
		if(arr2.length != arr.length * arr[0].length)
			throw new InvalidSizeException("Cannot reshape an array of size " + arr.length + " x " + arr[0].length + " to " + arr2.length);
	
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[i].length; j++)
				arr2[i * arr[i].length + j] = arr[1][j];
		return arr2;
	}
	
	/**
	 * maps a function to each element in an array
	 * @param arr the array
	 * @param function the function
	 * @param <E> the array type
	 * @return the array after the mapping
	 */
	public static <E> E[] map(E[] arr, Function<E, E> function)
	{
		for(int i = 0; i < arr.length; i++)
			arr[i] = function.f(arr[i]);
		return arr;
	}

	/**
	 * finds the index of a certain object in an array
	 * @param arr the array
	 * @param obj the object to look for
	 * @param <E> the array type
	 * @return the index
	 */
	public static <E> int indexOf(E[] arr, E obj)
	{
		for(int i = 0; i < arr.length; i++)
			if(arr[i] == obj)
				return i;
		return -1;
	}
	
	/**
	 * This method creates an array from an {@link java.util.ArrayList ArrayList}
	 * @param list the array list to create the array from
	 * @param arr the array to write to
	 * @param <E> the array type
	 * @return the new array created
	 */
	public static <E> E[] newArrayFromArrayList(ArrayList<E> list, E[] arr)
	{
		if(list.size() != arr.length)
			return null;
		for(int x=0; x<arr.length; x++)
			arr[x] = list.get(x);
		return arr;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @param <E> the array type
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static <E> ArrayList<E> newArrayList(E... items)
	{
		ArrayList<E> list = new ArrayList<>();
		for(E a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The boolean elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Boolean> newArrayList(boolean... items)
	{
		ArrayList<Boolean> list = new ArrayList<>();
		for(boolean a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The byte elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Byte> newArrayList(byte... items)
	{
		ArrayList<Byte> list = new ArrayList<>();
		for(byte a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The char elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Character> newArrayList(char... items)
	{
		ArrayList<Character> list = new ArrayList<>();
		for(char a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The short elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Short> newArrayList(short... items)
	{
		ArrayList<Short> list = new ArrayList<>();
		for(short a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The int elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Integer> newArrayList(int... items)
	{
		ArrayList<Integer> list = new ArrayList<>();
		for(int a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The long elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Long> newArrayList(long... items)
	{
		ArrayList<Long> list = new ArrayList<>();
		for(long a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The float elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Float> newArrayList(float... items)
	{
		ArrayList<Float> list = new ArrayList<>();
		for(float a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * This method creates a new {@link java.util.ArrayList ArrayList} with the elements specified by the parameters passed into the method
	 * @param items The double elements that will fill the {@link java.util.ArrayList ArrayList} to be returned
	 * @return A new {@link java.util.ArrayList ArrayList} with the items specified from parameters
	 */
	@SafeVarargs
	public static ArrayList<Double> newArrayList(double... items)
	{
		ArrayList<Double> list = new ArrayList<>();
		for(double a: items)
			list.add(a);
		return list;
	}
	
	/**
	 * Prints out an array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 * @param <E> the array type
	 */
	@SafeVarargs
	public static <E> void printArr(E... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the boolean array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(boolean... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the byte array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(byte... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the char array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(char... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the short array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(short... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the int array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(int... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the long array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(long... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the float array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(float... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * Prints out the double array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 */
	@SafeVarargs
	public static void printArr(double... arr)
	{
		System.out.println(newArrayList(arr));
	}
	
	/**
	 * combine two arrays into a third array
	 * @param a the first array to combine
	 * @param b the second array to combine
	 * @param writeTo the array to combine the other two onto
	 * @param <E> the type of the array
	 * @return the combined array
	 */
	public static <E> E[] combine(E[] a, E[] b, E[] writeTo)
	{
		if(a.length+b.length>writeTo.length)
			return null;
		
		for(int x=0;x<a.length;x++)
			writeTo[x] = a[x];
		for(int x=0;x<b.length;x++)
			writeTo[x+a.length] = b[x];
			
		return writeTo;
	}

	/**
	 * creates an double array that ranges from 0 to the specified end
	 * @param stop where to stop (exclusive)
	 * @return the array
	 */
	public static double[] range(double stop)
	{
		return range(0, stop, 1);
	}
	
	/**
	 * creates an double array that ranges from a specified start to the specified end
	 * @param start where to start(inclusive)
	 * @param stop where to stop (exclusive)
	 * @return the array
	 */
	public static double[] range(double start, double stop)
	{
		return range(start, stop, 1);
	}
	
	/**
	 * creates an double array that ranges from a specified to the specified end with a specified step
	 * @param start where to start (inclusive)
	 * @param stop where to stop (exclusive)
	 * @param step the steps to take
	 * @return the array
	 */
	public static double[] range(double start, double stop, double step)
	{
		ArrayList<Double> list = new ArrayList<>();
		for(double i = start; i < stop; i += step)
			list.add(i);
		double[] arr = new double[list.size()];
		for(int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		return arr;
	}
	
	/**
	 * creates an double array that ranges from 0 to the specified end
	 * @param stop where to stop (exclusive)
	 * @return the array
	 */
	public static float[] range(float stop)
	{
		return range(0, stop, 1);
	}
	
	/**
	 * creates an double array that ranges from a specified start to the specified end
	 * @param start where to start(inclusive)
	 * @param stop where to stop (exclusive)
	 * @return the array
	 */
	public static float[] range(float start, float stop)
	{
		return range(start, stop, 1);
	}
	
	/**
	 * creates an double array that ranges from a specified to the specified end with a specified step
	 * @param start where to start (inclusive)
	 * @param stop where to stop (exclusive)
	 * @param step the steps to take
	 * @return the array
	 */
	public static float[] range(float start, float stop, float step)
	{
		ArrayList<Float> list = new ArrayList<>();
		for(float i = start; i < stop; i += step)
			list.add(i);
		float[] arr = new float[list.size()];
		for(int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		return arr;
	}
		
	/**
	 * An exception for when the array or list has the wrong size
	 * @author Bhagat
	 */
	public static class InvalidSizeException extends RuntimeException {

		private static final long serialVersionUID = 2509329868857094933L;
				
		public InvalidSizeException(String msg)
		{
			super(msg);
		}
		
	}
	
}

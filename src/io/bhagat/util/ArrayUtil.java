package io.bhagat.util;

import java.util.ArrayList;

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
	 * Prints out an array in an {@link java.util.ArrayList ArrayList} format
	 * @param arr Array to be printed
	 * @param <E> the array type
	 */
	public static <E> void printArr(E[] arr)
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
		
}

package io.bhagat.math;

import io.bhagat.util.ArrayUtil;

//TODO add more calculus
//TODO add proper comments

/**
 * A class containing many of the calculus operations
 * @author Bhagat
 */
public class Calculus {

	public static final int DEFAULT_NUMBER_OF_ITERATIONS = 1000000;
	public static final double DEFAULT_DX = 0.000000000001;
	
	public static Function<Double, Double> derivative(Function<Double, Double> function, double limitNumber)
	{
		return new Function<Double, Double> () {

			@Override
			public Double f(Double x) {
				return (function.f(x + limitNumber) - function.f(x)) / limitNumber;
			}
			
		};
	}
	
	public static Function<Float, Float> derivative(Function<Float, Float> function, float limitNumber)
	{
		return new Function<Float, Float> () {

			@Override
			public Float f(Float x) {
				return (function.f(x + limitNumber) - function.f(x)) / limitNumber;
			}
			
		};
	}
	
	public static Function<Double, Double> derivative(Function<Double, Double> function)
	{
		return derivative(function, DEFAULT_DX);
	}
	
	//FIXME fix higher order derivatives
	public static Function<Double, Double> higherOrderDerivative(Function<Double, Double> function, int order)
	{
		System.out.println(function.f(4.0));
		for(int i = 0; i < order; i++)
		{
			function = derivative(function);
			System.out.println(function.f(4.0));
		}
		return function;
	}
	
	//TODO change to simpson rule
	//TODO add error function for simpson (or trapezoid)
	public static Function<Double[], Double> integral(Function<Double, Double> function, int n)
	{
		return new Function<Double[], Double> () {

			@Override
			public Double f(Double[] x) {
				return (x[1] - x[0])*(function.f(x[0]) + function.f(x[1]) + 2*(new Vector(ArrayUtil.range(x[0] + (x[1] - x[0]) / n , x[1], (x[1] - x[0]) / n)).map(function).getSum())) / (2*n);
			}
			
		};
	}
	
	public static Function<Double[], Double> integral(Function<Double, Double> function)
	{
		return integral(function, DEFAULT_NUMBER_OF_ITERATIONS);
	}
	
}

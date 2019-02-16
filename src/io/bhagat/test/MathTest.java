/**
 * 
 */
package io.bhagat.test;

import io.bhagat.math.Calculus;
import io.bhagat.math.Function;

public class MathTest {
	public static void main(String[] args) {
		
		Function<Double, Double> function = new Function<Double, Double> () {

			@Override
			public Double f(Double x) {
				return x*x*x;
			}
			
		};
		
		Function<Double, Double> df = Calculus.derivative(function);
		
		double at4 = function.f(4.0);
		double derivativeAt4 = df.f(4.0);
		double orderDerivativeAt4 = Calculus.higherOrderDerivative(function, 4).f(4.0);
		double areaFrom0To4 = Calculus.integral(function).f(new Double[] {0.0, 4.0});
		
		System.out.println("\n\n\n"+at4 + " " + derivativeAt4 + " " + orderDerivativeAt4 + " " + areaFrom0To4);
				
	}
}

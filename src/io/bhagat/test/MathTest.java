/**
 * 
 */
package io.bhagat.test;

import io.bhagat.math.Calculus;
import io.bhagat.math.Function;
import io.bhagat.math.LinearEquation;
import io.bhagat.math.Matrix;
import io.bhagat.math.Vector;
import io.bhagat.util.ArrayUtil;

public class MathTest {
	public static void main(String[] args) {
		
//		Function<Double, Double> function = new Function<Double, Double> () {
//
//			@Override
//			public Double f(Double x) {
//				return x*x*x;
//			}
//			
//		};
//		
//		Function<Double, Double> df = Calculus.derivative(function);
//		
//		double at4 = function.f(4.0);
//		double derivativeAt4 = df.f(4.0);
//		double orderDerivativeAt4 = Calculus.higherOrderDerivative(function, 4).f(4.0);
//		double areaFrom0To4 = Calculus.integral(function).f(new Double[] {0.0, 4.0});
//		
//		System.out.println("\n\n\n"+at4 + " " + derivativeAt4 + " " + orderDerivativeAt4 + " " + areaFrom0To4);
				
//		Matrix A = new Matrix(new double[][] {
//			{1, 1, -1},
//			{1, 1, 1},
//			{1, -1, 0}
//		});
//		Vector b = new Vector(1, 2, 3);
//		
//		double a = A.determinant();
//		double a1b = A.clone().setColumn(b, 0).determinant();
//		double a2b = A.clone().setColumn(b, 1).determinant();
//		double a3b = A.clone().setColumn(b, 2).determinant();
//				
//		Vector x = new Vector(a1b / a, a2b / a, a3b / a);
//		System.out.println(x.toMatrix());
		
		LinearEquation le = new LinearEquation("2*x -5x+ 8 = 10","x");
		System.out.println(le);
		for(LinearEquation.Term w: le.terms)
			System.out.println(w);
	}
	
}

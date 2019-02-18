/**
 * 
 */
package io.bhagat.test;

import java.util.HashMap;

import io.bhagat.math.Calculus;
import io.bhagat.math.Function;
import io.bhagat.math.LinearEquationSolver;

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
		
		System.out.println("\n" + at4 + " " + derivativeAt4 + " " + orderDerivativeAt4 + " " + areaFrom0To4 + "\n\n\n");
		
		String[] equations = {
			"2w+4x+6y+8z=10",
			"3w+4x+5y+6z=28",
			"32w+3x+3y=0",
			"2w+3x+4y=2"
		};
		String[] variables = { "x", "y", "z", "w" };
		
		HashMap<String, Double> result = LinearEquationSolver.solve(equations, variables);
		
		System.out.println("x:" + result.get("x") + "\ny:" + result.get("y") + "\nz:" + result.get("z") + "\nw:" + result.get("w"));
	}
	
}

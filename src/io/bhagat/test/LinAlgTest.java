/**
 * 
 */
package io.bhagat.test;

import java.util.HashMap;
import io.bhagat.math.linearalgebra.LinearEquationSolver;
import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.util.Timer;

public class LinAlgTest {
	public static void main3(String[] args) {
		
		String[] equations = {
			"2w + 4x + 6y + 8z = 10",
			"3w + 4x + 5y + 6z = 28",
			"32w + 3x = -3y",
			"2w + 3x + 4y = 2"
		};
		String[] variables = { "x", "y", "z", "w" };
		Timer t = new Timer();
		
		int method = LinearEquationSolver.GAUSS_METHOD;
		
		t.start();
		HashMap<String, Double> result = LinearEquationSolver.solve(equations, variables, method);
		long elapsedTime = t.elapsed();
		System.out.println("x:" + result.get("x") + "\ny:" + result.get("y") + "\nz:" + result.get("z") + "\nw:" + result.get("w"));
		System.out.println(elapsedTime + " milliseconds ellapsed\n\n");
		
	}
	
	public static void main(String[] args)
	{
		String[] equations = {
				"c-b=-1",
				"b + 3c - 2a = -5",
				"a - 2c = 3"
		};
		String[] variables = { "a","b","c" };
		HashMap<String, Double> result = LinearEquationSolver.solve(equations, variables);
		System.out.println("a: " + result.get("a") + "\nb: " + result.get("b") + "\nc: " + result.get("c"));
	}
	
	public static void main2(String[] args)
	{
		Matrix A = new Matrix(new double[][] {
			{1, 2, 3},
			{0, 4, 5},
			{1, 0, 6}
		});
		
		System.out.print(Matrix.cofactor(A));
		System.out.println(A.inverse());
	}
	
}

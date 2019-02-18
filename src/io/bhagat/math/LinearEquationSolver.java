package io.bhagat.math;

import java.util.ArrayList;
import java.util.HashMap;

import io.bhagat.util.ArrayUtil;
//TODO documentation for Linear Equation Solver
/**
 * a class to solve linear equations
 * @author Bhagat
 */
public class LinearEquationSolver {

	public static HashMap<String, Double> solve(ArrayList<LinearEquation> equations)
	{
		ArrayList<String> variables = new ArrayList<>();
		for(LinearEquation equation: equations)
		{
			for(String variable: equation.getVariables())
				if(variables.indexOf(variable) < 0 && variable != "")
					variables.add(variable);
		}
		//TODO create a proper exception for not enough equations
		if(variables.size() != equations.size())
			throw new RuntimeException();
				
		Matrix A = new Matrix(variables.size(), variables.size());
		Vector b = new Vector(equations.size());
		for(int i = 0; i < equations.size(); i++)
		{
			b.set(i, -1*equations.get(i).getConstant());
			Vector weights = new Vector(variables.size());
			for(int j = 0; j < variables.size(); j++)
				weights.set(j, equations.get(i).getWeight(variables.get(j)));
			A.setRow(weights, i);
		}
		
		double a = A.determinant();

		HashMap<String, Double> results = new HashMap<>();
		
		for(int i = 0; i < variables.size(); i++)
		{
			double aib = A.clone().setColumn(b, i).determinant();
			results.put(variables.get(i), aib / a);
		}
		
		return results;
		
	}
	
	public static HashMap<String, Double> solve(LinearEquation... equations)
	{
		return solve(ArrayUtil.newArrayList(equations));
	}
	
	public static HashMap<String, Double> solve(String[] equations, String[] variables)
	{
		ArrayList<LinearEquation> list = new ArrayList<>();
		for(String equation: equations)
			list.add(new LinearEquation(equation, variables));
		return solve(list);
	}
}

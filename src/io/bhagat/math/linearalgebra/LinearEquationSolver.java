package io.bhagat.math.linearalgebra;

import java.util.ArrayList;
import java.util.HashMap;

import io.bhagat.util.ArrayUtil;

/**
 * a class to solve linear equations
 * @author Bhagat
 */
public class LinearEquationSolver {

	/**
	 * constant for Cramar's Rule
	 */
	public static final int CRAMARS_RULE = 1269;
	/**
	 * constant for Gauss Method
	 */
	public static final int GAUSS_METHOD = 1739;
	/**
	 * constant for Inverse Method
	 */
	public static final int INVERSE_METHOD = 1920;
	
	/**
	 * Takes in LinearEquation objects and then returns the solution
	 * @param equations an ArrayList of the LinearEquations
	 * @return the solutions of the equations
	 */
	public static HashMap<String, Double> solve(ArrayList<LinearEquation> equations)
	{
		return solve(equations, 0);
	}
	
	/**
	 * Takes in LinearEquation objects and then returns the solution
	 * @param equations an ArrayList of the LinearEquations
	 * @param method int defining the method to use to solve this
	 * @return the solutions of the equations
	 */
	public static HashMap<String, Double> solve(ArrayList<LinearEquation> equations, int method)
	{
		ArrayList<String> variables = new ArrayList<>();
		for(LinearEquation equation: equations)
		{
			for(String variable: equation.getVariables())
				if(variables.indexOf(variable) < 0 && variable != "")
					variables.add(variable);
		}

		if(variables.size() != equations.size())
			throw new ImproperNumberOfEquationsException();
				
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
		
		switch(method)
		{
			case GAUSS_METHOD:
				return gaussMethod(A, b, variables);
			case CRAMARS_RULE:
				return cramarsRule(A, b, variables);
			case INVERSE_METHOD:
				return inverseA(A, b, variables);
		}
		return gaussMethod(A, b, variables);
		
	}
	
	/**
	 * computes the solutions of the matrix equation Ax=b using cramar's rule
	 * @param A a matrix for the equation
	 * @param b a vector for the equation
	 * @param variables the variable names
	 * @return the solutions to the matrix equation
	 */
	public static HashMap<String, Double> cramarsRule(Matrix A, Vector b, ArrayList<String> variables)
	{
		double a = A.determinant();

		HashMap<String, Double> results = new HashMap<>();
		
		for(int i = 0; i < variables.size(); i++)
		{
			double aib = A.clone().setColumn(b, i).determinant();
			results.put(variables.get(i), aib / a);
		}
		
		return results;
	}
	
	/**
	 * computes the solutions of the matrix equation Ax=b using the guassian method
	 * @param A a matrix for the equation
	 * @param b a vector for the equation
	 * @param variables the variable names
	 * @return the solutions to the matrix equation
	 */
	public static HashMap<String, Double> gaussMethod(Matrix A, Vector b, ArrayList<String> variables)
	{
		HashMap<String, Double> results = new HashMap<>();
		double[] answers = new double[variables.size() + 1];
		
		Matrix augmentedMatrix = new Matrix(A.getRows(), A.getColumns() + 1);
		for(int i = 0; i < augmentedMatrix.getColumns() - 1; i++)
		{
			augmentedMatrix.setColumn(A.getVectorColumns()[i], i);
		}
		augmentedMatrix.setColumn(b, A.getColumns());
		
		Matrix rref = augmentedMatrix.reducedRowEchelonForm();
		Vector[] rrefRows = rref.getVectorRows();
		for(int i = variables.size() - 1; i >= 0; i--)
		{
			Vector row = rrefRows[i];
			answers[i] = row.get(row.getSize() - 1) - new Vector(answers).dot(row);
			results.put(variables.get(i), answers[i]);
		}
				
		return results;
	}
	
	/**
	 * computes the solutions of the matrix equation Ax=b using the inverse of A
	 * @param A a matrix for the equation
	 * @param b a vector for the equation
	 * @param variables the variable names
	 * @return the solutions to the matrix equation
	 */
	public static HashMap<String, Double> inverseA(Matrix A, Vector b, ArrayList<String> variables)
	{
		HashMap<String, Double> results = new HashMap<>();
		
		Vector x = Matrix.multiply(A.inverse(), b.toMatrix()).toVector();
		
		for(int i = 0; i < variables.size(); i++)
		{
			results.put(variables.get(i), x.get(i));
		}
		
		return results;
	}
	
	/**
	 * Takes in LinearEquation objects and then returns the solution
	 * @param equations an ArrayList of the LinearEquations
	 * @return the solutions of the equations
	 */
	public static HashMap<String, Double> solve(LinearEquation... equations)
	{
		return solve(ArrayUtil.newArrayList(equations));
	}
	
	/**
	 * Takes in LinearEquation objects and then returns the solution
	 * @param equations an ArrayList of the LinearEquations
	 * @param method int defining the method to use to solve this
	 * @return the solutions of the equations
	 */
	public static HashMap<String, Double> solve(int method, LinearEquation... equations)
	{
		return solve(ArrayUtil.newArrayList(equations), method);
	}
	
	/**
	 * Takes in string equations and then solves them
	 * @param equations the string equations
	 * @param variables the variables to solve for
	 * @param method int defining the method to use to solve this
	 * @return the solutions of the equations
	 */
	public static HashMap<String, Double> solve(String[] equations, String[] variables, int method)
	{
		ArrayList<LinearEquation> list = new ArrayList<>();
		for(String equation: equations)
			list.add(new LinearEquation(equation, variables));
		return solve(list, method);
	}
	
	/**
	 * Takes in string equations and then solves them
	 * @param equations the string equations
	 * @param variables the variables to solve for
	 * @return the solutions of the equations
	 */
	public static HashMap<String, Double> solve(String[] equations, String[] variables)
	{
		ArrayList<LinearEquation> list = new ArrayList<>();
		for(String equation: equations)
			list.add(new LinearEquation(equation, variables));
		return solve(list, 0);
	}
	
	/**
	 * An exception for not giving the same amount of variables and equations
	 * @author Bhagat
	 */
	private static class ImproperNumberOfEquationsException extends RuntimeException {

		private static final long serialVersionUID = 620411548306974794L;

		/**
		 * constructs an ImproperNumberOfEquationsException
		 */
		public ImproperNumberOfEquationsException()
		{
			super("The number of variables and equations must be the same");
		}

	}
	
}

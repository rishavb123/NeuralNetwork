package io.bhagat.math.linearalgebra;

import java.util.ArrayList;

import io.bhagat.util.ArrayUtil;

/**
 * A class for linear equations
 * @author Bhagat
 */
public class LinearEquation {

	/**
	 * An array of the terms in the linear equation
	 */
	public Term[] terms;
	
	/**
	 * Constructs a Linear Equation object from a string equation and a list of variables
	 * @param equation the string equation
	 * @param variables the list of variables
	 */
	public LinearEquation(String equation, String... variables) {
		equation = equation.replace("*", "").replace(" ", "").replace("+","~+").replace("-", "~-");
		int equalsIndex = equation.indexOf("=");
		equation = equation.replace("=","~+");
		String[] stringTerms = equation.split("~");
		terms = new Term[stringTerms.length];
		for(int i = 0; i < stringTerms.length; i++)
		{
			int index = -1;
			for(int j = 0; j < variables.length; j++)
				if(stringTerms[i].indexOf(variables[j]) >= 0)
				{
					index = j;
					break;
				}
			String w = (index == -1)? stringTerms[i] : stringTerms[i].substring(0, stringTerms[i].indexOf(variables[index]));
			double weight = Double.parseDouble((w.equals("+") || w.equals("-") || (i == 0 && w.equals("")))? w+"1": w);
			if(equation.indexOf(w) >= equalsIndex)
				weight *= -1;
			String variable = (index == -1)? "" : variables[index];
		
			terms[i] = new Term(weight, variable);
			
		}

		ArrayList<Term> termsList = new ArrayList<>();
		
		for(int i = 0; i < terms.length; i++)
		{
			boolean exists = false;
			for(int j = 0; j < termsList.size(); j++)
			{
				if(terms[i].getVariable().equals(termsList.get(j).getVariable()))
				{
					exists = true;
					termsList.get(j).setWeight(termsList.get(j).getWeight() + terms[i].getWeight());
					break;
				}
			}
			if(!exists)
				termsList.add(terms[i]);
		}
		
		terms = new Term[termsList.size()];
		ArrayUtil.newArrayFromArrayList(termsList, terms);
		
	}
	
	/**
	 * creates a list of variables from the terms
	 * @return the variables
	 */
	public ArrayList<String> getVariables()
	{
		ArrayList<String> variables = new ArrayList<>();
		for(Term t: terms)
			if(t.getVariable().length() > 0)
				variables.add(t.getVariable());
		return variables;
	}
	
	/**
	 * gets all the weights that the variables are multiplied with in each term
	 * @return a vector containing the weights
	 */
	public Vector getWeights()
	{
		Vector weights = new Vector(terms.length - 1);
		int i = 0;
		for(Term t: terms)
		{
			if(t.getVariable().length() > 0)
			{
				weights.set(i, terms[i].getWeight());
				i++;
			}
		}
		return weights;
	}
	
	/**
	 * gets a weight for a specific variable
	 * @param variable the variable
	 * @return the weight
	 */
	public double getWeight(String variable)
	{
		for(Term t: terms)
			if(t.getVariable().equals(variable))
				return t.getWeight();
		return 0.0;
	}
	
	/**
	 * finds the constant in the equation
	 * @return the constant
	 */
	public double getConstant()
	{
		return getWeight("");
	}
	
	/**
	 * converts back to a string equation
	 */
	public String toString()
	{
		String s = "";
		boolean first = true;
		for(Term t: terms)
		{
			if(t.getWeight() > 0 && !first)
			{
				s += "+" + t;
			}
			else
				s += t;
			first = false;
		}
		return s + "=0.0";
	}
	
	/**
	 * A class for each term in the equation
	 * @author Bhagat
	 */
	public class Term {
		
		/**
		 * the scalar multiple of the variable
		 */
		private double weight;
		/**
		 * the variable name
		 */
		private String variable;
		
		/**
		 * constructs a term
		 * @param weight the weight
		 * @param variable the variable
		 */
		private Term(double weight, String variable)
		{
			this.setWeight(weight);
			this.setVariable(variable);
		}

		/**
		 * @return the weight
		 */
		public double getWeight() {
			return weight;
		}

		/**
		 * @param weight the weight to set
		 */
		public void setWeight(double weight) {
			this.weight = weight;
		}

		/**
		 * @return the variable
		 */
		public String getVariable() {
			return variable;
		}

		/**
		 * @param variable the variable to set
		 */
		public void setVariable(String variable) {
			this.variable = variable;
		}
		
		/**
		 * A way of visualizing each term
		 */
		public String toString()
		{
			return this.weight+this.variable;
		}
		
	}
	
}

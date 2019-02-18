package io.bhagat.math;

import java.util.ArrayList;

import io.bhagat.util.ArrayUtil;
//TODO documentation for Linear Equation

/**
 * A class for linear equations
 * @author Bhagat
 */
public class LinearEquation {

	public Term[] terms;
	
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
	
	public ArrayList<String> getVariables()
	{
		ArrayList<String> variables = new ArrayList<>();
		for(Term t: terms)
			if(t.getVariable().length() > 0)
				variables.add(t.getVariable());
		return variables;
	}
	
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
	
	public double getWeight(String variable)
	{
		for(Term t: terms)
			if(t.getVariable().equals(variable))
				return t.getWeight();
		return 0.0;
	}
	
	public double getConstant()
	{
		return getWeight("");
	}
	
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
	
	public class Term {
		
		private double weight;
		private String variable;
		
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
		
		public String toString()
		{
			return this.weight+this.variable;
		}
		
	}
	
}

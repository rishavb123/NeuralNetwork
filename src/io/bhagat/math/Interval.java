package io.bhagat.math;

/**
 * A class for an interval of numbers
 * @author Bhagat
 */
public class Interval{

	/**
	 * the lower bound
	 */
	private double lowerBound;
	/**
	 * the upper bound
	 */
	private double upperBound;
	/**
	 * whether it is open or not
	 */
	private boolean open;
	
	/**
	 * creates an interval with a lower and upper bound and whether it is open or not
	 * @param lowerBound the lower bound
	 * @param upperBound the upper bound
	 * @param open whether it is open or not
	 */
	public Interval(double lowerBound, double upperBound, boolean open)
	{
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.open = open;
	}
	
	/**
	 * creates an interval with a lower and upper bound (defaults open to false)
	 * @param lowerBound the lower bound
	 * @param upperBound the upper bound
	 */
	public Interval(double lowerBound, double upperBound)
	{
		this(lowerBound, upperBound, false);
	}
	
	/**
	 * checks if a number is inside this interval
	 * @param x the number
	 * @return whether or not the number is inside the interval
	 */
	public boolean inInterval(double x) 
	{
		if(x > lowerBound && x < upperBound)
			return true;
		if(x == lowerBound || x == upperBound)
			return !open;
		return false;
	}

	/**
	 * @return the range of the interval
	 */
	public double range()
	{
		return upperBound - lowerBound;
	}
	
	/**
	 * @return the midpoint of the interval
	 */
	public double midpoint()
	{
		return (lowerBound + upperBound) / 2;
	}
	
	/**
	 * @return the lowerBound
	 */
	public double getLowerBound() {
		return lowerBound;
	}

	/**
	 * @param lowerBound the lowerBound to set
	 */
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @return the upperBound
	 */
	public double getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound the upperBound to set
	 */
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

}

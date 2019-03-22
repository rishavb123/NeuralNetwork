package io.bhagat.util;

import java.util.Date;

import io.bhagat.math.Function;

/**
 * A class for timing calculations to test efficiency
 * @author Bhagat
 */
public class Timer {

	private long startTime;
	
	/**
	 * creates a Timer object
	 */
	public Timer() 
	{
		start();
	}

	/**
	 * starts the timer by initializing the startTime to the current time
	 */
	public void start()
	{
		startTime = new Date().getTime();
	}
	
	/**
	 * calls the start method
	 */
	public void restart()
	{
		start();
	}
	
	/**
	 * @return the time elapsed in milliseconds since the timer started
	 */
	public long elapsed()
	{
		return new Date().getTime() - startTime;
	}
	
	/**
	 * times a function
	 * @param function the function to time
	 * @return the number of milliseconds elapsed
	 */
	public static long time(Function<Void, Void> function)
	{
		Timer timer = new Timer();
		timer.start();
		function.f(null);
		return timer.elapsed();
	}
	
}

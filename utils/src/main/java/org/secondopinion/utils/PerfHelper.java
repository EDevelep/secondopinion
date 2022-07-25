package org.secondopinion.utils;

public class PerfHelper
{
	private long _beginTime;

	/**
	 * PrerformanceHelper constructor
	 */
	public PerfHelper()
	{
		reset();
	}

	/**
	 * Reset this PerformanceHelper
	 */
	public void reset()
	{
		_beginTime = System.currentTimeMillis();
	}

	/**
	 * Get the time elapsed since the last reset (in milliseconds),
	 * displayed as the following string: " ???? msecs. "
	 * @return time in milliseconds
	 */
	public String getTimeTakenStr()
	{
		long elapsedTimeMillis = timeTaken();
		return " " + elapsedTimeMillis + " msecs. ";
	}
	
	public long timeTaken()
	{
		return System.currentTimeMillis() - _beginTime;
	}
}


package com.bsb.hike.models;

public class NUXTaskDetails
{

	private String incentiveId;

	private String activityId;

	private int incrMax;

	private int incrMin;

	private int min;
	
	private int incentiveAmount;

	/**
	 * @param incentiveId
	 * @param activityId
	 * @param incrMax
	 * @param incrMin
	 * @param min
	 * @param max
	 * @param incentiveAmount 
	 */
	public NUXTaskDetails(String incentiveId, String activityId, int incrMax, int incrMin, int min, int max, int incentiveAmount)
	{
		super();
		this.incentiveId = incentiveId;
		this.activityId = activityId;
		this.incrMax = incrMax;
		this.incrMin = incrMin;
		this.min = min;
		this.max = max;
		this.setIncentiveAmount(incentiveAmount);
	}

	private int max;

	/**
	 * @return the incentiveId
	 */
	public String getIncentiveId()
	{
		return incentiveId;
	}

	/**
	 * @return the activityId
	 */
	public String getActivityId()
	{
		return activityId;
	}

	/**
	 * @return the incrMax
	 */
	public int getIncrMax()
	{
		return incrMax;
	}

	/**
	 * @return the incrMin
	 */
	public int getIncrMin()
	{
		return incrMin;
	}

	/**
	 * @return the min
	 */
	public int getMin()
	{
		return min;
	}

	/**
	 * @return the max
	 */
	public int getMax()
	{
		return max;
	}

	public int getIncentiveAmount()
	{
		return incentiveAmount;
	}

	public void setIncentiveAmount(int incentiveAmount)
	{
		this.incentiveAmount = incentiveAmount;
	}

}

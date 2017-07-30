package com.virtusapolaris.task.policy;

/**
 * This class is responsible for performing subtraction operation
 * 
 * @author Murugadoss
 *
 */
public class SubtractionEvent implements BaseEvent {

	/*
	 * @see
	 * com.virtusapolaris.task.policy.BaseOperation#calculate(java.lang.Double,
	 * java.lang.Double)
	 */
	@Override
	public Double calculate(Double firstValue, Double secondValue) {
		return secondValue - firstValue;
	}
}

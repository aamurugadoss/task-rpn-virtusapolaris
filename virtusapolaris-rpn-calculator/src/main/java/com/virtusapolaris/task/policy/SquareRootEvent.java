package com.virtusapolaris.task.policy;

import static java.lang.Math.sqrt;

/**
 * This class is responsible for performing square root operation
 * 
 * @author Murugadoss
 *
 */
public class SquareRootEvent implements BaseEvent {

	/*
	 * @see
	 * com.virtusapolaris.task.policy.BaseOperation#calculate(java.lang.Double,
	 * java.lang.Double)
	 */
	@Override
	public Double calculate(Double firstValue, Double secondValue) {
		return sqrt(firstValue);
	}
}

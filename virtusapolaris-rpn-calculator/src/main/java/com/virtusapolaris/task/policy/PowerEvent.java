package com.virtusapolaris.task.policy;

import static java.lang.Math.pow;

import com.virtusapolaris.task.utility.Constant;

/**
 * This class is responsible for performing undo of square root operation
 * 
 * @author Murugadoss
 *
 */
public class PowerEvent implements BaseEvent {

	/*
	 * 
	 * @see
	 * com.virtusapolaris.task.policy.BaseOperation#calculate(java.lang.Double,
	 * java.lang.Double)
	 */
	@Override
	public Double calculate(Double firstValue, Double secondValue) {
		return pow(firstValue, Constant.TWO);
	}
}

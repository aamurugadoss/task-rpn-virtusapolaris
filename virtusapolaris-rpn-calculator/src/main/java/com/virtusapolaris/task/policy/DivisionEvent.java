package com.virtusapolaris.task.policy;


/**
 * This class is responsible for performing division operation
 * 
 * @author Murugadoss
 *
 */
public class DivisionEvent implements BaseEvent {

	/*
	 * @see
	 * com.virtusapolaris.task.policy.BaseOperation#calculate(java.lang.Double,
	 * java.lang.Double)
	 */
	@Override
	public Double calculate(Double firstValue, Double secondValue) {
		if (firstValue == 0) {
			throw new ArithmeticException("Cannot devided by zero");
		}
		return secondValue / firstValue;
	}
}

package com.virtusapolaris.task.policy;

/**
 * @author murugadoss.a Responsible for addition operation
 */
public class AdditionEvent implements BaseEvent {

	/*
	 * @see
	 * com.virtusapolaris.task.policy.BaseOperation#calculate(java.lang.Double,
	 * java.lang.Double)
	 */
	@Override
	public Double calculate(Double firstValue, Double secondValue) {
		return secondValue + firstValue;
	}
}

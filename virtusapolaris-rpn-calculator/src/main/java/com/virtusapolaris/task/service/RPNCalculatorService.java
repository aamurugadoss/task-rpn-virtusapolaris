package com.virtusapolaris.task.service;

import java.text.DecimalFormat;
import java.util.Stack;

import com.virtusapolaris.task.factory.EventFactory;
import com.virtusapolaris.task.policy.BaseEvent;
import com.virtusapolaris.task.utility.Constant;
import com.virtusapolaris.task.utility.EventUtil;

/**
 * @author murugadoss.a This service performs Reverse Polish Notation
 *         calculation
 */
public class RPNCalculatorService {

	private int position = 0;
	private Stack<Double> values = new Stack<>();
	private Stack<String> events = new Stack<>();
	private boolean isUndoEvent = false;

	/**
	 * @param expression
	 *            - String
	 * @throws Exception
	 */
	public void processExpression(String expression) throws Exception {
		String[] inputs = expression.split("\\s");
		position = 0;
		for (String input : inputs) {
			if ((!isUndoEvent) && isInvalidInput(input)) {
				throw new Exception("Input Expression Contains Invalid Operator or Invalid Value.");
			}
			try {
				position++;
				Double value = Double.parseDouble(input);
				values.push(value);
				if (!isUndoEvent) {
					events.push(null);
				}
			} catch (NumberFormatException ex) {
				performEvents(EventUtil.getEvent(input));
			}
		}
	}

	/**
	 * @param input
	 *            - String
	 * @return
	 */
	private boolean isInvalidInput(String input) {
		try {
			Double.parseDouble(input);
			return false;
		} catch (NumberFormatException ex) {
			// if (Util.getOperatorList().contains(input)) {
			if (EventUtil.contains(input)) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * @param operator
	 *            - String
	 * @throws Exception
	 */
	private void performEvents(EventUtil event) throws Exception {
		// perform clear operation if operation is 'clear'
		if (event.equals(EventUtil.CLEAR)) {
			performClearEvent();
		} // perform undo operation if operation is 'undo'
		else if (event.equals(EventUtil.UNDO)) {
			performUndoEvent();
		} // perform +, -, *, /, sqrt calculation based on operator
		else {
			performCalculationEvent(event);
		}
	}

	/**
	 * This API perform clear operation
	 * 
	 * @throws Exception
	 */
	private void performClearEvent() throws Exception {
		if (events.isEmpty() && values.isEmpty()) {
			throw new Exception("Stack is empty");
		}
		values.clear();
		events.clear();
	}

	/**
	 * This API perform undo operation
	 * 
	 * @throws Exception
	 */
	private void performUndoEvent() throws Exception {
		if (events.isEmpty()) {
			throw new Exception("Stack is empty");
		}
		String undoOperation = events.pop();
		if (undoOperation == null) {
			values.pop();
		} else {
			isUndoEvent = true;
			processExpression(undoOperation);
			isUndoEvent = false;
		}
	}

	/**
	 * This API perform +, -, *, /, sqrt calculation based on operator
	 * 
	 * @param event
	 * @throws Exception
	 */
	private void performCalculationEvent(EventUtil event) throws Exception {
		BaseEvent baseEvent = EventFactory.getEvent(event);
		if (baseEvent == null) {
			throw new Exception("Operator (" + event.getEventType() + ") is not valid");
		}
		// Checking that there are enough operand for the operation
		if ((EventUtil.POWER != event) && event.getSize() > values.size()) {
			StringBuilder builder = new StringBuilder();
			builder.append("Operator ").append(event.getEventType()).append(" (position: ").append(position)
					.append("): insufficient parameters");
			throw new Exception(builder.toString());
		}
		Double firstValue = values.pop();
		Double secondValue = null;
		if ((EventUtil.POWER != event) && Constant.TWO == event.getSize()) {
			secondValue = values.pop();
		}
		// call calculate method
		Double eventResult = baseEvent.calculate(firstValue, secondValue);
		if (eventResult != null) {
			values.push(eventResult);
			// prepare undo operation
			if (!isUndoEvent) {
				constructUndoEvent(event, firstValue);
			}
		}
	}

	/**
	 * This API is responsible for construct undo operation.
	 * 
	 * @param event
	 * @param value
	 */
	private void constructUndoEvent(EventUtil event, Double value) {
		// find undo event based on operator
		StringBuilder builder = new StringBuilder();
		if (EventUtil.SQUAREROOT == event) {
			builder.append(event.getUndoEventType());
		} else {
			builder.append(value).append(" ").append(event.getUndoEventType()).append(" ").append(value);
		}
		events.push(builder.toString());
	}

	/**
	 * Responsible for final result
	 * 
	 * @return - String
	 */
	public String getEventResult() {
		// 10 decimal precision
		DecimalFormat decimalFormat = new DecimalFormat("0.##########");
		StringBuilder eventResult = new StringBuilder();
		int count = 0;
		for (Double result : getValues()) {
			count++;
			eventResult.append(decimalFormat.format(result));
			if (count != values.size()) {
				eventResult.append(" ");
			}
		}
		return eventResult.toString();
	}

	/**
	 * @return
	 */
	public Stack<Double> getValues() {
		return values;
	}
}

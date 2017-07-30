package com.virtusapolaris.task.factory;

import java.util.HashMap;
import java.util.Map;

import com.virtusapolaris.task.policy.AdditionEvent;
import com.virtusapolaris.task.policy.DivisionEvent;
import com.virtusapolaris.task.policy.MultiplicationEvent;
import com.virtusapolaris.task.policy.BaseEvent;
import com.virtusapolaris.task.policy.PowerEvent;
import com.virtusapolaris.task.policy.SquareRootEvent;
import com.virtusapolaris.task.policy.SubtractionEvent;
import com.virtusapolaris.task.utility.EventUtil;

import static com.virtusapolaris.task.utility.EventUtil.ADDITION;
import static com.virtusapolaris.task.utility.EventUtil.DIVISION;
import static com.virtusapolaris.task.utility.EventUtil.MULTIPLICATION;
import static com.virtusapolaris.task.utility.EventUtil.SUBTRACTION;
import static com.virtusapolaris.task.utility.EventUtil.POWER;
import static com.virtusapolaris.task.utility.EventUtil.SQUAREROOT;

/**
 * @author murugadoss.a Responsible for providing Operation objects
 */
public class EventFactory {

	private static final Map<EventUtil, BaseEvent> eventMap = new HashMap<>();

	static {
		eventMap.put(ADDITION, new AdditionEvent());
		eventMap.put(DIVISION, new DivisionEvent());
		eventMap.put(MULTIPLICATION, new MultiplicationEvent());
		eventMap.put(SUBTRACTION, new SubtractionEvent());
		eventMap.put(POWER, new PowerEvent());
		eventMap.put(SQUAREROOT, new SquareRootEvent());
	}

	/**
	 * @param operand
	 *            - String
	 * @return - BaseOperation
	 */
	public static BaseEvent getEvent(EventUtil event) {
		return eventMap.get(event);
	}
}

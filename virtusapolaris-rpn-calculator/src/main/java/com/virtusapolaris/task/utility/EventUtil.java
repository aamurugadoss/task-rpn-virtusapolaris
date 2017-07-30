package com.virtusapolaris.task.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murugadoss
 *
 */
public enum EventUtil {

	ADDITION("+", "-", Constant.TWO), SUBTRACTION("-", "+", Constant.TWO), MULTIPLICATION("*", "/",
			Constant.TWO), DIVISION("/", "*", Constant.TWO), SQUAREROOT("sqrt", "pow", Constant.ONE), POWER("pow", null,
					Constant.ONE), UNDO("undo", null, Constant.ZERO), CLEAR("clear", null, Constant.ZERO);

	private String eventType;
	private String undoEventType;
	private int size;

	// Lookup map for getting a event
	private static final Map<String, EventUtil> eventUtilMap = new HashMap<String, EventUtil>();

	static {
		for (EventUtil d : EventUtil.values()) {
			eventUtilMap.put(d.getEventType(), d);
		}
	}

	EventUtil(String eventType, String undoEventType, int size) {
		this.eventType = eventType;
		this.undoEventType = undoEventType;
		this.size = size;
	}

	public String getEventType() {
		return eventType;
	}

	public String getUndoEventType() {
		return undoEventType;
	}

	public int getSize() {
		return size;
	}

	public static EventUtil getEvent(String event) {
		return eventUtilMap.get(event);
	}

	public static boolean contains(String event) {
		//Power operator can be allowed only for undo operation
		if (event.equals(POWER.getEventType())) {
			return false;
		}
		return eventUtilMap.containsKey(event);
	}
}

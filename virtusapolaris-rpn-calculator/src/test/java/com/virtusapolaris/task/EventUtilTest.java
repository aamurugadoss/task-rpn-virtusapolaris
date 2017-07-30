package com.virtusapolaris.task;

import static org.junit.Assert.*;

import org.junit.Test;

import com.virtusapolaris.task.utility.Constant;
import com.virtusapolaris.task.utility.EventUtil;

import static com.virtusapolaris.task.utility.EventUtil.ADDITION;
import static com.virtusapolaris.task.utility.EventUtil.POWER;
import static com.virtusapolaris.task.utility.EventUtil.SUBTRACTION;
import static com.virtusapolaris.task.utility.EventUtil.CLEAR;

/**
 * Utility class test
 * 
 * @author Murugadoss
 *
 */
public class EventUtilTest {

	/**
	 * Test get operation map API
	 */
	@Test
	public void testGetEventMap() {
		assertFalse(EventUtil.contains(POWER.getEventType()));
		assertTrue("clear", EventUtil.contains(CLEAR.getEventType()));
		assertEquals("+", ADDITION.getEventType());
		assertEquals("pow", POWER.getEventType());
		assertEquals(Constant.TWO, SUBTRACTION.getSize());
	}
}

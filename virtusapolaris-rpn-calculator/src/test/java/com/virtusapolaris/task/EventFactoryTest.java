package com.virtusapolaris.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static java.lang.Math.sqrt;
import org.junit.Test;

import com.virtusapolaris.task.factory.EventFactory;
import com.virtusapolaris.task.policy.AdditionEvent;
import com.virtusapolaris.task.utility.EventUtil;
import static com.virtusapolaris.task.utility.EventUtil.ADDITION;
import static com.virtusapolaris.task.utility.EventUtil.DIVISION;
import static com.virtusapolaris.task.utility.EventUtil.MULTIPLICATION;
import static com.virtusapolaris.task.utility.EventUtil.SUBTRACTION;
import static com.virtusapolaris.task.utility.EventUtil.POWER;
import static com.virtusapolaris.task.utility.EventUtil.SQUAREROOT;

/**
 * Operation Factory Testing
 * @author Murugadoss
 *
 */
public class EventFactoryTest {
	/**
	 * Test divided by zero
	 * @throws Exception
	 */
	@Test(expected = ArithmeticException.class)
	public void testArithmeticException() throws Exception {
		EventFactory.getEvent(EventUtil.DIVISION).calculate(0.0, 1.0);
	}
	/**
	 * Test 
	 */
	@Test
	public void testGetOperation() {
		assertSame(AdditionEvent.class, EventFactory.getEvent(ADDITION).getClass());
		assertEquals(new Double(10), EventFactory.getEvent(SUBTRACTION).calculate(10.0, 20.0));
		assertEquals(new Double(9), EventFactory.getEvent(MULTIPLICATION).calculate(3.0, 3.0));
		assertEquals(new Double(1), EventFactory.getEvent(DIVISION).calculate(3.0, 3.0));
		assertEquals(sqrt(5),0, EventFactory.getEvent(SQUAREROOT).calculate(5.0, null));
		assertEquals(25,0, EventFactory.getEvent(POWER).calculate(5.0, null));
	}
}

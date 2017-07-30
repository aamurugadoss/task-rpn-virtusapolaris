package com.virtusapolaris.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.virtusapolaris.task.service.RPNCalculatorService;

/**
 * Calculator Service Test
 * 
 * @author Murugadoss
 *
 */
public class RPNCalculationServiceTest {

	/**
	 * Test invalid expressions
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidation() throws Exception {

		RPNCalculatorService service = new RPNCalculatorService();
		try {
			service.processExpression("a 1 -");
			service.processExpression("999*");
		} catch (Exception e) {
			assertEquals("Input Expression Contains Invalid Operator or Invalid Value.", e.getMessage());
		}

		try {
			service.processExpression("undo");
		} catch (Exception e) {
			assertEquals("Stack is empty", e.getMessage());
		}
		try {
			service.processExpression("clear");
		} catch (Exception e) {
			assertEquals("Stack is empty", e.getMessage());
		}
	}

	/**
	 * Test insufficient parameter expression
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInValidExpression() throws Exception {
		RPNCalculatorService service = new RPNCalculatorService();
		try {
			service.processExpression("1 2 3 * 5 + * * 6 5");
		} catch (Exception e) {
			assertEquals("Operator * (position: 8): insufficient parameters", e.getMessage());
		}
	}

	/**
	 * Test Exception conditions
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testExpectException() throws Exception {
		RPNCalculatorService service = new RPNCalculatorService();
		service.processExpression("clear");
		service.processExpression("undo");
	}

	/**
	 * Test divided by zero
	 * 
	 * @throws Exception
	 */
	@Test(expected = ArithmeticException.class)
	public void testArithmeticException() throws Exception {
		RPNCalculatorService service = new RPNCalculatorService();
		service.processExpression("1 0 /");
	}

	/**
	 * Test All the example expression given in task
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidExpression() throws Exception {
		RPNCalculatorService service = new RPNCalculatorService();
		// Example 1
		service.processExpression("5 2");
		assertEquals("5 2", service.getEventResult());
		service.processExpression("clear");
		String s = new String();
		assertTrue(s.equals(service.getEventResult()));
		// Example 2
		service.processExpression("2 sqrt");
		assertEquals("1.4142135624", service.getEventResult());
		service.processExpression("clear 9 sqrt");
		assertEquals("3", service.getEventResult());
		// Example 3
		service.processExpression("clear 5 2 -");
		assertEquals("3", service.getEventResult());
		service.processExpression("3 -");
		assertEquals("0", service.getEventResult());
		service.processExpression("clear");
		// Example 4
		service.processExpression("5 4 3 2");
		assertEquals("5 4 3 2", service.getEventResult());
		service.processExpression("undo undo *");
		assertEquals("20", service.getEventResult());
		service.processExpression("5 *");
		assertEquals("100", service.getEventResult());
		service.processExpression("undo");
		assertEquals("20 5", service.getEventResult());
		service.processExpression("clear");
		// Example 5
		service.processExpression("7 12 2 /");
		assertEquals("7 6", service.getEventResult());
		service.processExpression("*");
		assertEquals("42", service.getEventResult());
		service.processExpression("4 /");
		assertEquals("10.5", service.getEventResult());
		service.processExpression("clear");
		// Example 6
		service.processExpression("1 2 3 4 5");
		assertEquals("1 2 3 4 5", service.getEventResult());
		service.processExpression("*");
		assertEquals("1 2 3 20", service.getEventResult());
		service.processExpression("clear 3 4 -");
		assertEquals("-1", service.getEventResult());
		service.processExpression("clear");
		// Example 7
		service.processExpression("1 2 3 4 5");
		assertEquals("1 2 3 4 5", service.getEventResult());
		service.processExpression("* * * *");
		assertEquals("120", service.getEventResult());
		service.processExpression("clear");
		// more undo and square root operations
		service.processExpression("1 2 3 4 5");
		service.processExpression("* *");
		assertEquals("1 2 60", service.getEventResult());
		service.processExpression("undo undo");
		assertEquals("1 2 3 4 5", service.getEventResult());
		service.processExpression("undo");
		assertNotEquals("1 2 3", service.getEventResult());
		assertEquals("1 2 3 4", service.getEventResult());
		service.processExpression("5 sqrt");
		assertEquals("1 2 3 4 2.2360679775", service.getEventResult());
		service.processExpression("undo");
		assertEquals("1 2 3 4 5", service.getEventResult());
		service.processExpression("clear");
		// checking values in stack
		service.processExpression("12 sqrt");
		assertEquals(new Double(3.4641016151377544), 0, service.getValues().pop());
	}
}

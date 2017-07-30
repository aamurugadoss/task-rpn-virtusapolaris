package com.virtusapolaris.task;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.virtusapolaris.task.service.RPNCalculatorService;
import com.virtusapolaris.task.utility.Constant;

@SpringBootApplication
public class RPNCalculatorMain {

	public static void main(String[] args) {
		// SpringApplication.run(VirtusapolarisRpnCalculatorApplication.class,
		// args);
		System.out.println("Enter expression to calculate..");
		Scanner scanner = new Scanner(System.in);

		RPNCalculatorService service = new RPNCalculatorService();
		while (scanner.hasNext()) {
			String nextLine = scanner.nextLine().trim();
			// Don't process if user enters empty string or enter
			if (Constant.ZERO == nextLine.length()) {
				continue;
			}
			try {
				service.processExpression(nextLine);
			} catch (Exception e) {
				System.out.println("WARNING: " + e.getMessage());
			} finally {
				System.out.println("Stack: " + service.getEventResult());
			}
		}
		scanner.close();
	}
}

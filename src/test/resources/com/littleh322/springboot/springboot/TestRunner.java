package com.littleh322.springboot.springboot;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.littleh322.springboot.springboot.tests.ParameterizedTestWithJSON;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ParameterizedTestWithJSON.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println("success: " + result.wasSuccessful());
	}
}
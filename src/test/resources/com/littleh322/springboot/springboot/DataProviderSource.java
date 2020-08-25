package com.littleh322.springboot.springboot;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderSource {
	private static final String PATH_TO_JSON = "JSONFileToTest";

	// ---- Example ------//

	@DataProvider(name = "employeeData")
	public static Object[][] getEmployeeData() throws IOException {
		return ObjectToJson.convertPractices(System.getProperties().getProperty(PATH_TO_JSON,
				"c:/source/littleh322/JavaSpringReact/src/test/resources/com/littleh322/springboot/springboot/employees.json"));
	}
}

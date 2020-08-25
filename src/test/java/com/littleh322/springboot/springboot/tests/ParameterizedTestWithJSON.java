package com.littleh322.springboot.springboot.tests;

import java.io.IOException;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.littleh322.springboot.springboot.ObjectToJson;
import com.littleh322.springboot.springboot.SpringbootApplication;
import com.littleh322.springboot.springboot.modal.Employee;

@SpringBootTest(classes = SpringbootApplication.class)
public class ParameterizedTestWithJSON {
	private static final String PATH_TO_JSON = "JSONFileToTest";

	@DataProvider(name = "employeeData")
	public static Object[][] getEmployeeData() throws IOException {
		return ObjectToJson.convertPractices(System.getProperties().getProperty(PATH_TO_JSON,
				"c:/source/littleh322/JavaSpringReact/src/test/resources/com/littleh322/springboot/springboot/employees.json"));
	}

	@BeforeClass
	public static void setup() {
		System.out.println("\r\nRUNNING THE CLASS WITH GIVEN JSON DATA:\r\n");
	}

	@Test(dataProvider = "employeeData")
	public void shouldReturnCorrectData(Employee[] employees) {
		for (Employee employee : employees) {
			System.out.println("shouldReturnCorrectData: { " + employee.getId() + ", " + employee.getName() + ", "
					+ employee.getDepartment() + ", " + employee.getDob() + ", " + employee.getGender() + " }");
		}
	}

}

package com.littleh322.springboot.springboot.tests;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.littleh322.springboot.springboot.DataProviderSource;
import com.littleh322.springboot.springboot.SpringbootApplication;
import com.littleh322.springboot.springboot.modal.Employee;

@SpringBootTest(classes = SpringbootApplication.class)
public class ParameterizedTestWithJSON {
	
	@BeforeClass
	public static void setup() {
		System.out.println("\r\nRUNNING THE CLASS WITH GIVEN JSON DATA:\r\n");
	}

	@Test(dataProvider = "employeeData", dataProviderClass = DataProviderSource.class)
	public void shouldReturnCorrectData(Employee[] employees) {
		for (Employee employee : employees) {
			System.out.println("shouldReturnCorrectData: { " + employee.getId() + ", " + employee.getName() + ", "
					+ employee.getDepartment() + ", " + employee.getDob() + ", " + employee.getGender() + " }");
		}
	}

}

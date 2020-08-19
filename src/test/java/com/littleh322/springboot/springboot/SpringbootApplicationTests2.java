package com.littleh322.springboot.springboot;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.littleh322.springboot.springboot.modal.Employee;
import com.littleh322.springboot.springboot.service.EmployeeService;

@SpringBootTest
@RunWith(Parameterized.class)
public class SpringbootApplicationTests2 {
	private static final String PATH_TO_JSON = "JSONFileToTest";

	// Example
//	new Object[][] { { 3, "Joseph Haberberger", "IT", "1993-06-10", "Male" },
//		{ 4, "Andy Ayres", "Sales", "1993-09-11", "Male" } };

	@Parameters
	public static List<Employee> data() throws IOException {
		Employee[] allEmployees = ObjectToJson.convertJSONToEmployees(System.getProperties().getProperty(PATH_TO_JSON,
				"c:/source/littleh322/JavaSpringReact/src/test/java/com/littleh322/springboot/springboot/employees.json"));
		return Arrays.asList(allEmployees);
	}

	private final Employee employee;
	private final int id;
	private final String name;
	private final String department;
	private final String dob;
	private final String gender;

	@Autowired
	private EmployeeService employeeService;

	public SpringbootApplicationTests2(int id, String name, String department, String dob, String gender) {
		this.employee = new Employee();
		this.id = id;
		this.name = name;
		this.department = department;
		this.dob = dob;
		this.gender = gender;
	}

	@Test
	public void shouldReturnCorrectName() {
		System.out.println("got it!");
//		Employee employee = employeeService.get(id);
//		assertThat(employee.getName().equals("failOnPurpose"));
	}

}

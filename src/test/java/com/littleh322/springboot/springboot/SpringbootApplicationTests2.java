package com.littleh322.springboot.springboot;

import java.io.IOException;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;

import com.littleh322.springboot.springboot.modal.Employee;

@SpringBootTest
@RunWith(Parameterized.class)
public class SpringbootApplicationTests2 {
	private static final String PATH_TO_JSON = "JSONFileToTest";

	// ---- Example ------//
	// new Object[][] { { 3, "Joseph Haberberger", "IT", "1993-06-10", "Male" },
	// { 4, "Andy Ayres", "Sales", "1993-09-11", "Male" } };

	@Parameters
	public static Collection<Employee> data() throws IOException {
		return ObjectToJson.convertJSONToEmployees(System.getProperties().getProperty(PATH_TO_JSON,
				"c:/source/littleh322/JavaSpringReact/src/test/java/com/littleh322/springboot/springboot/employees.json"));
	}

	private final Employee employee;

	public SpringbootApplicationTests2(Employee employee) {
		this.employee = employee;
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("");
		System.out.println("RUNNING THE CLASS WITH GIVEN JSON DATA:");
	}

	@Test
	public void shouldReturnCorrectName() {
		System.out.println("{ " + employee.getId() + ", " + employee.getName() + ", " + employee.getDepartment() + ", "
				+ employee.getDob() + ", " + employee.getGender() + " }");
	}

}

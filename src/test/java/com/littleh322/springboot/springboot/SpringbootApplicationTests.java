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
public class SpringbootApplicationTests {
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

	private final int id;
	private final String name;
	private final String department;
	private final String dob;
	private final String gender;

	@Autowired
	private EmployeeService employeeService;

	public SpringbootApplicationTests(int id, String name, String department, String dob, String gender) {
		this.employee = new Employee();
		this.id = id;
		this.name = name;
		this.department = department;
		this.dob = dob;
		this.gender = gender;
	}

	@BeforeTestMethod
	public void beforeTestMethod(TestContext testContext) throws Exception {
		System.out.println("beforeTestMethod : {} " + testContext.getTestMethod());
	};

	@AfterTestMethod
	public void afterTestMethod(TestContext testContext) throws Exception {
		System.out.println("afterTestMethod : {} " + testContext.getTestMethod());
	};

	@Test
	public void shouldReturnCorrectName() {
		System.out.println("got it!");
//		Employee employee = employeeService.get(id);
//		assertThat(employee.getName().equals("failOnPurpose"));
	}

//	private static RequestSpecification spec;
//
//	// TODO: use this for APi POST and GET..
//	@BeforeTestMethod
//	public static void initSpec() {
//		spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("http://localhost:8080/")
//				.addFilter(new ResponseLoggingFilter()).addFilter(new RequestLoggingFilter()).build();
//	}
//
//	@Autowired
//	private EmployeeController controller;
//	@Autowired
//	private EmployeeService employeeService;
//
//	@Test
//	void employeeControllerLoads() {
//		assertThat(controller).isNotNull();
//	}
//
//	@Test
//	void getEmployee() {
//		List<Employee> list = employeeService.get();
//		assertThat(list).isNotEmpty();
//	}
//
//	@Test
//	void getEmployeeById() {
//		String employeeName = "Joseph Haberberger";
//		Employee employee = employeeService.get(3);
//		assertThat(employee.getName().equals(employeeName));
//	}

}

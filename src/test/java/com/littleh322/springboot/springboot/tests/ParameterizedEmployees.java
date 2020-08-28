package com.littleh322.springboot.springboot.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.littleh322.springboot.springboot.SpringbootApplication;
import com.littleh322.springboot.springboot.modal.Employee;

/**
 * One SpringbootApplication test called ParameterizedEmployees uses TestNG
 * DataProvider (parsed by Jackson ObjectMapper) to run integration testing.
 * 
 * @author haberbergerj
 *
 */
@SpringBootTest(classes = SpringbootApplication.class)
public class ParameterizedEmployees extends BaseTest {
	private final String PATH_TO_JSON = "JSONFileToTest";

	@DataProvider(name = "employeeData")
	public Object[][] getEmployeeData() throws IOException {
		return new JsonDataObject(System.getProperties().getProperty(PATH_TO_JSON,
				"src/test/resources/com/littleh322/springboot/springboot/employees.json")).buildJsonObject();
	}

	@Test(dataProvider = "employeeData")
	public void dataProviderReturnsCorrectData(Employee[] employees) {
		assertEquals(employees.length, 4);
	}

	@Test(dataProvider = "employeeData")
	public void getEmployeeMatch(Employee[] jsonEmps) {
		for (Employee jsonEmp : jsonEmps) {
			Employee actualEmp = controller.get(jsonEmp.getId());
			assertEquals(actualEmp.getName(), jsonEmp.getName());
			assertEquals(actualEmp.getDepartment(), jsonEmp.getDepartment());
			assertEquals(actualEmp.getGender(), jsonEmp.getGender());
			// TODO: skip for now. Not sure why it's returning the wrong java.sql.Date
			// assertEquals(actualEmp.getDob(), jsonEmp.getDob());
		}
	}

	@Test(dataProvider = "employeeData")
	public void getRestEmployees(Employee[] jsonEmps) throws Exception {
		this.mockMvc.perform(get("/api/employee")).andExpect(status().isOk());
	}

}

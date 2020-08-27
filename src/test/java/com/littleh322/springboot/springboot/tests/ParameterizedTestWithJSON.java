package com.littleh322.springboot.springboot.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.littleh322.springboot.springboot.SpringbootApplication;
import com.littleh322.springboot.springboot.modal.Employee;

@SpringBootTest(classes = SpringbootApplication.class)
public class ParameterizedTestWithJSON {
	private final String PATH_TO_JSON = "JSONFileToTest";

	public class JsonDataObject {

		private String jsonStr;

		public JsonDataObject(String fileName) throws IOException {
			this.jsonStr = readFileJsonAsString(fileName);
		}

		public Object[][] buildJsonObject() throws JsonMappingException, JsonProcessingException {
			System.out.println("Deserializing JSON to Object....");
			Employee[] readValues = new ObjectMapper().readValue(jsonStr, Employee[].class);
			for (Employee employee : readValues) {
				System.out.println("{ " + employee.getId() + ", " + employee.getName() + ", " + employee.getDepartment()
						+ ", " + employee.getDob() + ", " + employee.getGender() + " }");
			}
			return new Object[][] { readValues };
		}

		private String readFileJsonAsString(String fileName) throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			// delete the last new line separator
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();
			return stringBuilder.toString();
		}
	}

	@DataProvider(name = "employeeData")
	public Object[][] getEmployeeData() throws IOException {
		return new JsonDataObject(System.getProperties().getProperty(PATH_TO_JSON,
				"src/test/resources/com/littleh322/springboot/springboot/employees.json")).buildJsonObject();
	}

	@BeforeClass
	public static void setup() {
		System.out.println("\r\nBEFORE THE CLASS: BUILD THE EMLPOYEE DATA\r\n");
	}

	@Test(dataProvider = "employeeData")
	public void shouldReturnCorrectData(Employee[] employees) {
		for (Employee employee : employees) {
			System.out.println("shouldReturnCorrectData: { " + employee.getId() + ", " + employee.getName() + ", "
					+ employee.getDepartment() + ", " + employee.getDob() + ", " + employee.getGender() + " }");
		}
	}

	@AfterClass
	public static void teardown() {
		System.out.println("\r\nAFTER THE CLASS: CLEAN UP\r\n");
	}

}

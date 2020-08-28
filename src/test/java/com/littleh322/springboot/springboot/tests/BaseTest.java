package com.littleh322.springboot.springboot.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.littleh322.springboot.springboot.SpringbootApplication;
import com.littleh322.springboot.springboot.controller.EmployeeController;
import com.littleh322.springboot.springboot.modal.Employee;

/**
 * The BaseTest.class is the fore-front for all other tests. It includes
 * functionality that MUST occur before running any other test, such as
 * connecting the WebApplicatioContext. It uses MockMvc to stand up the
 * SpringbootApplication. We do BeforeClass here to share functionality across
 * multiple classes. There is one test method here to verify the API is stood up
 * correctly. A JsonDataObject class is also found here for Json conversion
 * purposes. Finally, protected abstraction elements, such as the bean factory,
 * are provided to be used by extended classes.
 * 
 * @author haberbergerj
 *
 */
@EnableWebMvc
@WebAppConfiguration
@ContextConfiguration(classes = { EmployeeController.class })
@SpringBootTest(classes = SpringbootApplication.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private List<Employee> employees;

	@Autowired
	protected EmployeeController controller;

	protected MockMvc mockMvc;

	public class JsonDataObject {

		private String jsonStr;

		public JsonDataObject(String fileName) throws IOException {
			this.jsonStr = readFileJsonAsString(fileName);
		}

		public Object[][] buildJsonObject() throws JsonMappingException, JsonProcessingException {
			Employee[] readValues = new ObjectMapper().readValue(jsonStr, Employee[].class);
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

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testController() throws Exception {
		this.mockMvc.perform(get("/api/employee")).andExpect(status().isOk());
	}

	@AfterClass
	public static void teardown() {
		System.out.println("\r\nAFTER THE CLASS: CLEAN UP\r\n");
	}
}
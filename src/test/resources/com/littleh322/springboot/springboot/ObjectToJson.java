package com.littleh322.springboot.springboot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.littleh322.springboot.springboot.modal.Employee;

public class ObjectToJson {

	public static String getEmployeeAsString(Employee emp) {
		// Creating Object of ObjectMapper define in Jakson Api
		ObjectMapper jsonObect = new ObjectMapper();
		try {
			// get Employee object as a json string
			return jsonObect.writeValueAsString(emp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Get the data to be inserted into the object
	public static Employee getObjectData(Employee emp) {
		// Insert the data
		emp.setName("GeeksforGeeks");
		emp.setGender("Male");
		emp.setDob(DateUtils.generateRandomDate());
		// Return the object
		return emp;
	}

	// Get the data to be inserted into the object
	public static String getEmployeesAsString(Employee[] emps) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int e = 0; e < emps.length; e++) {
			sb.append(getEmployeeAsString(emps[e]));
			if (e < emps.length - 1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

	/** moved to @{ParameterizedTestWithJSON.JsonDataObject} */
	@Deprecated
	public static Collection<Employee> convertJSONToEmployees(String filePathToJson) throws IOException {
		String jsonStr = readFileAsString(filePathToJson);
		System.out.println("Deserializing JSON to Object....");
		Collection<Employee> readValues = new ObjectMapper().readValue(jsonStr,
				new TypeReference<Collection<Employee>>() {
				});
		for (Employee employee : readValues) {
			System.out.println("{ " + employee.getId() + ", " + employee.getName() + ", " + employee.getDepartment()
					+ ", " + employee.getDob() + ", " + employee.getGender() + " }");
		}
		return readValues;
	}

	/** moved to @{ParameterizedTestWithJSON.JsonDataObject} */
	@Deprecated
	public static Employee convertJSONToEmployee(String filePathToJson) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = readFileAsString(filePathToJson);
		System.out.println("Deserializing JSON to Object:");
		Employee employee = mapper.readValue(jsonStr, Employee.class);
		System.out.println("{ " + employee.getId() + ", " + employee.getName() + ", " + employee.getDepartment() + ", "
				+ employee.getDob() + ", " + employee.getGender() + " }");
		return employee;
	}

	/** moved to @{ParameterizedTestWithJSON.JsonDataObject} */
	@Deprecated
	public static String readFileAsString(String fileName) throws IOException {
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

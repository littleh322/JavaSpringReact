package com.littleh322.springboot.springboot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.littleh322.springboot.springboot.modal.Employee;

public class ObjectToJson {
	public static void main(String[] args) throws IOException {
		System.out.println(getEmployeeAsString(new FakeEmployee().getFakeEmployee()));

		String filePath = "c:/source/littleh322/JavaSpringReact/src/test/java/com/littleh322/springboot/springboot/employees.json";
//		Employee employees = convertJSONToEmployees(filePath);
		System.out.println("returning employees!");
	}

	public static String getEmployeeAsString(Employee emp) {
		// Insert the data into the object
		emp = getObjectData(emp);
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

	public static Employee convertJSONToEmployee(String filePathToJson) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = readFileAsString(filePathToJson);
		System.out.println("Deserializing JSON to Object:");
		Employee employee = mapper.readValue(jsonStr, Employee.class);
		System.out.println("{ " + employee.getId() + ", " + employee.getName() + ", " + employee.getDepartment() + ", "
				+ employee.getDob() + ", " + employee.getGender() + " }");
		return employee;
	}

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

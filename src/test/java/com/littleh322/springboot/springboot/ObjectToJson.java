package com.littleh322.springboot.springboot;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.littleh322.springboot.springboot.modal.Employee;

public class ObjectToJson {
	public static void main(String[] args) {
		System.out.println(getEmployeeAsString(new FakeEmployee().getFakeEmployee()));
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
}

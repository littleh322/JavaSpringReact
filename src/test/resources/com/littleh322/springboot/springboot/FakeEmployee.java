package com.littleh322.springboot.springboot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.littleh322.springboot.springboot.modal.Employee;

public class FakeEmployee {

	private Employee employee;
	private List<String> departments;
	private List<String> genders;

	private enum Departments {
		Human_Resources, IT, Accounting, Research, Management, Sales
	}

	private enum Gender {
		Male, Female
	}

	private List<String> buildDepartments() {
		List<String> departments = new ArrayList<>();
		for (Departments dep : Departments.values()) {
			departments.add(dep.toString().replace("_", " "));
		}
		return departments;
	}

	private List<String> buildGenders() {
		List<String> genders = new ArrayList<String>();
		for (Gender gender : Gender.values()) {
			genders.add(gender.toString());
		}
		return genders;
	}

	public FakeEmployee() {
		departments = buildDepartments();
		genders = buildGenders();
		employee = new Employee();
		employee.setId(generateId());
		employee.setName(generateFakeFullName());
		employee.setDepartment(generateRandomDepartment());
		employee.setGender(getRandomGender());
		employee.setDob(DateUtils.generateRandomDate());
	}

	private String getRandomGender() {
		Random random = new Random();
		return genders.get(random.nextInt(genders.size()));
	}

	private Integer generateId() {
		Random rand = new Random(); // instance of random class
		int upperbound = 1000000000;
		// generate random values from 0-99999999
		int int_random = rand.nextInt(upperbound);
		return int_random;
	}

	public Employee getFakeEmployee() {
		return employee;
	}

	private String generateRandomDepartment() {
		Random random = new Random();
		return departments.get(random.nextInt(departments.size()));
	}

	private String generateFakeName() {
		// class variable
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		final java.util.Random rand = new java.util.Random();

		// consider using a Map<String,Boolean> to say whether the identifier is being
		// used or not
		final Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public String generateFakeFullName() {
		return generateFakeName() + " " + generateFakeName();
	}
}

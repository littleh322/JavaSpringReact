package com.littleh322.springboot.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.littleh322.springboot.springboot.controller.EmployeeController;
import com.littleh322.springboot.springboot.modal.Employee;
import com.littleh322.springboot.springboot.service.EmployeeService;

@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private EmployeeController controller;
	@Autowired
	private EmployeeService employeeService;

	@Test
	void employeeControllerLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void getEmployee() {
		List<Employee> list = employeeService.get();
		assertThat(list).isNotEmpty();
	}

	@Test
	void getEmployeeById() {
		String employeeName = "Joseph Haberberger";
		Employee employee = employeeService.get(3);
		assertThat(employee.getName().equals(employeeName));
	}

}

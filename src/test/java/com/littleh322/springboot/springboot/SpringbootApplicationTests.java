package com.littleh322.springboot.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.littleh322.springboot.springboot.controller.EmployeeController;
import com.littleh322.springboot.springboot.modal.Employee;
import com.littleh322.springboot.springboot.service.EmployeeService;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
class SpringbootApplicationTests {
	private static RequestSpecification spec;

	// TODO: use this for APi POST and GET..
	@BeforeTestMethod
	public static void initSpec() {
		spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("http://localhost:8080/")
				.addFilter(new ResponseLoggingFilter()).addFilter(new RequestLoggingFilter()).build();
	}

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

package com.littleh322.springboot.springboot.main.java.util;

import com.littleh322.springboot.springboot.modal.Employee;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeUtil {
    public static Employee generateEmployee() {
        Employee employee = new Employee();
        employee.setId(null);
        employee.setDob(Date.valueOf(LocalDate.now()));
        employee.setGender("male");
        employee.setDepartment("department");
        employee.setName("name");
        return employee;
    }
}

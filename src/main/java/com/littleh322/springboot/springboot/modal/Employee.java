package com.littleh322.springboot.springboot.modal;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_emp")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String name;

	@Column
	private String department;

	@Column
	private Date dob;

	@Column
	private String gender;

	@Override
	public String toString() {
		String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dob);
		StringBuilder sb = new StringBuilder();
		sb.append("Employee [id=").append(id)
		.append(", name=").append(name)
		.append(", department=").append(department)
		.append(", dob=").append(formatted)
		.append(", gender=").append(gender).append("]");
		return sb.toString();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
}

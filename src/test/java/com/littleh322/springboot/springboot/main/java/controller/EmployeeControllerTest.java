package com.littleh322.springboot.springboot.main.java.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.littleh322.springboot.springboot.modal.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.littleh322.springboot.springboot.main.java.util.EmployeeUtil.generateEmployee;
import static com.littleh322.springboot.springboot.main.java.util.ObjectMapperUtil.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    private Employee cachedEmployee;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Test
    @Order(1)
    void test_addEmployee() throws Exception {
        Employee employee = generateEmployee();
        MockHttpServletResponse result = this.mockMvc.perform(post("/api/employee")
                        .content(asJsonString(employee))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").exists())
                .andReturn().getResponse();
        cachedEmployee = gson.fromJson(result.getContentAsString(), Employee.class);
    }

    @Test
    @Order(2)
    void test_getEmployees() throws Exception {
        this.mockMvc.perform(get("/api/employee"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void test_getEmployeeById() throws Exception {
        this.mockMvc.perform(get("/api/employee/{id}", cachedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(cachedEmployee)));
    }

    @Test
    @Order(4)
    void test_updateEmployee() throws Exception {
        Employee updatedEmployee = cachedEmployee;
        updatedEmployee.setId(cachedEmployee.getId());
        updatedEmployee.setName("newFirstName");
        this.mockMvc.perform(put("/api/employee")
                        .content(asJsonString(updatedEmployee))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(updatedEmployee)));
    }

    @Test
    @Order(5)
    void test_deleteEmployee() throws Exception {
        this.mockMvc.perform(delete("/api/employee/{id}", cachedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee (id: " + cachedEmployee.getId() + ") has been removed"));
    }

    @AfterAll
    void teardown() {
        System.out.println("\r\nAFTER THE CLASS: CLEAN UP\r\n");
    }
}
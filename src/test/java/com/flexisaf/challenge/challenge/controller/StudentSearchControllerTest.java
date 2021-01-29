package com.flexisaf.challenge.challenge.controller;

import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.service.DepartmentService;
import com.flexisaf.challenge.challenge.service.StudentSearchService;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentSearchController.class)
class StudentSearchControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentSearchService studentSearchService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void searchStudents() throws Exception {
        Student student = new Student();
        student.setFirstName("name");
        when(studentSearchService.getSearchResult("name")).thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/search/name"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.status", Matchers.is("success")))
                .andExpect(jsonPath("$.data[0].firstName", Matchers.is("name")));
    }
}
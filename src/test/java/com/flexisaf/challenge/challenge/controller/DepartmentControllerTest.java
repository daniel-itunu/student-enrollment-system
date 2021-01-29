package com.flexisaf.challenge.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flexisaf.challenge.challenge.dto.DepartmentDto;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentSearchService studentSearchService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addDepartment() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("biology");
        when(departmentService.addDepartment(departmentDto)).thenReturn("department added");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(departmentDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/departments")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.message", Matchers.is("Invalid validation")))
                .andExpect(jsonPath("$.errors.id", Matchers.is("id cannot be blank")));
    }

    @Test
    void retrieveDepartment() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("biology");
        when(departmentService.findDepartmentByName("biology")).thenReturn(departmentDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/departments/biology"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.status", Matchers.is("success")))
                .andExpect(jsonPath("$.data.name", Matchers.is("biology")));
    }

    @Test
    void retrieveDepartments() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("biology");
        when(departmentService.retrieveDepartments()).thenReturn(List.of(departmentDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/departments"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.status", Matchers.is("success")))
                .andExpect(jsonPath("$.data.length()", Matchers.is(1)));
    }
}
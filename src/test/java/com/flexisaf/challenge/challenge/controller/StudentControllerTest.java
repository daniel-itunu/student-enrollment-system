package com.flexisaf.challenge.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flexisaf.challenge.challenge.dto.StudentDto;
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
import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentSearchService studentSearchService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addStudent() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("name");
        studentDto.setLastName("name");
        studentDto.setDateOfBirth("2000-05-03");
        studentDto.setGender("m");
        studentDto.setDepartment("biology");
        studentDto.setPhoneNumber("+2347082547384");
        studentDto.setOtherName("name");
        when(studentService.addStudent(studentDto)).thenReturn("student added");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(studentDto );
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.message", Matchers.is("student added")));
    }

    @Test
    void retrieveStudent() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setMatricNumber("FLEXISAF/001");
        when(studentService.retrieveStudent("001")).thenReturn(studentDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/001"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.status", Matchers.is("success")))
                .andExpect(jsonPath("$.data.matricNumber", Matchers.is("FLEXISAF/001")));
    }

    @Test
    void retrieveStudents() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setMatricNumber("FLEXISAF/001");
        when(studentService.retrieveStudents()).thenReturn(List.of(studentDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.status", Matchers.is("success")))
                .andExpect(jsonPath("$.data.length()", Matchers.is(1)));
    }

    @Test
    void deleteStudent() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setMatricNumber("FLEXISAF/001");
        when(studentService.deleteStudent("001")).thenReturn("student deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/students/001"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.status", Matchers.is("success")))
                .andExpect(jsonPath("$.message", Matchers.is("student deleted")));
    }

    @Test
    void updateStudent() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("name");
        studentDto.setMatricNumber("FLEXISAF/001");
        studentDto.setLastName("name");
        studentDto.setDepartment("biology");
        studentDto.setOtherName("name");
        studentDto.setCreatedAt(LocalDateTime.now().toString());
        studentDto.setPhoneNumber("+2347082563748");
        studentDto.setGender("m");
        studentDto.setDateOfBirth("2000-09-09");
        when(studentService.updateStudent(studentDto,"001")).thenReturn("student updated");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(studentDto );
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/students/001").content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("success")))
               .andExpect(jsonPath("$.message", Matchers.is("student updated")));
    }
}
package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.StudentSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentSearchServiceImplTest {

    @Autowired
    private StudentSearchService studentSearchService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getSearchResult() throws Exception {
        Student student = new Student();
        student.setFirstName("name");
        when(studentRepository.findAll()).thenReturn(List.of(student));
        assertEquals("name", studentSearchService.getSearchResult("name").get(0).getFirstName());
    }
}
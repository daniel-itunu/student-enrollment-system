package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.dto.DepartmentDto;
import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.model.Department;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.repository.DepartmentRepository;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.DepartmentService;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    void addStudent() {

        Department department = new Department();
        department.setName("biology");
        department.setId("bio");
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("biology");
        departmentDto.setId("bio");
        String saved = departmentService.addDepartment(departmentDto);
        assertEquals("department added", saved);


        Student student = new Student();
        student.setFirstName("name");
        student.setDateOfBirth("1994-05-03");
        student.setDepartment(department);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDto studentDto = new StudentDto();
        studentDto.setDateOfBirth("1994-05-03");
        studentDto.setDepartment("biology");
        assertEquals("student added", studentService.addStudent(studentDto));
    }

    @Test
    void updateStudent() {
    }
}
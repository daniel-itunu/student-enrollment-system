package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.model.Department;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.repository.DepartmentRepository;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.DepartmentService;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void addStudent() {
        Department department = new Department();
        department.setName("biology");
        when(departmentRepository.findDepartmentByName("biology")).thenReturn(department);

        Student student1 = new Student();
        student1.setFirstName("name");
        student1.setLastName("name");
        student1.setDateOfBirth("2000-05-03");
        student1.setGender("m");
        student1.setDepartment(department);
        student1.setPhoneNumber("+2347082547384");
        student1.setOtherName("name");
        student1.setMatricNumber("FLEXISAF/001");
        when(studentRepository.findAll()).thenReturn(List.of(student1));

        Student student = new Student();
        student.setFirstName("name");
        student.setLastName("name");
        student.setDateOfBirth("2000-05-03");
        student.setGender("m");
        student.setDepartment(department);
        student.setPhoneNumber("+2347082547384");
        student.setOtherName("name");
        student.setMatricNumber("FLEXISAF/002");
        when(studentRepository.save(student)).thenReturn(student);

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("name");
        studentDto.setLastName("name");
        studentDto.setDateOfBirth("2000-05-03");
        studentDto.setGender("m");
        studentDto.setDepartment("biology");
        studentDto.setPhoneNumber("+2347082547384");
        studentDto.setOtherName("name");
        assertEquals("failed to add student", studentService.addStudent(studentDto));
    }

    @Test
    void retrieveStudents() throws Exception {
        Student student = new Student();
        Department department = new Department();
        department.setName("biology");
        student.setDepartment(department);
        when(studentRepository.findAll()).thenReturn(List.of(student));
        assertEquals(1, studentService.retrieveStudents().size());
    }

    @Test
    void deleteStudent() {
        Student student = new Student();
        when(studentRepository.getStudentByMatricNumber("FLEXISAF/001")).thenReturn(student);
        assertEquals("student FLEXISAF/001 deleted", studentService.deleteStudent("001"));
    }

    @Test
    void retrieveStudent() throws Exception {
        Student student = new Student();
        student.setFirstName("name");
        Department department = new Department();
        department.setName("biology");
        student.setDepartment(department);
        when(studentRepository.getStudentByMatricNumber("FLEXISAF/001")).thenReturn(student);
        assertEquals("name", studentService.retrieveStudent("001").getFirstName());
    }

    @Test
    void updateStudent() {
        Department department = new Department();
        department.setName("biology");
        when(departmentRepository.findDepartmentByName("biology")).thenReturn(department);

        Student student = new Student();
        when(studentRepository.getStudentByMatricNumber("FLEXISAF/001")).thenReturn(student);

        student.setFirstName("peter");
        when(studentRepository.save(student)).thenReturn(student);

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("name");
        studentDto.setLastName("name");
        studentDto.setDateOfBirth("2000-05-03");
        studentDto.setGender("m");
        studentDto.setDepartment("biology");
        studentDto.setPhoneNumber("+2347082547384");
        studentDto.setOtherName("name");
        assertEquals("student FLEXISAF/001 updated", studentService.updateStudent(studentDto,"001"));
    }
}
package com.flexisaf.challenge.challenge.service;

import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    String addStudent(StudentDto studentDto);
    List<Student> retrieveStudents();
    void deleteStudent(String matricNumber);
    Student retrieveStudent(String matricNumber);
}

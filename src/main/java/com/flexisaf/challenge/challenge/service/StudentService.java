package com.flexisaf.challenge.challenge.service;

import com.flexisaf.challenge.challenge.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    String addStudent(StudentDto studentDto);
    List<StudentDto> retrieveStudents() throws Exception;
    String deleteStudent(String matricNumber);
    StudentDto retrieveStudent(String matricNumber) throws Exception;
    String updateStudent(StudentDto studentDto, String matricNumber);
}

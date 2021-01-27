package com.flexisaf.challenge.challenge.controller;

import com.flexisaf.challenge.challenge.apiresponse.ApiResponse;
import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private final StudentService StudentService;

    public StudentController(StudentService studentService) {
        StudentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<ApiResponse<String>> addStudent(@RequestBody StudentDto studentDto){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, StudentService.addStudent(studentDto));
        return ResponseEntity.ok(apiResponse);
    }

}

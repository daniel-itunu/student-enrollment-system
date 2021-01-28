package com.flexisaf.challenge.challenge.controller;

import com.flexisaf.challenge.challenge.apiresponse.ApiResponse;
import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<ApiResponse<String>> addStudent(@RequestBody StudentDto studentDto){
        ApiResponse apiResponse = new ApiResponse(studentService.addStudent(studentDto),"success" );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/students/{matricNumber}")
    public ResponseEntity<ApiResponse<StudentDto>> retrieveStudent(@PathVariable String matricNumber) throws Exception {
        ApiResponse apiResponse = new ApiResponse("success", studentService.retrieveStudent(matricNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<StudentDto>>> retrieveStudents() throws Exception {
        ApiResponse apiResponse = new ApiResponse( "success", studentService.retrieveStudents());
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/students/{matricNumber}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable String matricNumber) throws Exception {
        ApiResponse apiResponse = new ApiResponse(studentService.deleteStudent(matricNumber),"success");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/students/{matricNumber}")
    public ResponseEntity<ApiResponse<String>> updateStudent(@RequestBody StudentDto studentDto, @PathVariable String matricNumber) throws Exception {
        ApiResponse apiResponse = new ApiResponse(studentService.updateStudent(studentDto, matricNumber),"success" );
        return ResponseEntity.ok(apiResponse);
    }
}

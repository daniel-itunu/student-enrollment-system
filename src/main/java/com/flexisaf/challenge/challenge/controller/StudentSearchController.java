package com.flexisaf.challenge.challenge.controller;

import com.flexisaf.challenge.challenge.apiresponse.ApiResponse;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.service.StudentSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentSearchController {
    private final StudentSearchService studentSearchService;

    public StudentSearchController(StudentSearchService studentSearchService) {
        this.studentSearchService = studentSearchService;
    }

    @GetMapping("/students/search/{input}")
    public ResponseEntity<ApiResponse<List<Student>>> searchStudents(@PathVariable String input) throws Exception {
        ApiResponse apiResponse = new ApiResponse("success", studentSearchService.getSearchResult(input));
        return ResponseEntity.ok(apiResponse);
    }
}

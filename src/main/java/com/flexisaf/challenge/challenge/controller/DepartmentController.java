package com.flexisaf.challenge.challenge.controller;

import com.flexisaf.challenge.challenge.apiresponse.ApiResponse;
import com.flexisaf.challenge.challenge.dto.DepartmentDto;
import com.flexisaf.challenge.challenge.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/department")
    public ResponseEntity<ApiResponse<String>> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, departmentService.addDepartment(departmentDto));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/department")
    public ResponseEntity<ApiResponse<DepartmentDto>> retrieveDepartment(String name) throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "success", departmentService.findDepartmentByName(name));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/departments")
    public ResponseEntity<ApiResponse<List<DepartmentDto>>> retrieveDepartments() throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "success", departmentService.retrieveDepartments());
        return ResponseEntity.ok(apiResponse);
    }

}

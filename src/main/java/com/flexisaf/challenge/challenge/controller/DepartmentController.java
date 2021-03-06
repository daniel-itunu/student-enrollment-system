package com.flexisaf.challenge.challenge.controller;

import com.flexisaf.challenge.challenge.apiresponse.ApiResponse;
import com.flexisaf.challenge.challenge.dto.DepartmentDto;
import com.flexisaf.challenge.challenge.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<ApiResponse<String>> addDepartment(@Validated(DepartmentDto.Validation.class) @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = new ApiResponse(departmentService.addDepartment(departmentDto), "success");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/departments/{name}")
    public ResponseEntity<ApiResponse<DepartmentDto>> retrieveDepartment(@PathVariable("name") String name) throws Exception {
        ApiResponse apiResponse = new ApiResponse("success", departmentService.findDepartmentByName(name));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/departments")
    public ResponseEntity<ApiResponse<List<DepartmentDto>>> retrieveDepartments() throws Exception {
        ApiResponse apiResponse = new ApiResponse("success", departmentService.retrieveDepartments());
        return ResponseEntity.ok(apiResponse);
    }

}

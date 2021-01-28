package com.flexisaf.challenge.challenge.service;

import com.flexisaf.challenge.challenge.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    String addDepartment(DepartmentDto departmentDto);

    DepartmentDto findDepartmentByName(String name) throws Exception;

    List<DepartmentDto> retrieveDepartments() throws Exception;
}

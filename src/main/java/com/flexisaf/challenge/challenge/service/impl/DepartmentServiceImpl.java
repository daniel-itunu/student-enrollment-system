package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.dto.DepartmentDto;
import com.flexisaf.challenge.challenge.exception.GenericException;
import com.flexisaf.challenge.challenge.model.Department;
import com.flexisaf.challenge.challenge.repository.DepartmentRepository;
import com.flexisaf.challenge.challenge.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setId(departmentDto.getId());
        Department savedDepartment = departmentRepository.save(department);
        if(savedDepartment == null){
            return "failed to add department";
        }
        return "department added";
    }

    @Override
    public DepartmentDto findDepartmentByName(String name) throws Exception {
        Department department = departmentRepository.findDepartmentByName(name);
        if(department == null){
            throw new GenericException("department not found, add one first");
        }
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
        return departmentDto;
    }

    @Override
    public List<DepartmentDto> retrieveDepartments() throws Exception {
       List<Department> departments = departmentRepository.findAll();
       if(departments.size() == 0){
           throw new GenericException("empty list of department, add a department first");
       }
       List<DepartmentDto> departmentDtos = new ArrayList<>();
        departments.stream().forEach(department -> {
           DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId(department.getId());
            departmentDto.setName(department.getName());
            departmentDto.setCreatedAt(department.getCreatedAt().toString());
            departmentDtos.add(departmentDto);
        });
       return departmentDtos;
    }
}

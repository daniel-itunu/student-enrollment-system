package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.dto.DepartmentDto;
import com.flexisaf.challenge.challenge.model.Department;
import com.flexisaf.challenge.challenge.repository.DepartmentRepository;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.DepartmentService;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    void addDepartment() {
        Department department = new Department();
        department.setName("biology");
        department.setId("bio");
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("biology");
        departmentDto.setId("bio");
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        assertEquals("department added", departmentService.addDepartment(departmentDto));
    }

    @Test
    void findDepartmentByName() throws Exception {
        Department department = new Department();
        department.setName("biology");
        when(departmentRepository.findDepartmentByName("biology")).thenReturn(department);
        assertEquals("biology", departmentService.findDepartmentByName("biology").getName());
    }

    @Test
    void retrieveDepartments() throws Exception {
        Department department = new Department();
        department.setName("biology");
        department.setId("bio");
        when(departmentRepository.findAll()).thenReturn(List.of(department));
        assertEquals(1, departmentService.retrieveDepartments().size());
    }
}
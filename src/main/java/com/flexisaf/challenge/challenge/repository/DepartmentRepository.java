package com.flexisaf.challenge.challenge.repository;

import com.flexisaf.challenge.challenge.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName(String name);
}

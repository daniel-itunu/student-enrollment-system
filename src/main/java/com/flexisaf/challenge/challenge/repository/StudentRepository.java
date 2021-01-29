package com.flexisaf.challenge.challenge.repository;

import com.flexisaf.challenge.challenge.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByMatricNumber(String matricNumber);
}

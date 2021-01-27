package com.flexisaf.challenge.challenge.repository;

import com.flexisaf.challenge.challenge.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student save(Student student);
    List<Student> findAll();
    void deleteStudentByMatricNumber(String matricNumber);
    Student getStudentByMatricNumber(String matricNumber);
}

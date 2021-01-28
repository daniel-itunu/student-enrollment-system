package com.flexisaf.challenge.challenge.repository;

import com.flexisaf.challenge.challenge.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    void deleteStudentByMatricNumber(String matricNumber);
    Student getStudentByMatricNumber(String matricNumber);
    List<Student> getAllByFirstName(String firstName);
    List<Student>  getAllByLastName(String lastName);
    List<Student>  getAllByOtherName(String otherName);
    List<Student>  getAllByGender(String gender);
    List<Student>  getAllByCreatedAt(String createdAt);
    List<Student> getAllByDepartment(String name);
    List<Student> getAllByDepartment__id(long id);

}

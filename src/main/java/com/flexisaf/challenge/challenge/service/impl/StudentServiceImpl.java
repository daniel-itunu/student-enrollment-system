package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.model.Department;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.repository.DepartmentRepository;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String addStudent(StudentDto studentDto) {
        Department department = departmentRepository.findDepartmentByName(studentDto.getDepartment());
        if(department == null){
            return "department not found";
        } else {
            Student student = new Student();
            student.setFirstName(studentDto.getFirstName());
            student.setOtherName(studentDto.getOtherName());
            student.setLastName(studentDto.getLastName());
            student.setDateOfBirth(studentDto.getDateOfBirth());
            student.setGender(studentDto.getGender());
            student.setDepartment(department);
            Integer number;
            Integer size = studentRepository.findAll().size();
            if (size == 0) {
                number = 1;
            } else {
                number = size+1;
            }
            student.setMatricNumber("FLEXISAF/00"+number);
            Student addedStudent = studentRepository.save(student);
            if(addedStudent==null){
                return "failed to add Student";
            }
            return "student added successfully with matric number FLEXISAF/00"+number;
        }
    }

    @Override
    public List<Student> retrieveStudents() {
        return null;
    }

    @Override
    public void deleteStudent(String matricNumber) {

    }

    @Override
    public Student retrieveStudent(String matricNumber) {
        return null;
    }
}

package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.exception.GenericException;
import com.flexisaf.challenge.challenge.model.Department;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.repository.DepartmentRepository;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        if (department == null) {
            throw new GenericException("department not found");
        } else {
            Student student = new Student();
            student.setFirstName(studentDto.getFirstName());
            student.setOtherName(studentDto.getOtherName());
            student.setLastName(studentDto.getLastName());
            student.setDateOfBirth(studentDto.getDateOfBirth());
            student.setGender(studentDto.getGender());
            student.setPhoneNumber(studentDto.getPhoneNumber());
            student.setDepartment(department);

            //age
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(studentDto.getDateOfBirth(), formatter);
            Integer years = Period.between(birthDate, LocalDate.now()).getYears();
            if (years >= 18 && years <= 25) {
                Integer number;
                Integer size = studentRepository.findAll().size();
                if (size == 0) {
                    number = 1;
                } else {
                    number = size + 1;
                }
                student.setMatricNumber("FLEXISAF/00" + number);
                Student addedStudent = studentRepository.save(student);
                if (addedStudent == null) {
                    throw new GenericException("failed to add Student");
                }
                return "student added with matric number FLEXISAF/00" + number;
            }
            throw new GenericException("student should be between age 18 and 25");
        }
    }

    @Override
    public List<StudentDto> retrieveStudents() throws Exception {
        List<Student>  students = studentRepository.findAll();
        if(students.size() == 0){
            throw new GenericException("no student has been registered");
        }
        List<StudentDto> studentDtos = new ArrayList<>();
        students.stream().forEach(student -> {
            StudentDto studentDto = new StudentDto();
            studentDto.setFirstName(student.getFirstName());
            studentDto.setOtherName(student.getOtherName());
            studentDto.setLastName(student.getLastName());
            studentDto.setMatricNumber(student.getMatricNumber());
            studentDto.setDepartment(student.getDepartment().getName());
            studentDto.setDateOfBirth(student.getDateOfBirth());
            studentDto.setGender(student.getGender());
            studentDto.setPhoneNumber(student.getPhoneNumber());
            studentDto.setCreatedAt(student.getCreatedAt().toString());
            studentDtos.add(studentDto);

        });
        return studentDtos;
    }

    @Override
    public String deleteStudent(String matricNumber) {
        Student student = studentRepository.getStudentByMatricNumber("FLEXISAF/"+matricNumber);
        if(student == null){
            throw new GenericException("Student not found");
        }
        studentRepository.delete(student);
        return "student FLEXISAF/"+matricNumber+" deleted";
    }

    @Override
    public StudentDto retrieveStudent(String matricNumber) throws Exception {
        Student student = studentRepository.getStudentByMatricNumber("FLEXISAF/"+matricNumber);
        if(student == null){
            throw new GenericException("Student not found");
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setOtherName(student.getOtherName());
        studentDto.setLastName(student.getLastName());
        studentDto.setMatricNumber(student.getMatricNumber());
        studentDto.setDepartment(student.getDepartment().getName());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setGender(student.getGender());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        studentDto.setCreatedAt(student.getCreatedAt().toString());
        return studentDto;
    }

    @Override
    public String updateStudent(StudentDto studentDto, String matricNumber) {
        Department department = departmentRepository.findDepartmentByName(studentDto.getDepartment());
        if(department == null){
            throw new GenericException("department not found");
        } else{
            Student student = studentRepository.getStudentByMatricNumber("FLEXISAF/"+matricNumber);
            student.setFirstName(studentDto.getFirstName());
            student.setOtherName(studentDto.getOtherName());
            student.setLastName(studentDto.getLastName());
            student.setDepartment(department);
            student.setDateOfBirth(studentDto.getDateOfBirth());
            student.setPhoneNumber(studentDto.getPhoneNumber());
            Student updatedStudent = studentRepository.save(student);
            if(updatedStudent==null){
                throw new GenericException("failed to update Student");
            }
            return "student FLEXISAF/"+matricNumber+" updated";
        }
    }
}

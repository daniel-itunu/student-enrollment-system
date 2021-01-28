package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.model.Department;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.repository.DepartmentRepository;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.StudentSearchService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentSearchServiceImpl implements StudentSearchService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentSearchServiceImpl(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Student> getSearchResult(String input) throws Exception {
        List<Student> students = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        if(!input.toLowerCase().contains(" ")){
            studentList.stream().forEach(student -> {
                if(student.getFirstName().equals(input) || student.getLastName().equals(input) ||
                 student.getDepartment().equals(input) || student.getOtherName().equals(input) || student.getGender().equals(input)){
                    students.add(student);
                }
            });
            if(students.size() == 0){
                throw new Exception("no student found");
            }
            return students;
            //fullname
        } else if(input.toLowerCase().contains(" ") && input.toLowerCase().matches("[a-z A-Z]")){
            String fullNames[] = input.split(" ");
            if(fullNames.length == 2){
               studentList.stream().forEach(student -> {
                   if(student.getFirstName().equals(fullNames[0]) && student.getLastName().equals(fullNames[1])){
                       students.add(student);
                   }
               });
            } else if(fullNames.length == 3){
                studentList.stream().forEach(student -> {
                    if(student.getFirstName().equals(fullNames[0]) && student.getLastName().equals(fullNames[1]) && student.getOtherName().equals(fullNames[2])){
                        students.add(student);
                    }
                });
            }
            if(students.size() == 0){
                throw new Exception("no student found");
            }
            return students;

        } else if(input.toLowerCase().contains("-") ){
            String dates[] = input.split(" to ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime start = LocalDateTime.parse(dates[0], formatter);
            LocalDateTime end = LocalDateTime.parse(dates[1], formatter);

            studentList.stream().forEach(student -> {
                if((start.isEqual(student.getCreatedAt()) || start.isBefore(student.getCreatedAt())) && (end.isEqual(student.getCreatedAt()) || end.isAfter(student.getCreatedAt()))){
                    students.add(student);
                }
            });

            if(students.size() == 0){
                throw new Exception("no student found");
            }
            return students;

        } else {
            throw new Exception("no student found");
        }
    }

    @Override
    public List<Student> getAllByDepartment(String input) throws Exception {
        Department department = departmentRepository.findDepartmentByName(input);
        if(department != null) {
            List<Student> students = studentRepository.getAllByDepartment__id(department.get_id());
            if (students.size() != 0) {
                return students;
            }
        }
        return List.of();
    }

    @Override
    public List<Student> getAllByFirstName(String firstName) throws Exception {
        List<Student> students = studentRepository.getAllByFirstName(firstName);
        if(students.size() != 0){
            return students;
        }
        return List.of();
    }

    @Override
    public List<Student> getAllByLastName(String lastName) throws Exception {
        List<Student> students = studentRepository.getAllByLastName(lastName);
        if(students.size() != 0){
            return students;
        }
        return List.of();
    }

    @Override
    public List<Student> getAllByOtherName(String otherName) throws Exception {
        List<Student> students = studentRepository.getAllByOtherName(otherName);
        if(students.size() != 0){
            return students;
        }
        return List.of();
    }

    @Override
    public List<Student> getAllByGender(String gender) throws Exception {
        List<Student> students = studentRepository.getAllByGender(gender);
        if(students.size() != 0){
            return students;
        }
        return List.of();
    }

    @Override
    public List<Student> getAllByCreatedAt(String createdAt) throws Exception {
        List<Student> students = studentRepository.getAllByCreatedAt(createdAt);
        if(students.size() != 0){
            return students;
        }
        return List.of();
    }
}

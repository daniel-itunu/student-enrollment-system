package com.flexisaf.challenge.challenge.service.impl;

import com.flexisaf.challenge.challenge.exception.GenericException;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.repository.StudentRepository;
import com.flexisaf.challenge.challenge.service.StudentSearchService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSearchServiceImpl implements StudentSearchService {
    private final StudentRepository studentRepository;

    public StudentSearchServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getSearchResult(String input) {
        List<Student> students = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        String in = input.toLowerCase();
        if(!input.toLowerCase().contains(" ")){
            studentList.stream().forEach(student -> {
                if(student.getFirstName().toLowerCase().equals(in) || student.getLastName().toLowerCase().equals(in) ||
                 student.getDepartment().getName().toLowerCase().equals(in) || student.getOtherName().toLowerCase().equals(in) || student.getGender().toLowerCase().equals(in)){
                    students.add(student);
                }
            });
            if(students.size() == 0){
                throw new GenericException("no student found");
            }
            return students;

        } else if(input.toLowerCase().contains(" ") && input.toLowerCase().matches("[a-z A-Z]")){
            String fullNames[] = in.split(" ");
            if(fullNames.length == 2){
               studentList.stream().forEach(student -> {
                   if(student.getFirstName().toLowerCase().equals(fullNames[0]) && student.getLastName().toLowerCase().equals(fullNames[1])){
                       students.add(student);
                   }
               });
            } else if(fullNames.length == 3){
                studentList.stream().forEach(student -> {
                    if(student.getFirstName().toLowerCase().equals(fullNames[0]) && student.getLastName().toLowerCase().equals(fullNames[1]) && student.getOtherName().toLowerCase().equals(fullNames[2])){
                        students.add(student);
                    }
                });
            }
            if(students.size() == 0){
                throw new GenericException("no student found");
            }
            return students;

        } else if(input.toLowerCase().contains("-") ){
            String dates[] = in.split(" to ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime start = LocalDateTime.parse(dates[0], formatter);
            LocalDateTime end = LocalDateTime.parse(dates[1], formatter);

            studentList.stream().forEach(student -> {
                if((start.isEqual(student.getCreatedAt()) || start.isBefore(student.getCreatedAt())) && (end.isEqual(student.getCreatedAt()) || end.isAfter(student.getCreatedAt()))){
                    students.add(student);
                }
            });

            if(students.size() == 0){
                throw new GenericException("no student found");
            }

            return students;

        } else {
            throw new GenericException("no student found");
        }
    }
}

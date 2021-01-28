package com.flexisaf.challenge.challenge.service;

import com.flexisaf.challenge.challenge.model.Student;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StudentSearchService {
    List<Student> getAllByFirstName(String firstName) throws Exception;
    List<Student>  getAllByLastName(String lastName) throws Exception;
    List<Student>  getAllByOtherName(String otherName) throws Exception;
    List<Student>  getAllByGender(String gender) throws Exception;
    List<Student>  getAllByCreatedAt(String createdAt) throws Exception;
    List<Student> getSearchResult(String input) throws Exception;
    List<Student> getAllByDepartment(String input) throws Exception;
}

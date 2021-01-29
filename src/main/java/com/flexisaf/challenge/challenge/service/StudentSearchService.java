package com.flexisaf.challenge.challenge.service;

import com.flexisaf.challenge.challenge.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentSearchService {
    List<Student> getSearchResult(String input) throws Exception;
}

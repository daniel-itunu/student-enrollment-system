package com.flexisaf.challenge.challenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StudentDto {
    private String firstName;

    private String lastName;

    private String otherName;

    private String gender;

    private String dateOfBirth;

    private String department;

    private String matricNumber;

}

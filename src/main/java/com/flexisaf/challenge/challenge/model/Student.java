package com.flexisaf.challenge.challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_name")
    private String otherName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "department")
    private String department;


    @Column(name = "matric_number")
    private String matricNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}

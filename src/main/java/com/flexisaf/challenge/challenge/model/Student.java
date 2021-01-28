package com.flexisaf.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "Student")
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name="incrementor", strategy="increment")
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

    @JsonIgnore
    @ManyToOne
    private Department department;

    @Column(name = "matric_number")
    private String matricNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}

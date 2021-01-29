package com.flexisaf.challenge.challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "department")
@Entity(name = "Department")
public class Department {
    @Id
    @GeneratedValue
    private long _id;

    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Student> students;
}

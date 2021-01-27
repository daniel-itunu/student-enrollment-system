package com.flexisaf.challenge.challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Table(name = "department")
@Entity(name = "Department")
public class Department {
    @Id
    @GeneratedValue
    private long _id;

    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Student> students;
}

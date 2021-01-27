package com.flexisaf.challenge.challenge.dto;

import com.flexisaf.challenge.challenge.model.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private String name;
}

package com.flexisaf.challenge.challenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class StudentDto {
    @NotBlank(message = "first name cannot be blank", groups=StudentDto.Validation.class)
    @Size(min = 1, max = 50, message = "first name should have a min of 2 characters and max of 50 characters", groups=StudentDto.Validation.class)
    private String firstName;

    @NotBlank(message = "last name cannot be blank", groups=StudentDto.Validation.class)
    @Size(min = 1, max = 50, message = "last name should have a min of 2 characters and max of 50 characters", groups=StudentDto.Validation.class)
    private String lastName;

    @NotBlank(message = "other name cannot be blank", groups=StudentDto.Validation.class)
    @Size(min = 1, max = 50, message = "other name should have a min of 2 characters and max of 50 characters", groups=StudentDto.Validation.class)
    private String otherName;

    @NotBlank(message = "gender cannot be blank", groups=StudentDto.Validation.class)
    @Size(min = 1, max = 1, message = "gender should be M or F", groups=StudentDto.Validation.class)
    private String gender;

    @NotBlank(message = "date of birth cannot be blank", groups=StudentDto.Validation.class)
    @Size(min = 10, max = 10, message = "date of birth should have a min and max of 10 characters, yyyy-mm-dd", groups=StudentDto.Validation.class)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date of birth pattern should be yyyy-mm-dd", groups=StudentDto.Validation.class)
    private String dateOfBirth;


    @NotBlank(message = "department cannot be blank", groups=StudentDto.Validation.class)
    @Size(min = 2, max = 50, message = "location should have a minimum of 2 characters", groups=StudentDto.Validation.class)
    private String department;

    private String matricNumber;

    @NotBlank(message = "phone1 cannot be blank", groups=StudentDto.Validation.class)
    @Size(max = 14, message = "phone1 should have a maximum of 14 characters", groups=StudentDto.Validation.class)
    @Pattern(regexp = "^[+]234[0-9]{10}", message = "phone1 pattern is invalid", groups=StudentDto.Validation.class)
    private String phoneNumber;


    private String createdAt;

    public interface Validation{}
}

package com.flexisaf.challenge.challenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class DepartmentDto {

    @NotBlank(message = "name cannot be blank", groups=DepartmentDto.Validation.class)
    @Size(min = 1, max = 50, message = "name should have a min of 1 characters and max of 50 characters", groups=DepartmentDto.Validation.class)
    private String name;

    @NotBlank(message = "id cannot be blank", groups=DepartmentDto.Validation.class)
    @Size(min = 1, max = 5, message = "id should have a min of 1 characters and max of 5 characters", groups=DepartmentDto.Validation.class)
    private String id;

    private String createdAt;

    public interface Validation{}
}

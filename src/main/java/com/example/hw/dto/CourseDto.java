package com.example.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDto {

    @NotBlank(message = "The course title cannot be blank")
    @Size(min = 2, message = "The course title should be equal to 2 characters or greater")
    private String courseName;

    @NotNull(message = "Course price cannot be null")
    private Integer coursePrice;
}

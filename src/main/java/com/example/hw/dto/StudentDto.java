package com.example.hw.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentDto {

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(value = 5, message = "Age should be between 5 and 150")
    @Max(value = 150, message = "Age should be between 5 and 150")
    private Integer age;
}

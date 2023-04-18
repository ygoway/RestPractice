package com.example.hw.service;

import com.example.hw.dto.StudentDto;
import com.example.hw.repository.entity.Student;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);

    List<StudentDto> getAllStudents();

    void deleteStudentById(Long id);
}

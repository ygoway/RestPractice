package com.example.hw.service;

import com.example.hw.dto.StudentDto;
import com.example.hw.repository.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(StudentDto studentDto);

    Student getStudentById(Long id);

    List<Student> getAllStudents();

    void deleteStudentById(Long id);
}

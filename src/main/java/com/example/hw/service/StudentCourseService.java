package com.example.hw.service;

import com.example.hw.dto.CourseDto;
import com.example.hw.repository.entity.Student;

import java.util.List;

public interface StudentCourseService {

    Student addCourseToStudent(Long studentId, Long courseId);

    List<CourseDto> getStudentCourseList(Long id);

    void deleteStudentFromCourse(Long studentId, Long courseId);
}

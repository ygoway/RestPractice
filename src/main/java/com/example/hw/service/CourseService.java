package com.example.hw.service;

import com.example.hw.dto.CourseDto;
import com.example.hw.repository.entity.Course;

import java.util.List;

public interface CourseService {

    Course createCourse (CourseDto courseDto);

    Course getCourseById(Long id);

    List<Course> getAllCourses();

    void deleteCourseById (Long id);
}

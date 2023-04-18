package com.example.hw.service;

import com.example.hw.dto.CourseDto;
import com.example.hw.repository.entity.Course;

import java.util.List;

public interface CourseService {

    CourseDto createCourse (CourseDto courseDto);

    CourseDto getCourseById(Long id);

    List<CourseDto> getAllCourses();

    void deleteCourseById (Long id);
}

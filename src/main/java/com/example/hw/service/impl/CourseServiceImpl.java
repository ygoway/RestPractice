package com.example.hw.service.impl;

import com.example.hw.dto.CourseDto;
import com.example.hw.repository.CourseRepository;
import com.example.hw.repository.entity.Course;
import com.example.hw.service.CourseService;
import com.example.hw.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    @Override
    public Course createCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with id : " + id + " not found"));
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    @Override
    public void deleteCourseById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new NotFoundException("Course with id : " + id + " not found");
        }
    }
}

package com.example.hw.service.impl;

import com.example.hw.dto.CourseDto;
import com.example.hw.repository.CourseRepository;
import com.example.hw.repository.entity.Course;
import com.example.hw.service.CourseService;
import com.example.hw.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        courseRepository.save(course);
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with id : " + id + " not found"));
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return StreamSupport.stream(courseRepository.findAll().spliterator(), false)
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
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

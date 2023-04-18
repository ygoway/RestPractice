package com.example.hw.web;

import com.example.hw.dto.CourseDto;
import com.example.hw.service.CourseService;
import com.example.hw.service.DtoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final DtoValidation dtoValidation;

    @PostMapping("/create")
    public ResponseEntity createCourse(@RequestBody @Validated CourseDto courseDto,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dtoValidation.inputDtoValidation(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDto));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity getCourseById(@PathVariable Long courseId) {
        CourseDto course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity deleteCourseByID(@PathVariable Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok("Student with id : " + courseId + " delete successful");
    }
}

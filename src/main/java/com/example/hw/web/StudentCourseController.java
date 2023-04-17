package com.example.hw.web;

import com.example.hw.service.StudentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/studentCourse")
@RequiredArgsConstructor
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    @PostMapping("/student/{studentId}/addCourse/{courseId}")
    public ResponseEntity addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentCourseService.addCourseToStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body("student with id : "
                + studentId + " added to course with id : " + courseId + " successful");
    }

    @GetMapping("/studentCourseList/{studentId}")
    public ResponseEntity getStudentCourseList(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentCourseService.getStudentCourseList(studentId));
    }

    @DeleteMapping("/deleteStudent/{studentId}/fromCourse/{courseId}")
    public ResponseEntity deleteStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentCourseService.deleteStudentFromCourse(studentId, courseId);
        return ResponseEntity.ok("Student with id : " + studentId +
                " delete successful from course with id : " + courseId);
    }
}

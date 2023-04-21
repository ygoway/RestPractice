package com.example.hw.web;

import com.example.hw.dto.StudentDto;
import com.example.hw.service.DtoValidation;
import com.example.hw.service.StudentCourseService;
import com.example.hw.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private final StudentCourseService studentCourseService;

    private final DtoValidation dtoValidation;

    @PostMapping("/create")
    public ResponseEntity createStudent(@RequestBody @Validated StudentDto studentDto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dtoValidation.inputDtoValidation(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDto));
    }

    @PostMapping("/student/{studentId}/addCourse/{courseId}")
    public ResponseEntity addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentCourseService.addCourseToStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body("student with id : "
                + studentId + " added to course with id : " + courseId + " successful");
    }

    @GetMapping("/{studentId}")
    public ResponseEntity getStudentById(@PathVariable Long studentId) {
        StudentDto student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/studentCourseList/{studentId}")
    public ResponseEntity getStudentCourseList(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentCourseService.getStudentCourseList(studentId));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudentByID(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Student with id : " + studentId + " delete successful");
    }

    @DeleteMapping("/deleteStudent/{studentId}/fromCourse/{courseId}")
    public ResponseEntity deleteStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentCourseService.deleteStudentFromCourse(studentId, courseId);
        return ResponseEntity.ok("Student with id : " + studentId +
                " delete successful from course with id : " + courseId);
    }

}

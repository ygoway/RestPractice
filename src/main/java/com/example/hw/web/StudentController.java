package com.example.hw.web;

import com.example.hw.dto.StudentDto;
import com.example.hw.repository.entity.Student;
import com.example.hw.service.DtoValidation;
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

    private final DtoValidation dtoValidation;

    @PostMapping("/create")
    public ResponseEntity createStudent(@RequestBody @Validated StudentDto studentDto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dtoValidation.inputDtoValidation(bindingResult));
        }
        studentService.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudentByID(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Student with id : " + studentId + " delete successful");
    }
}

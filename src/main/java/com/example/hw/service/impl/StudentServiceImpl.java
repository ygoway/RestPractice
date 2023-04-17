package com.example.hw.service.impl;

import com.example.hw.dto.StudentDto;
import com.example.hw.repository.StudentRepository;
import com.example.hw.repository.entity.Student;
import com.example.hw.service.StudentService;
import com.example.hw.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id : " + id + " not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    @Override
    public void deleteStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Student with id : " + id + " not found");
        }
    }

}

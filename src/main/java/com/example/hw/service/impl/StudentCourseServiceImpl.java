package com.example.hw.service.impl;

import com.example.hw.dto.CourseDto;
import com.example.hw.repository.CourseRepository;
import com.example.hw.repository.StudentRepository;
import com.example.hw.repository.entity.Course;
import com.example.hw.repository.entity.Student;
import com.example.hw.service.StudentCourseService;
import com.example.hw.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    @Override
    public Student addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with id :" + studentId + " not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with id :" + courseId + " not found"));
        List<Course> courses = student.getCourses();
        if (!courses.contains(course)) {
            courses.add(course);
            student.setCourses(courses);
            return studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student is studying a course");
        }
    }

    @Override
    public List<CourseDto> getStudentCourseList(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with id :" + studentId + " not found"));
        return student.getCourses()
                .stream().map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    public void deleteStudentFromCourse(Long studentId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with id :" + courseId + " not found"));
        Student student = course.getStudents().stream()
                .filter(student1 -> student1.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student with id :" + studentId + " not joined to course : " + course));
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        courseRepository.save(course);
    }

    /*@Override
    public void deleteStudentFromCourse1(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with id :"  + studentId + " not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with id :"  + courseId + " not found"));
        if (course.getStudents().removeIf(foundStudent -> foundStudent.getId().equals(studentId))) {
            student.getCourses().removeIf(foundCourse -> foundCourse.getId().equals(courseId));
            studentRepository.save(student);
        } else {
            throw new NotFoundException("Student with id :"  + studentId + " not joined to course : " + course);
        }
    }*/

}

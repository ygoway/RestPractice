package com.example.hw.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String courseName;
    private Integer coursePrice;

    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<Student> students;
}

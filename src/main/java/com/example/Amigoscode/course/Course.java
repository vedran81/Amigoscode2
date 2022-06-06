package com.example.Amigoscode.course;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @SequenceGenerator(name = "course_sequence",  allocationSize = 1)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_year")
    private Integer courseYear;

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course(String courseName, Integer courseYear) {
        this.courseName = courseName;
        this.courseYear = courseYear;
    }

    public Course() {
    }
}
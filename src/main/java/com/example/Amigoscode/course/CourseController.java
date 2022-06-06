package com.example.Amigoscode.course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired //govori da private final studentService treba biti "autowired" u konstruktor
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public List<Course> getStudents() {
        return courseService.getAll();
    }

    @GetMapping(path = "year/{year}")
    public List<Course> findByYear(int year) {
        return courseService.findAllByYear(year);
    }

    @GetMapping(path = "name/{name}")
    public List<Course> findByName(String name) {
        return courseService.findAllByName(name);
    }

    @DeleteMapping(path = "delete/{courseId}")
    public void deleteCourse(Long courseId) {
        courseService.deleteCourse(courseId);
    }

    @PostMapping(path = "add/{course}")
    public void findByName(Course course) {
        courseService.addNewCourse(course);
    }
};

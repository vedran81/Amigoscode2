package com.example.Amigoscode.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAllByYear(int year) {
        return courseRepository.findByCourseYear(year);
    }

    public List<Course> findAllByName(String name) {
        return courseRepository.findByCourseName(name);
    }

    public void addNewCourse(Course course) {

        if (courseRepository.existsByCourseNameAndCourseYear(course.getCourseName(), course.getCourseYear())) {
            throw new IllegalStateException("course already exists");
        }

        courseRepository.save(course);
    }

    public void deleteCourse(Long studentId) {
        boolean exists = courseRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("Course with id " + studentId + " does not exists");
        }
        courseRepository.deleteById(studentId);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }





}

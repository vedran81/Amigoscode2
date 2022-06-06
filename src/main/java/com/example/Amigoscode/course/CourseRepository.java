package com.example.Amigoscode.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCourseNameAndCourseYear(String courseName, Integer courseYear);

    List<Course> findByCourseName(String courseName);

    List<Course> findByCourseYear(Integer courseYear);

    long countByCourseYear(Integer courseYear);

    Optional<Course> findByCourseNameAndCourseYear(String courseName, Integer courseYear);
}
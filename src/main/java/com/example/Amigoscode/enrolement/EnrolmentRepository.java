package com.example.Amigoscode.enrolement;

import com.example.Amigoscode.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {
    @Transactional
    @Modifying
    @Query("update Enrolment e set e.student = ?1, e.grade = ?2 where e.id = ?3")
    void updateStudentAndGradeById(Student student, Integer grade, Long id);



}
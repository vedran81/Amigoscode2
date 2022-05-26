package com.Amigoscode.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    List<Student> findByMentor_Id(Long id);

    Student findByEmail(String email);

    boolean existsByEmail(String email);



    @Query("select s from Student s where s.studyYear = ?1")
    List<Student> findByStudyYear(Integer studyYear);

    boolean existsById(Long id);

    Student findStudentById(Long id);
}

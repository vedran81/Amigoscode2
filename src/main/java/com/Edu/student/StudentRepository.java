package com.Edu.student;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    List<Student> findByMentor_Id(Long id);

    Student findByEmail(String email);

    @Override
    @NotNull
    Optional<Student> findById(@NotNull Long aLong);

    boolean existsByEmail(String email);


    @Query("select s from Student s where s.studyYear = ?1")
    List<Student> findByStudyYear(Integer studyYear);

    boolean existsById(@NotNull Long id);

    Student findStudentById(Long id);

    List<Student> findByMentorIsNotNull();


}

package com.Edu.enrolment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Long>, JpaSpecificationExecutor<Enrolment> {

    boolean existsByStudent_IdAndSubject_Id(Long studentId, Long subjectId);

    List<Enrolment> findByStudent_Id(Long id);


    Enrolment findByStudent_IdAndSubject_Id(Long studentId, Long subjectId);


    Enrolment findEnrolmentById(Long enrId);
}
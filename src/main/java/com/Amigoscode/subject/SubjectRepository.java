package com.Amigoscode.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> , JpaSpecificationExecutor<Subject> {

    List<Subject> findByName(String name);

    List<Subject> findByYear(Integer year);

    long countByYear(Integer year);

    List<Subject> findByNameAndYear(String name, Integer year);

    boolean existsByNameAndYear(String name, Integer year);

    boolean existsById(Long id);

    Subject findSubjectById(Long id);
}
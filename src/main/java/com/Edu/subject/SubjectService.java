package com.Edu.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAllByYear(int year) {
        return subjectRepository.findByYear(year);
    }

    public List<Subject> findAllByName(String name) {
        return subjectRepository.findByName(name);
    }

    public void addNewSubject(Subject subject) {

        if (subjectRepository.existsByNameAndYear(subject.getName(), subject.getYear())) {
            throw new IllegalStateException("subject already exists");
        }

        subjectRepository.save(subject);
    }

    public void deleteSubject(Long studentId) {
        boolean exists = subjectRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("subject with id " + studentId + " does not exists");
        }
        subjectRepository.deleteById(studentId);
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }





}

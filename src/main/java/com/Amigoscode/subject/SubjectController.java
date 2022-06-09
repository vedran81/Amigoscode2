package com.Amigoscode.subject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired //govori da private final studentService treba biti "autowired" u konstruktor
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectService.getAll();
    }

    @GetMapping(path = "by_year/{year}")
    public List<Subject> findByYear(@PathVariable Integer year) {
        return subjectService.findAllByYear(year);
    }

    @GetMapping(path = "by_name/{name}")
    public List<Subject> findByName(@PathVariable String name) {
        return subjectService.findAllByName(name);
    }

    @DeleteMapping(path = "{subjectId}/delete")
    public void deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }

    @PostMapping(path = "add/{name}/{year}")
    public void addSubject(@PathVariable String name, @PathVariable Integer year) {

        subjectService.addNewSubject(new Subject(name, year));
    }
};

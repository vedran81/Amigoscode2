package com.example.Amigoscode.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired //govori da private final studentService treba biti "autowired" u konstruktor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "with_mentor/{mentorId}")
    public List<Student> findByMentorId(Long Id) {
        return studentService.findByMentorId(Id);
    }

    @PostMapping(path = "assign_mentor/{studentId}/{mentorId}")
    public void assignMentor(@PathVariable Long studentId, @PathVariable Long mentorId) {
        studentService.assignMentor(studentId, mentorId);
    }

    @PostMapping(path = "add")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "edit/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentUpdateRequest studentUpdateRequest) {
        studentService.updateStudent(studentId, studentUpdateRequest);


    }
}


package com.Edu.enrolment;


import com.Edu.student.Student;
import com.Edu.subject.Subject;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "enrolment")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Enrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrolment_sequence")
    @SequenceGenerator(name = "enrolment_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    //@JsonManagedReference(value = "student-ref")
    private Student student;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id")
    //@JsonManagedReference(value = "subject-ref")
    private Subject subject;

    private Integer grade;


    private LocalDateTime enrolTimeStamp;


    private LocalDateTime gradeTimeStamp;


    public Enrolment() {
    }

    public boolean isCourseFinished() {
        return (grade != null);
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
        this.gradeTimeStamp = now();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getEnrolTimeStamp() {
        return enrolTimeStamp;
    }

    public void setEnrolTimeStamp(LocalDateTime enrolTimeStamp) {
        this.enrolTimeStamp = enrolTimeStamp;
    }

    public LocalDateTime getGradeTimeStamp() {
        return gradeTimeStamp;
    }

    public void setGradeTimeStamp(LocalDateTime gradeTimeStamp) {
        this.gradeTimeStamp = gradeTimeStamp;
    }
}
package com.Amigoscode.enrolment;


import com.Amigoscode.student.Student;
import com.Amigoscode.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "enrolment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Enrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrolment_sequence")
    @SequenceGenerator(name = "enrolment_sequence",  allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonManagedReference(value = "student-ref")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonManagedReference(value = "subject-ref")
    private Subject subject;

    private Integer grade;

    public Enrolment() {
    }

    public boolean isCourseFinished() {return ( grade != null);}

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
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

}
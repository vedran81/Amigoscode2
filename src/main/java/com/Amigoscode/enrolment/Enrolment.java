package com.Amigoscode.enrolment;


import com.Amigoscode.student.Student;
import com.Amigoscode.subject.Subject;

import javax.persistence.*;
import java.time.LocalDate;

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

    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    //@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    //@JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate enrolTimeStamp;

    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    //@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    //@JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate gradeTimeStamp;


    public Enrolment() {
    }

    public boolean isCourseFinished() {
        return (grade != null);
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
        this.gradeTimeStamp = LocalDate.now();
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

    public LocalDate getEnrolTimeStamp() {
        return enrolTimeStamp;
    }

    public void setEnrolTimeStamp(LocalDate enrolTimeStamp) {
        this.enrolTimeStamp = enrolTimeStamp;
    }

    public LocalDate getGradeTimeStamp() {
        return gradeTimeStamp;
    }

    public void setGradeTimeStamp(LocalDate gradeTimeStamp) {
        this.gradeTimeStamp = gradeTimeStamp;
    }
}
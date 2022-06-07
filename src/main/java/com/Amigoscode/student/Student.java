package com.Amigoscode.student;

import com.Amigoscode.enrolment.Enrolment;
import com.Amigoscode.mentor.Mentor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity //mapira klasu u bazu
@Table(name = "student")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    @Transient //označava da ovo polje ne mora biti stupac u bazi
    private Integer age;
    private String status;
    private Integer studyYear;


    public Set<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public void setEnrolmentList(Set<Enrolment> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }

    @OneToMany(mappedBy = "student")
    @JsonBackReference(value = "srtdent-ref")
    private Set<Enrolment> enrolmentList;

    public Student() {
    }


    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth, String status, Integer studyYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.studyYear = studyYear;
    }


    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonManagedReference(value = "mentor-ref")

    private Mentor mentor;

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

   /* @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", studentStatus='" + status + '\'' +
                ", yearOfEnrollment=" + studyYear +
                '}';
    }*/
}
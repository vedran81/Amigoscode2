package com.Amigoscode.student;

import com.Amigoscode.enrolment.Enrolment;
import com.Amigoscode.mentor.Mentor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

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


    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    Set<Enrolment> enrolments;


    public Set<Enrolment> getEnrolment() {
        return enrolments;
    }
    public void setEnrolment(Set<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }


    public Student(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, Integer age, String status, Integer studyYear, Set<Enrolment> enrolments, @Nullable Mentor mentor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.status = status;
        this.studyYear = studyYear;
        this.enrolments = enrolments;
        this.mentor = mentor;
    }

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @Nullable
    private Mentor mentor;

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }


    public Student() {
        this.status = "";
        this.mentor = null;
    }

    public Student(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, String status, Integer studyYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.studyYear = studyYear;
    }

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth, String status, Integer studyYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.studyYear = studyYear;
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

    @Override
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
    }
}
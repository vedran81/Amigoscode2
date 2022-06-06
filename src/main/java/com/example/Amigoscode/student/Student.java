package com.example.Amigoscode.student;

import com.example.Amigoscode.mentor.Mentor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity //mapira klasu u bazu
@Table(name = "Student")
@NamedQueries({
        @NamedQuery(name = "Student.findByMentor", query = "select s from Student s where s.mentor = ?1")
})
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
    private String studentStatus;
    private Integer yearOfEnrollment;


    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Student() {
    }



    public Student(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, String studentStatus, Integer yearOfEnrollment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.studentStatus = studentStatus;
        this.yearOfEnrollment = yearOfEnrollment;
    }

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth, String studentStatus, Integer yearOfEnrollment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.studentStatus = studentStatus;
        this.yearOfEnrollment = yearOfEnrollment;
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

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Integer getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public void setYearOfEnrollment(Integer yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
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
                ", studentStatus='" + studentStatus + '\'' +
                ", yearOfEnrollment=" + yearOfEnrollment +
                '}';
    }


}
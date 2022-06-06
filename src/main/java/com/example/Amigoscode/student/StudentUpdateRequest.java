package com.example.Amigoscode.student;

import java.time.LocalDate;

public class StudentUpdateRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Integer age;
    private String studentStatus;
    private Integer yearOfEnrollment;

    public StudentUpdateRequest(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, Integer age, String studentStatus, Integer yearOfEnrollment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
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
        return age;
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
}

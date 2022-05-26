package com.Amigoscode.subject;

import com.Amigoscode.enrolment.Enrolment;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
    @SequenceGenerator(name = "subject_sequence", allocationSize = 1)
    private Long id;


    private String name;

    private Integer year;

    public Set<Enrolment> getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(Set<Enrolment> enrolment) {
        this.enrolment = enrolment;
    }

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    Set<Enrolment> enrolment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public Subject() {
    }

    public Subject(Long id, String name, Integer year, Set<Enrolment> enrolments) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.enrolment = enrolments;
    }
}
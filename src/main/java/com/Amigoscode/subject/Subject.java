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

    public Set<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(Set<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    Set<Enrolment> enrolments;

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
}
package com.Amigoscode.subject;

import com.Amigoscode.enrolment.Enrolment;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
    @SequenceGenerator(name = "subject_sequence",  allocationSize = 1)
    private Long id;



    private String name;

    private Integer year;

    public Set<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public void setEnrolmentList(Set<Enrolment> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }

    @OneToMany(mappedBy = "subject")
    Set<Enrolment> enrolmentList;

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
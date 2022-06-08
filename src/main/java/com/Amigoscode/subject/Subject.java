package com.Amigoscode.subject;

import com.Amigoscode.enrolment.Enrolment;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subject")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
    @SequenceGenerator(name = "subject_sequence", allocationSize = 1)
    private Long id;

    private String name;

    private Integer year;

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

    public Subject(Long id, String name, Integer year, List<Enrolment> enrolmentList) {
        this.id = id;
        this.name = name;
        this.year = year;
        //this.enrolmentList = enrolmentList;
    }
}
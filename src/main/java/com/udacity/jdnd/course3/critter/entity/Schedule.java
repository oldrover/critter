package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    private List<Employee> employees;

    private List<Pet> pets;

    private LocalDate date;
    
    @Column
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> activities;
}

package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets;

    private LocalDate date;
    
    @Column
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> activities;

}

package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    private List<Long> employeeIds;

    private List<Long> petIds;

    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;
}

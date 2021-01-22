package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Setter
@Getter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Nationalized
    private String name;

    @Column
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> skills;

    @Column
    @ElementCollection(targetClass = DayOfWeek.class)
    private Set<DayOfWeek> daysAvailable;

}

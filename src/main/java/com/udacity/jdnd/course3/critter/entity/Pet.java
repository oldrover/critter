package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private long id;

    private PetType type;

    @Nationalized
    private String name;

    private long ownerId;

    private LocalDate birthDate;

    private String notes;


}

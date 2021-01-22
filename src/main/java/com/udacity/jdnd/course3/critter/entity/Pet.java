package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Nationalized
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private LocalDate birthDate;

    private String notes;

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

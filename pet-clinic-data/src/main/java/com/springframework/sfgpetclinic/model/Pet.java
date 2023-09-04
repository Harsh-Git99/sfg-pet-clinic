package com.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="pets")
public class Pet extends BaseEntity {

    @Column(name="name")
    private String petName;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private  Owner owner;
    @Column(name="birth_Date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visit = new HashSet<>();
}

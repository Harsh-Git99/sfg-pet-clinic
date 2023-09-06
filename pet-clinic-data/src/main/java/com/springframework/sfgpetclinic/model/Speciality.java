package com.springframework.sfgpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name="specialities")
public class Speciality extends BaseEntity{

    @Builder
    public Speciality(Long id, String description) {
        super(id);
        this.description = description;
    }


    @Column(name = "description")
    private String description;


}

package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServicesTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaServices service;

    final Long petId = 1L;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(petId).build();
    }

    @Test
    void findAll() {
        Set<Pet> petSet = new HashSet<>();

        petSet.add(Pet.builder().id(petId).build());
        petSet.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(petSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2,pets.size());

    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet1 = service.findById(petId);

        assertNotNull(pet1);

    }

    @Test
    void save() {
        Pet pet = Pet.builder().id(petId).build();
        when(petRepository.save(any())).thenReturn(returnPet);
        Pet savedPet = service.save(pet);
        assertNotNull(savedPet);
        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPet);
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(petId);
        verify(petRepository).deleteById(anyLong());
    }
}
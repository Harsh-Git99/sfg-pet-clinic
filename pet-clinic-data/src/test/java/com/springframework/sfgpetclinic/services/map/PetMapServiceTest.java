package com.springframework.sfgpetclinic.services.map;

import com.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    PetMapService petMapService;

    final Long petId = 1L;
    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());

    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();
        assertEquals(1,petSet.size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);

        assertEquals(1,pet.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Pet pet2 = Pet.builder().id(id).build();

        Pet savePet = petMapService.save(pet2);

        assertEquals(id,pet2.getId());

    }

    @Test
    void saveNoId() {
        Pet savePet = petMapService.save(Pet.builder().build());
        assertNotNull(savePet);
    }



    @Test
    void deleteById() {

         petMapService.deleteById(petId);
         assertEquals(0,petMapService.findAll().size());

    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0,petMapService.findAll().size());
    }

}
package com.springframework.sfgpetclinic.services.map;

import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;

    final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();

        petTypeMapService.save(PetType.builder().id(petTypeId).build());

    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = petTypeMapService.findAll();
        assertEquals(1,petTypeSet.size());
    }

    @Test
    void findById() {
        PetType pet = petTypeMapService.findById(petTypeId);
        assertEquals(petTypeId, pet.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        PetType petType2 = PetType.builder().id(id).build();
        petTypeMapService.save(petType2);
        assertEquals(id,petType2.getId());
    }

    @Test
    void saveNoId() {

        PetType petType2 = petTypeMapService.save(PetType.builder().build());

        assertNotNull(petType2);
        assertNotNull(petType2.getId());
    }
    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));

        assertEquals(0,petTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {

        petTypeMapService.deleteById(petTypeId);
        assertEquals(0,petTypeMapService.findAll().size());

    }


}
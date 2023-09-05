package com.springframework.sfgpetclinic.services.map;

import com.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";
    @BeforeEach
    public void setUp(){
        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());

    }
    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1,ownerSet.size());
    }

    @Test
    void FindById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(1,owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        Owner saveOwner = ownerMapService.save(owner2);

        assertEquals(id,owner2.getId());
    }
    @Test
    void saveNoId(){
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void Delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void DeleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0,ownerMapService.findAll().size());
    }



    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(ownerId,smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerMapService.findByLastName("foo");

        assertNull(smith);

    }


//    @Test
//    void findAll() {
//        Set<Owner> ownerSet = ownerMapService.findAll();
//
//        assertEquals(1,ownerSet.size());
//
//    }

//    @Test
//    void saveExistingId() {
//        Long id = 2L;
//        Owner owner2 = Owner.builder().id(id).build();
//        Owner savedOwner = ownerMapService.save(owner2);
//
//        assertEquals(id,savedOwner.getId());
//    }
//
//    @Test
//    void saveNoId(){
//        Owner savedOwner = ownerMapService.save(Owner.builder().build());
//
//        assertNotNull(savedOwner);
//        assertNotNull(savedOwner.getId());
//    }
//
//    @Test
//    void findById() {
//        Owner owner = ownerMapService.findById(ownerId);
//        assertEquals(ownerId,owner.getId());
//    }
//
//    @Test
//    void deleteById() {
//        ownerMapService.deleteById(ownerId);
//        assertEquals(0,ownerMapService.findAll().size());
//    }
//
//    @Test
//    void delete() {
//        ownerMapService.delete(ownerMapService.findById(ownerId));
//        assertEquals(0,ownerMapService.findAll().size());
//    }
//
//    @Test
//    void findByLastName() {
//        Owner smith = ownerMapService.findByLastName(lastName);
//        assertNotNull(smith);
//        assertEquals(ownerId,smith.getId());
//
//    }
}
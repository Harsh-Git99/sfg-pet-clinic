package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.repositories.OwnerRepository;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import com.springframework.sfgpetclinic.repositories.PetTypeRepository;
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
class OwnerSDJpaServiceTest {

    @Mock
     OwnerRepository ownerRepository;
    @Mock
     PetTypeRepository petTypeRepository;
    @Mock
     PetRepository petRepository;

    @InjectMocks
     OwnerSDJpaService service;

    final String lastName = "Smith";
    final Long ownerId = 1L;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(lastName);

        assertEquals(lastName,smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();

        returnOwnerSet.add(Owner.builder().id(ownerId).build());
        returnOwnerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2,owners.size());

    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = service.findById(ownerId);

        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(ownerId).build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = service.save(owner);
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());

    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ownerId);
        verify(ownerRepository).deleteById(anyLong());
    }



}
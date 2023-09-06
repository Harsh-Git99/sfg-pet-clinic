package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.repositories.SpecialityRepository;
import com.springframework.sfgpetclinic.repositories.VetRepository;
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
class VetSDJpaServicesTest {

    @Mock
    VetRepository vetRepository;

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    VetSDJpaServices services;

    final Long vetId = 1L;
    Vet returnVet;
    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(vetId).build();
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(Vet.builder().id(1L).build());
        vetSet.add(Vet.builder().id(2L).build());

        when(vetRepository.findAll()).thenReturn(vetSet);

        Set<Vet> returnedVetSet = services.findAll();

        assertNotNull(returnedVetSet);
        assertEquals(2,returnedVetSet.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        Vet vet1 = services.findById(vetId);

        assertNotNull(vet1);

    }

    @Test
    void save() {
        Vet vet = Vet.builder().id(1L).build();
        when(vetRepository.save(any())).thenReturn(returnVet);

        Vet returnedVet = services.save(vet);
        assertNotNull(returnedVet);
        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        services.delete(returnVet);

        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        services.deleteById(vetId);
        verify(vetRepository).deleteById(anyLong());
    }
}
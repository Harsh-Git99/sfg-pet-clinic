package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import com.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Profile("springdatajpa")
public class PetSDJpaServices implements PetService {

    private final PetRepository petRepository;

    public PetSDJpaServices(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public Set<Pet> findAll() {
        Set<Pet> pet = new HashSet<>();
        petRepository.findAll().forEach(pet :: add);
        return pet;
    }

    @Override
    public Pet findById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if(!pet.isPresent()) throw  new RuntimeException("Pet is not present");
        else return pet.get();
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}

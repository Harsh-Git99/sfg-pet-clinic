package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.model.Speciality;
import com.springframework.sfgpetclinic.repositories.SpecialityRepository;
import com.springframework.sfgpetclinic.services.SpecialityService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VetSpecialitySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public VetSpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }


    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();

        specialityRepository.findAll().forEach(specialities ::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        Optional<Speciality> speciality = specialityRepository.findById(id);

        if(!speciality.isPresent()) throw new RuntimeException("Speciality not present");
        else return speciality.get();

    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}

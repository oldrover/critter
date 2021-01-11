package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;


    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        addPetToCustomer(savedPet);
        return savedPet;
    }

    public Pet getPetById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if(optionalPet.isPresent()) {
            return optionalPet.get();
        }else {
            throw new PetNotFoundException();
        }
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long id) {
        return petRepository.findAllByCustomer_Id(id);

    }

    private void addPetToCustomer(Pet pet) {
        Customer customer = pet.getCustomer();
        if(customer != null) {
            List<Pet> petList = customer.getPets();
            if(petList == null) {
                petList = new ArrayList<>();
            }
            petList.add(pet);
            customer.setPets(petList);
            customerService.saveCustomer(customer);

        }



    }
}

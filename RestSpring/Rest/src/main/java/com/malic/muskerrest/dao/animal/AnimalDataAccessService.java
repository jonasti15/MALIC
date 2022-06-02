package com.malic.muskerrest.dao.animal;

import com.malic.muskerrest.entities.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalDataAccessService implements AnimalDao{

    @Autowired
    AnimalRepository repository;

    @Override
    public List<Animal> getAllAnimals() {
        return repository.findAll();
    }

    @Override
    public Animal getAnimal(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editAnimal(Animal animal) {
        repository.save(animal);
    }

    @Override
    public void deleteAnimal(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAnimal(Animal animal) {
        repository.delete(animal);
    }

    @Override
    public void addAnimal(Animal animal) {
        long id = repository.findLastAnimal().getAnimalId() + 1;
        animal.setAnimalId(id);
        animal.setPath("/images/animals/"+animal.getEspecie().getDescripcion()+"/"+id + ".png");
        repository.save(animal);
    }

    @Override
    public Animal findLastAnimal() {
        return repository.findLastAnimal();
    }

}

package com.malic.muskerrest.dao.animal;

import com.malic.muskerrest.entities.Animal;

import java.util.List;

public interface AnimalDao {

    List<Animal> getAllAnimals();
    Animal getAnimal(long id);
    void editAnimal(Animal animal);
    void deleteAnimal(long id);
    void deleteAnimal(Animal animal);
    void addAnimal(Animal animal);
    Animal findLastAnimal();

}

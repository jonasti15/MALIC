package com.malic.muskerrest.dao.animal;

import com.malic.muskerrest.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAll();

    @Query(value = "SELECT * FROM animal a ORDER BY a.animal_id DESC LIMIT 1", nativeQuery =  true)
    Animal findLastAnimal();
}

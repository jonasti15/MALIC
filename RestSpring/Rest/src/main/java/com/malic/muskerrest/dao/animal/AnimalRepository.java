package com.malic.muskerrest.dao.animal;

import com.malic.muskerrest.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}

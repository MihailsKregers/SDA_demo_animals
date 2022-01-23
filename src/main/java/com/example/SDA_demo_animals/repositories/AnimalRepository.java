package com.example.SDA_demo_animals.repositories;

import com.example.SDA_demo_animals.data_objects.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}

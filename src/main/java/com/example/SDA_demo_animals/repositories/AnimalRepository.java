package com.example.SDA_demo_animals.repositories;

import com.example.SDA_demo_animals.data_objects.Animal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

    List<Animal> findByAgeGreaterThan(Integer age);

    List<Animal> findByKind(String kind);
}

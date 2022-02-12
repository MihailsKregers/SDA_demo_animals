package com.example.SDA_demo_animals;

import com.example.SDA_demo_animals.data_objects.Animal;
import com.example.SDA_demo_animals.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("json")
@Component
public class DefaultAnimalConfig {

    private String name;
    private String color;
    private Integer age;
    private String gender;
    private String breed;
    private String kind;

    private AnimalRepository animalRepository;

    public DefaultAnimalConfig(
            @Value("${sda.demo.animals.name}") String name,
            @Value("${sda.demo.animals.color}") String color,
            @Value("${sda.demo.animals.age}") Integer age,
            @Value("${sda.demo.animals.gender}") String gender,
            @Value("${sda.demo.animals.breed}") String breed,
            @Value("${sda.demo.animals.kind}") String kind) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.kind = kind;
    }

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
        Animal animal = new Animal();
        animal.setName(this.name);
        animal.setColor(this.color);
        animal.setAge(this.age);
        animal.setGender(this.gender);
        animal.setBreed(this.breed);
        animal.setKind(this.kind);
        animalRepository.save(animal);
    }
}

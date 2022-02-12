package com.example.SDA_demo_animals;

import com.example.SDA_demo_animals.data_objects.Animal;
import com.example.SDA_demo_animals.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Profile("thymeleaf")
@Component
@ConfigurationProperties(prefix = "sda.demo.animals.pretty")
public class DefaultAnimalConfigPretty {

    private String name;
    private String color;
    private Integer age;
    private String gender;
    private String breed;
    private String kind;

    private AnimalRepository animalRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostConstruct
    public void makeAnimal() {
        Animal animal = new Animal();
        animal.setName(this.name);
        animal.setColor(this.color);
        animal.setAge(this.age);
        animal.setGender(this.gender);
        animal.setBreed(this.breed);
        animal.setKind(this.kind);
        animalRepository.save(animal);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}

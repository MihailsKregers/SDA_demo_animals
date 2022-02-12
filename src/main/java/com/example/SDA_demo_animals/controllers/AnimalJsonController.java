package com.example.SDA_demo_animals.controllers;

import com.example.SDA_demo_animals.config.UserRoles;
import com.example.SDA_demo_animals.data_objects.Animal;
import com.example.SDA_demo_animals.exceptions.AnimalJsonException;
import com.example.SDA_demo_animals.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Secured("ROLE_JSON_USER")
@Profile("json")
@Controller
@RequestMapping("/rest")
public class AnimalJsonController {

    private AnimalRepository animalRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostMapping(path = "/postAnimal", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void postAnimal(@RequestBody Animal animal) throws AnimalJsonException {
        if ("Cat".equals(animal.getName())) {
            throw new AnimalJsonException("Tried to post empty Animal with name Cat!");
        }
        animalRepository.save(animal);
    }

    @GetMapping(path = "/showAnimal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal showAnimal(@PathVariable("id") Long id) {
        return animalRepository.findById(id).get();
    }

    @GetMapping(path = "/showAllAnimals", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Animal> showAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping(path = "/showAnimalsOlder/{x}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Animal> showAnimalsOlder(@PathVariable("x") Integer x) {
        return animalRepository.findByAgeGreaterThan(x);
    }

    @GetMapping(path = "/showAnimalsByKind/{kind}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Animal> showAnimalsByKind(@PathVariable("kind") String kind) {
        return animalRepository.findByKind(kind);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public AnimalJsonException handleAnimalJsonException(AnimalJsonException e) {
        return e;
    }
}

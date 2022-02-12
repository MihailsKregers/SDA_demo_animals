package com.example.SDA_demo_animals.controllers;

import com.example.SDA_demo_animals.data_objects.Animal;
import com.example.SDA_demo_animals.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Profile("thymeleaf")
@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

   /* @GetMapping(value = "/")
    public String mainPage(Model model) {
        return showAnimals(model);
    }*/

    @GetMapping(value = "/postAnimal")
    public String postAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        return "editAnimal";
    }

    @PostMapping(value = "/postAnimal")
    public String postAnimal(@Valid Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("animal", animal);
            return "editAnimal";
        }
        animalRepository.save(animal);
        return "redirect:/showAnimal/" + animal.getId();
    }

    @GetMapping(value = "/showAnimal/{id}")
    public String showAnimal(@PathVariable("id") Long id, Model model) {
        Animal animal = animalRepository.findById(id).get();
        model.addAttribute("animal", animal);
        return "showAnimal";
    }

    @GetMapping(value = {"/showAnimals", "/"})
    public String showAnimals(Model model) {
        Iterable<Animal> animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        return "showAnimalList";
    }

    @GetMapping(value = "/showAnimalsOlder/{x}")
    public String showAnimalsOlder(@PathVariable("x") Integer x, Model model) {
        List<Animal> animals = animalRepository.findByAgeGreaterThan(x);
        model.addAttribute("animals", animals);
        return "showAnimalList";
    }

    @GetMapping(value = "/showAnimalsByKind/{kind}")
    public String showAnimalsByKind(@PathVariable("kind") String kind, Model model) {
        List<Animal> animals = animalRepository.findByKind(kind);
        model.addAttribute("animals", animals);
        return "showAnimalList";
    }

    @GetMapping(value = "/updateAnimal/{id}")
    public String updateAnimal(@PathVariable("id") Long id, Model model) {
        Animal animal = animalRepository.findById(id).get();
        model.addAttribute("animal", animal);
        return "editAnimal";
    }
}

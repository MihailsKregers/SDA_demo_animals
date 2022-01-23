package com.example.SDA_demo_animals.controllers;

import com.example.SDA_demo_animals.data_objects.Animal;
import com.example.SDA_demo_animals.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


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
}

package com.james;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by JamesHartanto on 4/14/17.
 */
@Controller
public class animalController {

    @Autowired
    AnimalRepository animalRepository;

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/animalForm")
    public String animalForm(Model model, Integer animalId) {
        if (animalId == null){
            model.addAttribute("animal",new Animal());
        } else{
            model.addAttribute("animal",animalRepository.findAnimal(animalId));
        }
        return "animalForm";
    }

    @GetMapping("/animalList")
    public String animalList(Model model, @RequestParam(defaultValue = "") String searchName,
                             @RequestParam(defaultValue = "") String searchSpecies,
                             @RequestParam(defaultValue = "") String searchBreed,
                             @RequestParam(defaultValue = "") String searchDescription){
        model.addAttribute("listAnimals", animalRepository.listAnimals(searchName, searchSpecies, searchBreed, searchDescription));
        model.addAttribute("searchName",searchName);
        model.addAttribute("searchSpecies",searchSpecies);
        model.addAttribute("searchBreed",searchBreed);
        model.addAttribute("searchDescription",searchDescription);
        return "animalList";
    }

    @PostMapping("/saveAnimal")
    public String saveAnimal(Animal animal){
        animalRepository.saveAnimal(animal);
        return "redirect:/animalList";
    }
}


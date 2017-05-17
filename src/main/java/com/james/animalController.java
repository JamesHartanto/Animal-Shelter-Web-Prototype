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

    @GetMapping("/")
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
    public String animalList(Model model, String searchName,
                             @RequestParam(defaultValue = "") String searchSpecies,
                             String searchBreed,
                             String searchDescription){
        // Filtering data when a field (not species) is populated
        if (searchName != null || searchBreed != null || searchDescription != null){
            if (searchSpecies.equals("")){
                searchSpecies = " ";
            }

            // Another condition - to show everything when other fields are empty
            if (searchName.isEmpty() && searchBreed.isEmpty() && searchDescription.isEmpty()){
                if (searchSpecies.trim().isEmpty()){
                    searchSpecies = "";
                }
            }
        }

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


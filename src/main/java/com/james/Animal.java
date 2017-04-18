package com.james;

/**
 * Created by JamesHartanto on 4/17/17.
 */
public class Animal {
    private Integer animalId;
    private String name;
    private String species;
    private String breed;
    private String description;

    public Animal() {
    }

    public Animal(Integer animalId, String name, String species, String breed, String description) {
        this.animalId = animalId;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

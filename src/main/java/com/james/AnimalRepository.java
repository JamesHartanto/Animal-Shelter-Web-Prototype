package com.james;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JamesHartanto on 4/17/17.
 */
@Component
public class AnimalRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Animal> listAnimals(String searchName, String searchSpecies, String searchBreed, String searchDescription) {
        return jdbcTemplate.query("SELECT * FROM animaltable " +
                "WHERE lower(name) like lower(?) OR " +
                        "lower(species) like lower(?) OR " +
                        "lower(breed) like lower(?) OR " +
                        "lower(description) like lower(?) ORDER BY id ASC",
                new Object[]{searchName,searchSpecies+"%",searchBreed,searchDescription},
                (resultSet, i) -> new Animal(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        resultSet.getString("breed"),
                        resultSet.getString("description")));
    }

    public Animal findAnimal(Integer animalId) {
        return jdbcTemplate.queryForObject("SELECT * FROM animaltable WHERE id = ?",
                new Object[]{animalId},
                (resultSet, i) -> new Animal(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        resultSet.getString("breed"),
                        resultSet.getString("description")));
    }

    public void saveAnimal(Animal animal) {
        if (animal.getAnimalId() == null){
            jdbcTemplate.update("INSERT INTO animaltable(name,species,breed,description) VALUES (?,?,?,?)",
                    new Object[]{animal.getName(),animal.getSpecies(),animal.getBreed(),animal.getDescription()});
        } else {
            jdbcTemplate.update("UPDATE animaltable SET name=?, species=?, breed=?, description=? WHERE id=?",
                    new Object[]{animal.getName(),animal.getSpecies(),animal.getBreed(),animal.getDescription(),animal.getAnimalId()});
        }
    }
}

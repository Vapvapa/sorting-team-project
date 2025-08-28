package com.team.sorting.app;

import com.team.sorting.generator.EntityGenerator;
import com.team.sorting.generator.GeneratorFactory;
import com.team.sorting.model.Animal;
import com.team.sorting.model.Barrel;
import com.team.sorting.model.Human;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityGenerator<Animal> animalGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.ANIMAL);
        List<Animal> randomAnimals = animalGen.generate(5);
        System.out.println("Animals:");
        randomAnimals.forEach(System.out::println);

        EntityGenerator<Barrel> barrelGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.BARREL);
        List<Barrel> randomBarrels = barrelGen.generate(5);
        System.out.println("\nBarrels:");
        randomBarrels.forEach(System.out::println);

        EntityGenerator<Human> humanGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.HUMAN);
        List<Human> randomHumans = humanGen.generate(5);
        System.out.println("\nHumans:");
        randomHumans.forEach(System.out::println);
    }
}

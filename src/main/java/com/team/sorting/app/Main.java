package com.team.sorting.app;

import com.team.sorting.input.generator.EntityGenerator;
import com.team.sorting.input.generator.GeneratorFactory;
import com.team.sorting.input.inputter.EntityInputter;
import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.input.loader.EntityLoader;
import com.team.sorting.input.loader.LoaderFactory;
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

        EntityLoader<Animal> animalLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.ANIMAL);
        List<Animal> animalsFromFile = animalLoader.load("com/team/sorting/loader/testAnimalLoader.txt");
        System.out.println("Loaded Animals:");
        animalsFromFile.forEach(System.out::println);

        EntityLoader<Barrel> barrelLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.BARREL);
        List<Barrel> barrelsFromFile = barrelLoader.load("com/team/sorting/loader/testBarrelLoader.txt");
        System.out.println("Loaded Barrels:");
        barrelsFromFile.forEach(System.out::println);

        EntityLoader<Human> humanLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.HUMAN);
        List<Human> humanFromFile = humanLoader.load("com/team/sorting/loader/testHumanLoader.txt");
        System.out.println("Loaded People:");
        humanFromFile.forEach(System.out::println);

        EntityInputter<Human> humanInputter = InputterFactory.getLoader(InputterFactory.EntityType.HUMAN);
        List<Human> humanFromConsole = humanInputter.input(1);
        System.out.println("Read People:");
        humanFromConsole.forEach(System.out::println);
    }
}

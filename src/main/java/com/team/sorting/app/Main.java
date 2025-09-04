package com.team.sorting.app;

import com.team.sorting.input.generator.EntityGenerator;
import com.team.sorting.input.generator.GeneratorFactory;
import com.team.sorting.input.loader.EntityLoader;
import com.team.sorting.input.loader.LoaderFactory;
import com.team.sorting.model.Animal;
import com.team.sorting.model.Barrel;
import com.team.sorting.model.Human;
import com.team.sorting.sort.InsertionSort;

import java.util.List;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        EntityGenerator<Animal> animalGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.ANIMAL);
        List<Animal> randomAnimals = animalGen.generate(5);
        System.out.println("\nAnimals:");
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
        System.out.println("\nLoaded Animals:");
        animalsFromFile.forEach(System.out::println);

        EntityLoader<Barrel> barrelLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.BARREL);
        List<Barrel> barrelsFromFile = barrelLoader.load("com/team/sorting/loader/testBarrelLoader.txt");
        System.out.println("\nLoaded Barrels:");
        barrelsFromFile.forEach(System.out::println);

        EntityLoader<Human> humanLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.HUMAN);
        List<Human> humanFromFile = humanLoader.load("com/team/sorting/loader/testHumanLoader.txt");
        System.out.println("\nLoaded Human:");
        humanFromFile.forEach(System.out::println);

//        EntityInputter<Animal> animalInputter = InputterFactory.getLoader(InputterFactory.EntityType.ANIMAL);
//        List<Animal> animalFromConsole = animalInputter.input(2);
//        System.out.println("\nRead Animals:");
//        animalFromConsole.forEach(System.out::println);
//
//        EntityInputter<Barrel> barrelInputter = InputterFactory.getLoader(InputterFactory.EntityType.BARREL);
//        List<Barrel> barrelFromConsole = barrelInputter.input(2);
//        System.out.println("\nRead Barrels:");
//        barrelFromConsole.forEach(System.out::println);
//
//        EntityInputter<Human> humanInputter = InputterFactory.getLoader(InputterFactory.EntityType.HUMAN);
//        List<Human> humanFromConsole = humanInputter.input(2);
//        System.out.println("\nRead People:");
//        humanFromConsole.forEach(System.out::println);

        InsertionSort<Animal> sorter = new InsertionSort<>();
        List<Animal> sortedAnimals = sorter.sort(randomAnimals, Comparator.naturalOrder());
        System.out.println("\nSorted animals:");
        sortedAnimals.forEach(System.out::println);

        InsertionSort<Barrel> barrelSorter = new InsertionSort<>();
        List<Barrel> sortedBarrels = barrelSorter.sort(randomBarrels, Comparator.naturalOrder());
        System.out.println("\nSorted barrels:");
        sortedBarrels.forEach(System.out::println);

        InsertionSort<Human> humanSorter = new InsertionSort<>();
        List<Human> sortedHumans = humanSorter.sort(randomHumans, Comparator.naturalOrder());
        System.out.println("\nSorted humans:");
        sortedHumans.forEach(System.out::println);
    }
}

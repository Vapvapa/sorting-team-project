package com.team.sorting.app;

import com.team.sorting.input.generator.EntityGenerator;
import com.team.sorting.input.generator.GeneratorFactory;
import com.team.sorting.input.loader.EntityLoader;
import com.team.sorting.input.loader.LoaderFactory;
import com.team.sorting.input.inputter.EntityInputter;
import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.model.Animal;
import com.team.sorting.model.Barrel;
import com.team.sorting.model.Human;
import com.team.sorting.search.BinarySearch;
import com.team.sorting.search.FindElementsInCollection;
import com.team.sorting.sort.InsertionSort;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSelect entity type: 1 - Animal, 2 - Barrel, 3 - Human, 0 - Exit");
            int entityChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (entityChoice == 0) {
                exit = true;
                continue;
            }

            System.out.println("Select input method: 1 - From file, 2 - Random, 3 - Input from console");
            int inputChoice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter collection size: ");
            int size = scanner.nextInt();
            scanner.nextLine();

            switch (entityChoice) {
                case 1 -> handleAnimal(inputChoice, size, scanner);
                case 2 -> handleBarrel(inputChoice, size, scanner);
                case 3 -> handleHuman(inputChoice, size, scanner);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Program exited.");
    }

    private static void handleAnimal(int inputChoice, int size, Scanner scanner) {
        List<Animal> animals;

        switch (inputChoice) {
            case 1 -> {
                EntityLoader<Animal> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.ANIMAL);
                animals = loader.load("com/team/sorting/loader/testAnimalLoader.txt");
            }
            case 2 -> {
                EntityGenerator<Animal> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.ANIMAL);
                animals = generator.generate(size);
            }
            case 3 -> {
                EntityInputter<Animal> inputter = InputterFactory.getLoader(InputterFactory.EntityType.ANIMAL);
                animals = inputter.input(size);
            }
            default -> {
                System.out.println("Invalid input method.");
                return;
            }
        }

        System.out.println("\nOriginal Animals:");
        animals.forEach(System.out::println);

        // Sorting
        InsertionSort<Animal> sorter = new InsertionSort<>();
        List<Animal> sorted = sorter.sort(animals, Comparator.naturalOrder());
        System.out.println("\nSorted Animals:");
        sorted.forEach(System.out::println);

        // Searching
        System.out.println("\nEnter search key (exact values only):");
        System.out.println("Format: Species EyeColor Fur EatsBun");
        System.out.println("Example: CAT BROWN SHORT true");
        System.out.println("Available values:");
        System.out.println("Species: " + java.util.Arrays.toString(Animal.Species.values()));
        System.out.println("EyeColor: " + java.util.Arrays.toString(Animal.EyeColor.values()));
        System.out.println("Fur: " + java.util.Arrays.toString(Animal.Fur.values()));
        System.out.println("EatsBun: true, false");
        System.out.print("Enter key: ");

        String input = scanner.nextLine().trim();
        String[] parts = input.split("\\s+");

        if (parts.length != 4) {
            System.out.println("Invalid format. Please enter exactly 4 values separated by spaces.");
            return;
        }

        Animal.Species speciesCriteria;
        Animal.EyeColor eyeColorCriteria;
        Animal.Fur furCriteria;
        boolean eatsBunCriteria;

        try {
            speciesCriteria = Animal.Species.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid species: " + parts[0]);
            return;
        }
        try {
            eyeColorCriteria = Animal.EyeColor.valueOf(parts[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid eye color: " + parts[1]);
            return;
        }
        try {
            furCriteria = Animal.Fur.valueOf(parts[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid fur: " + parts[2]);
            return;
        }
        if (parts[3].equalsIgnoreCase("true")) {
            eatsBunCriteria = true;
        } else if (parts[3].equalsIgnoreCase("false")) {
            eatsBunCriteria = false;
        } else {
            System.out.println("Invalid eats bun value: " + parts[3]);
            return;
        }

        // Binary search
        Animal key = new Animal.Builder()
                .species(speciesCriteria)
                .eyeColor(eyeColorCriteria)
                .fur(furCriteria)
                .eatsBun(eatsBunCriteria)
                .build();

        BinarySearch<Animal> binarySearch = new BinarySearch<>();
        int idx = binarySearch.search(sorted, key);
        if (idx >= 0) {
            System.out.println("Found at index " + idx + ": " + sorted.get(idx));
        } else {
            System.out.println("No animals found matching the criteria.");
        }

        // FindElementsInCollection
        System.out.print("\nEnter value for global search across all collections: ");
        String globalSearchValue = scanner.nextLine();

        // There are only animals, so we pass empty lists for Barrel and Human
        FindElementsInCollection.countersOfElements(
                animals, globalSearchValue,
                List.of(), globalSearchValue,
                List.of(), globalSearchValue
        );
    }

    private static void handleBarrel(int inputChoice, int size, Scanner scanner) {
        List<Barrel> barrels;

        switch (inputChoice) {
            case 1 -> {
                EntityLoader<Barrel> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.BARREL);
                barrels = loader.load("com/team/sorting/loader/testBarrelLoader.txt");
            }
            case 2 -> {
                EntityGenerator<Barrel> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.BARREL);
                barrels = generator.generate(size);
            }
            case 3 -> {
                EntityInputter<Barrel> inputter = InputterFactory.getLoader(InputterFactory.EntityType.BARREL);
                barrels = inputter.input(size);
            }
            default -> {
                System.out.println("Invalid input method.");
                return;
            }
        }

        System.out.println("\nOriginal Barrels:");
        barrels.forEach(System.out::println);

        // Sorting
        InsertionSort<Barrel> sorter = new InsertionSort<>();
        List<Barrel> sorted = sorter.sort(barrels, Comparator.naturalOrder());
        System.out.println("\nSorted Barrels:");
        sorted.forEach(System.out::println);

        // Searching
        System.out.println("\nEnter search key (exact values only):");
        System.out.println("Format: Volume Material StoredMaterial");
        System.out.println("Example: 50 WOOD WATER");
        System.out.println("Available values:");
        System.out.println("Material: " + java.util.Arrays.toString(Barrel.Material.values()));
        System.out.println("StoredMaterial: " + java.util.Arrays.toString(Barrel.StoredMaterial.values()));
        System.out.print("Enter key: ");

        String input = scanner.nextLine().trim();
        String[] parts = input.split("\\s+");

        if (parts.length != 3) {
            System.out.println("Invalid format. Please enter exactly 3 values separated by spaces.");
            return;
        }

        int volumeCriteria;
        Barrel.Material materialCriteria;
        Barrel.StoredMaterial storedMaterialCriteria;

        try {
            volumeCriteria = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid volume: " + parts[0]);
            return;
        }
        try {
            materialCriteria = Barrel.Material.valueOf(parts[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid material: " + parts[1]);
            return;
        }
        try {
            storedMaterialCriteria = Barrel.StoredMaterial.valueOf(parts[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid stored material: " + parts[2]);
            return;
        }

        // Binary search
        Barrel key = new Barrel.Builder()
                .volume(volumeCriteria)
                .material(materialCriteria)
                .storedMaterial(storedMaterialCriteria)
                .build();

        BinarySearch<Barrel> binarySearch = new BinarySearch<>();
        int idx = binarySearch.search(sorted, key);
        if (idx >= 0) {
            System.out.println("Found at index " + idx + ": " + sorted.get(idx));
        } else {
            System.out.println("No barrels found matching the criteria.");
        }

        // FindElementsInCollection
        System.out.print("\nEnter value for global search across all collections: ");
        String globalSearchValue = scanner.nextLine();

        FindElementsInCollection.countersOfElements(
                List.of(), globalSearchValue,
                barrels, globalSearchValue,
                List.of(), globalSearchValue
        );
    }

    private static void handleHuman(int inputChoice, int size, Scanner scanner) {
        List<Human> humans;

        switch (inputChoice) {
            case 1 -> {
                EntityLoader<Human> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.HUMAN);
                humans = loader.load("com/team/sorting/loader/testHumanLoader.txt");
            }
            case 2 -> {
                EntityGenerator<Human> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.HUMAN);
                humans = generator.generate(size);
            }
            case 3 -> {
                EntityInputter<Human> inputter = InputterFactory.getLoader(InputterFactory.EntityType.HUMAN);
                humans = inputter.input(size);
            }
            default -> {
                System.out.println("Invalid input method.");
                return;
            }
        }

        System.out.println("\nOriginal Humans:");
        humans.forEach(System.out::println);

        // Sorting
        InsertionSort<Human> sorter = new InsertionSort<>();
        List<Human> sorted = sorter.sort(humans, Comparator.naturalOrder());
        System.out.println("\nSorted Humans:");
        sorted.forEach(System.out::println);

        // Searching
        System.out.println("\nEnter search key (exact values only):");
        System.out.println("Format: Gender Age LastName");
        System.out.println("Example: MALE 25 Smith");
        System.out.println("Available values:");
        System.out.println("Gender: " + java.util.Arrays.toString(Human.Gender.values()));
        System.out.print("Enter key: ");

        String input = scanner.nextLine().trim();
        String[] parts = input.split("\\s+");

        if (parts.length != 3) {
            System.out.println("Invalid format. Please enter exactly 3 values separated by spaces.");
            return;
        }

        Human.Gender genderCriteria;
        int ageCriteria;
        String lastNameCriteria = parts[2];

        try {
            genderCriteria = Human.Gender.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender: " + parts[0]);
            return;
        }
        try {
            ageCriteria = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age: " + parts[1]);
            return;
        }

        // Binary search
        Human key = new Human.Builder()
                .gender(genderCriteria)
                .age(ageCriteria)
                .lastName(lastNameCriteria)
                .build();

        BinarySearch<Human> binarySearch = new BinarySearch<>();
        int idx = binarySearch.search(sorted, key);
        if (idx >= 0) {
            System.out.println("Found at index " + idx + ": " + sorted.get(idx));
        } else {
            System.out.println("No humans found matching the criteria.");
        }

        // FindElementsInCollection
        System.out.print("\nEnter value for global search across all collections: ");
        String globalSearchValue = scanner.nextLine();

        FindElementsInCollection.countersOfElements(
                List.of(), globalSearchValue,
                List.of(), globalSearchValue,
                humans, globalSearchValue
        );
    }
}

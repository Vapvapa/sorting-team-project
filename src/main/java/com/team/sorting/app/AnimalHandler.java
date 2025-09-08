package com.team.sorting.app;

import com.team.sorting.input.generator.EntityGenerator;
import com.team.sorting.input.generator.GeneratorFactory;
import com.team.sorting.input.inputter.EntityInputter;
import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.input.loader.EntityLoader;
import com.team.sorting.input.loader.LoaderFactory;
import com.team.sorting.model.Animal;
import com.team.sorting.search.BinarySearch;
import com.team.sorting.search.FindElementsInCollection;
import com.team.sorting.sort.InsertionSort;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * {@link EntityHandler} implementation for working with {@link Animal} entities.
 * <p>
 * Provides functionality to:
 * <ul>
 *     <li>Load animals from file, generate them, or input manually.</li>
 *     <li>Sort animals using insertion sort and natural ordering.</li>
 *     <li>Search for a specific animal using binary search.</li>
 *     <li>Perform a global search across the collection for any matching value.</li>
 * </ul>
 */
public class AnimalHandler implements EntityHandler<Animal> {

    /**
     * Loads a collection of animals depending on the input choice.
     *
     * @param inputChoice user choice of input source:
     *                    <ul>
     *                        <li>1 - load from file</li>
     *                        <li>2 - generate randomly</li>
     *                        <li>3 - manual input</li>
     *                    </ul>
     * @param size the number of animals to generate or input (ignored if loading from file)
     * @param scanner scanner for user input (only used for manual input)
     * @return a list of {@link Animal} instances
     * @throws IllegalArgumentException if {@code inputChoice} is invalid
     */
    @Override
    public List<Animal> loadEntities(int inputChoice, int size, Scanner scanner) {
        return switch (inputChoice) {
            case 1 -> {
                EntityLoader<Animal> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.ANIMAL);
                yield loader.load("com/team/sorting/loader/testAnimalLoader.txt");
            }
            case 2 -> {
                EntityGenerator<Animal> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.ANIMAL);
                yield generator.generate(size);
            }
            case 3 -> {
                EntityInputter<Animal> inputter = InputterFactory.getLoader(InputterFactory.EntityType.ANIMAL);
                yield inputter.input(size);
            }
            default -> throw new IllegalArgumentException("Invalid input choice: " + inputChoice);
        };
    }

    /**
     * Prints animals, sorts them, performs binary search, and executes a global search.
     * <p>
     * The method:
     * <ol>
     *     <li>Prints the original collection of animals.</li>
     *     <li>Sorts them with {@link InsertionSort} using natural ordering and prints the result.</li>
     *     <li>Prompts the user to enter a full animal specification (species, eye color, fur, eatsBun)
     *         and performs a binary search with {@link BinarySearch}.</li>
     *     <li>Prompts for a value to search across all fields of all animals using
     *         {@link FindElementsInCollection#countersOfElements(List, String, List, String, List, String)}.</li>
     * </ol>
     *
     * @param animals the list of animals to process
     * @param scanner scanner for reading user input
     */
    @Override
    public void searchAndPrint(List<Animal> animals, Scanner scanner) {
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

        FindElementsInCollection.countersOfElements(
                animals, globalSearchValue,
                List.of(), globalSearchValue,
                List.of(), globalSearchValue
        );
    }
}

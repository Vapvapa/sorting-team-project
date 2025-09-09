package com.team.sorting.app;

import com.team.sorting.writeToFile.FileRecorder;
import com.team.sorting.input.generator.EntityGenerator;
import com.team.sorting.input.generator.GeneratorFactory;
import com.team.sorting.input.inputter.EntityInputter;
import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.input.loader.EntityLoader;
import com.team.sorting.input.loader.LoaderFactory;
import com.team.sorting.model.Human;
import com.team.sorting.search.BinarySearch;
import com.team.sorting.search.FindElementsInCollection;
import com.team.sorting.sort.InsertionSort;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Handler class for {@link Human} entities.
 * <p>
 * Responsible for loading humans from different sources (file, generator, user input),
 * sorting them, performing searches, and printing results.
 * Implements {@link EntityHandler}.
 */
public class HumanHandler implements EntityHandler<Human> {

    /**
     * Loads a list of humans based on the user's choice.
     *
     * @param inputChoice the input method chosen by the user:
     *                    1 — load from file,
     *                    2 — generate randomly,
     *                    3 — input manually
     * @param size the number of humans to generate or input (ignored if loading from file)
     * @param scanner the scanner used for user input
     * @return a list of {@link Human} objects
     * @throws IllegalArgumentException if {@code inputChoice} is invalid
     */
    @Override
    public List<Human> loadEntities(int inputChoice, int size, Scanner scanner) {
        return switch (inputChoice) {
            case 1 -> {
                EntityLoader<Human> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.HUMAN);
                yield loader.load("com/team/sorting/loader/testHumanLoader.txt");
            }
            case 2 -> {
                EntityGenerator<Human> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.HUMAN);
                yield generator.generate(size);
            }
            case 3 -> {
                EntityInputter<Human> inputter = InputterFactory.getLoader(InputterFactory.EntityType.HUMAN);
                yield inputter.input(size);
            }
            default -> throw new IllegalArgumentException("Invalid input choice: " + inputChoice);
        };
    }

    /**
     * Sorts the list of humans, allows the user to search for a specific human
     * by exact criteria using binary search, and performs a global search across all collections.
     * <p>
     * Prints the original and sorted lists, search results, and counts of matching elements.
     *
     * @param humans the list of humans to process
     * @param scanner the scanner used for reading user input
     */
    @Override
    public void searchAndPrint(List<Human> humans, Scanner scanner) {
        System.out.println("\nOriginal Humans:");
        humans.forEach(System.out::println);
        FileRecorder.writeCollectionToFile(humans, "output.txt");

        // Sorting
        InsertionSort<Human> sorter = new InsertionSort<>();
        List<Human> sorted = sorter.sort(humans, Comparator.naturalOrder());
        System.out.println("\nSorted Humans:");
        sorted.forEach(System.out::println);
        FileRecorder.writeCollectionToFile(sorted, "output.txt");

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

        // Global search across collections
        System.out.print("\nEnter value for global search across all collections: ");
        String globalSearchValue = scanner.nextLine();

        FindElementsInCollection.countersOfElements(
                List.of(), globalSearchValue,
                List.of(), globalSearchValue,
                humans, globalSearchValue
        );
    }
}

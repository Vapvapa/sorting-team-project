package com.team.sorting.app;

import com.team.sorting.writeToFile.FileRecorder;
import com.team.sorting.input.generator.EntityGenerator;
import com.team.sorting.input.generator.GeneratorFactory;
import com.team.sorting.input.inputter.EntityInputter;
import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.input.loader.EntityLoader;
import com.team.sorting.input.loader.LoaderFactory;
import com.team.sorting.model.Barrel;
import com.team.sorting.search.BinarySearch;
import com.team.sorting.search.FindElementsInCollection;
import com.team.sorting.sort.InsertionSort;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Handles operations for {@link Barrel} entities:
 * <ul>
 *     <li>Loading barrels from file, generator, or user input</li>
 *     <li>Sorting barrels using {@link InsertionSort}</li>
 *     <li>Searching barrels with {@link BinarySearch}</li>
 *     <li>Performing global search with {@link FindElementsInCollection}</li>
 * </ul>
 */
public class BarrelHandler implements EntityHandler<Barrel> {

    /**
     * Loads a list of {@link Barrel} entities based on the chosen input method.
     *
     * @param inputChoice the input option:
     *                    <ul>
     *                        <li>1 – load from file</li>
     *                        <li>2 – generate randomly</li>
     *                        <li>3 – manual input</li>
     *                    </ul>
     * @param size the number of entities to generate or input (ignored if loading from file)
     * @param scanner scanner for user input (used in manual input option)
     * @return a list of loaded {@link Barrel} entities
     * @throws IllegalArgumentException if {@code inputChoice} is not 1, 2, or 3
     */
    @Override
    public List<Barrel> loadEntities(int inputChoice, int size, Scanner scanner) {
        return switch (inputChoice) {
            case 1 -> {
                EntityLoader<Barrel> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.BARREL);
                yield loader.load("com/team/sorting/loader/testBarrelLoader.txt");
            }
            case 2 -> {
                EntityGenerator<Barrel> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.BARREL);
                yield generator.generate(size);
            }
            case 3 -> {
                EntityInputter<Barrel> inputter = InputterFactory.getLoader(InputterFactory.EntityType.BARREL);
                yield inputter.input(size);
            }
            default -> throw new IllegalArgumentException("Invalid input choice: " + inputChoice);
        };
    }

    /**
     * Sorts and searches {@link Barrel} entities.
     * <p>
     * Steps:
     * <ol>
     *     <li>Prints the original list of barrels</li>
     *     <li>Sorts barrels with {@link InsertionSort}</li>
     *     <li>Prompts the user to enter a search key (volume, material, stored material)</li>
     *     <li>Performs {@link BinarySearch} for an exact match</li>
     *     <li>Asks for a value and performs global search across collections</li>
     * </ol>
     *
     * @param barrels list of barrels to process
     * @param scanner scanner for reading user input
     */
    @Override
    public void searchAndPrint(List<Barrel> barrels, Scanner scanner) {
        System.out.println("\nOriginal Barrels:");
        barrels.forEach(System.out::println);
        FileRecorder.writeCollectionToFile(barrels, "output.txt");

        // Sorting
        InsertionSort<Barrel> sorter = new InsertionSort<>();
        List<Barrel> sorted = sorter.sort(barrels, Comparator.naturalOrder());
        System.out.println("\nSorted Barrels:");
        sorted.forEach(System.out::println);
        FileRecorder.writeCollectionToFile(sorted, "output.txt");

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
}

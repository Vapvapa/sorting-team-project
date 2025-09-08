package com.team.sorting.app;

import java.util.List;
import java.util.Scanner;

/**
 * Interface defining operations for handling entities of type {@code T}.
 * <p>
 * Provides methods to:
 * <ul>
 *     <li>Load entities from different sources (file, generator, or user input)</li>
 *     <li>Search and display entities</li>
 * </ul>
 *
 * @param <T> the type of entities to handle
 */
public interface EntityHandler<T> {

    /**
     * Loads a list of entities of type {@code T} based on the specified input choice.
     *
     * @param inputChoice the input option:
     *                    <ul>
     *                        <li>1 – load from file</li>
     *                        <li>2 – generate randomly</li>
     *                        <li>3 – manual input</li>
     *                    </ul>
     * @param size the number of entities to generate or input (ignored if loading from file)
     * @param scanner a {@link Scanner} for reading user input (used for manual input)
     * @return a list of loaded entities
     * @throws IllegalArgumentException if {@code inputChoice} is not 1, 2, or 3
     */
    List<T> loadEntities(int inputChoice, int size, Scanner scanner);

    /**
     * Performs search operations on the given list of entities and prints results.
     * <p>
     * This may include sorting the entities, searching for a specific key,
     * or performing a global search across multiple properties.
     *
     * @param entities the list of entities to search
     * @param scanner a {@link Scanner} for reading user input for search criteria
     */
    void searchAndPrint(List<T> entities, Scanner scanner);
}

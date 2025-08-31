package com.team.sorting.loader;

import java.util.List;

/**
 * A generic interface for classes that load and parse entities from a file.
 *
 * @param <T> The type of entity to be loaded.
 */
@FunctionalInterface
public interface EntityLoader<T> {

    /**
     * Loads a list of entities from the specified file path.
     *
     * @param filepath The path to the file containing the entity data.
     * @return A list of valid entities parsed from the file.
     */
    List<T> load(String filepath);
}

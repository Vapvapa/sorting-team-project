package com.team.sorting.input.generator;

import java.util.List;

/**
 * A generic interface for entity generators.
 * Defines a method for generating a list of objects of type T.
 *
 * @param <T> The type of entity to generate.
 */
public interface EntityGenerator<T> {

    /**
     * Generates a list of entities.
     *
     * @param count The number of entities to generate.
     * @return A list containing the generated entities.
     */
    List<T> generate(int count);
}

package com.team.sorting.input.inputter;

import java.util.List;

/**
 * A generic interface for entity inputters.
 * Defines a method for reading a list of objects of type T from an input source.
 *
 * @param <T> The type of entity to input.
 */
public interface EntityInputter<T> {

    /**
     * Reads a list of entities from an input source.
     *
     * @param count The number of entities to read.
     * @return A list containing the input entities.
     */
    List<T> input(int count);
}

package com.team.sorting.search;

import java.util.List;

/**
 * Functional interface for search algorithms.
 * <p>
 * Defines a contract for searching a key in a list and
 * returning its index or {@code -1} if not found.
 *
 * @param <T> the type of elements to search, must implement {@link Comparable}
 */
@FunctionalInterface
public interface Search<T extends Comparable<T>> {

    /**
     * Searches for the given key in the specified list.
     *
     * @param list the list to search in
     * @param key  the element to find
     * @return the index of the key if found, otherwise {@code -1}
     */
    int search(List<T> list, T key);
}

package com.team.sorting.sort;

import java.util.Comparator;
import java.util.List;

/**
 * A generic sorting interface.
 * Implementations define how a list of elements is sorted
 * using a provided {@link Comparator}.
 *
 * @param <E> the type of elements to be sorted
 */
@FunctionalInterface
public interface Sort<E> {

    /**
     * Sorts the given list using the specified comparator.
     * The behavior (in-place or returning a copy) depends on the implementation.
     *
     * @param list the list of elements to sort
     * @param comparator the comparator that defines element order; must not be {@code null}
     * @return the sorted list (may be a new list or the same instance, depending on implementation)
     * @throws IllegalArgumentException if the comparator is {@code null}
     */
    List<E> sort(List<E> list, Comparator<? super E> comparator);
}

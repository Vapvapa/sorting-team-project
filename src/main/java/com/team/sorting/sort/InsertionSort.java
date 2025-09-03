package com.team.sorting.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Generic insertion sort implementation.
 * Creates a new sorted copy of the input list using either natural ordering or a custom {@link Comparator}.
 *
 * @param <E> the type of elements to be sorted
 */
public final class InsertionSort<E> implements Sort<E> {

    /** Ensures thread safety for concurrent sorting calls. */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Sorts the given list using insertion sort with the provided comparator.
     * Returns a new sorted list without modifying the input.
     *
     * @param list the list to sort (may be {@code null} or empty)
     * @param comparator the comparator used for ordering; must not be {@code null}
     * @return a new sorted list, or the original list if null/size < 2
     * @throws IllegalArgumentException if comparator is {@code null}
     */
    @Override
    public List<E> sort(List<E> list, Comparator<? super E> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator must not be null");
        }

        lock.lock();
        try {
            List<E> result = new ArrayList<>(list);

            for (int i = 1; i < result.size(); i++) {
                E key = result.get(i);
                int j = i - 1;
                while (j >= 0 && comparator.compare(result.get(j), key) > 0) {
                    result.set(j + 1, result.get(j));
                    j--;
                }
                result.set(j + 1, key);
            }

            return result;
        } finally {
            lock.unlock();
        }
    }
}

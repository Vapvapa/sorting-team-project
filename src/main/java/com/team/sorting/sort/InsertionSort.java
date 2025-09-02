package com.team.sorting.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Generic insertion sort implementation that sorts lists in-place.
 * Provides overloads for elements implementing {@link Comparable} and for a custom {@link Comparator}.
 */
public final class InsertionSort<E> implements Sort<E> {

    private final ReentrantLock lock = new ReentrantLock();

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



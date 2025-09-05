package com.team.sorting.binarySearch;

import java.util.List;

/**
 * Implementation of the binary search algorithm.
 * <p>
 * Works only on lists that are already sorted in ascending order
 * according to their natural ordering (defined by {@link Comparable}).
 * Returns the index of the searched element or {@code -1} if it is not found.
 *
 * @param <T> the type of elements in the list; must implement {@link Comparable}
 */
public class BinarySearch<T extends Comparable<T>> implements Search<T> {

    /**
     * Searches for a given key in the sorted list using binary search.
     *
     * @param sortedList the list to search in (must be sorted in ascending order)
     * @param key        the element to find
     * @return the index of the key if found, otherwise {@code -1}
     * @throws NullPointerException if {@code sortedList} or {@code key} is {@code null}
     */
    @Override
    public int search(List<T> sortedList, T key) {
        int low = 0;
        int high = sortedList.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedList.get(mid).compareTo(key);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}

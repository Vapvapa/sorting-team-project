package com.team.sorting.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link InsertionSort} class.
 * These tests verify sorting behavior, immutability of input list,
 * exception handling, and correct ordering with various comparators.
 */
class InsertionSortTest {

    private final InsertionSort<Integer> sorter = new InsertionSort<>();

    /**
     * Tests that sorting a null list returns null.
     */
    @Test
    void testSortNullList() {
        assertNull(sorter.sort(null, Comparator.naturalOrder()),
                "Sorting null list should return null");
    }

    /**
     * Tests that sorting an empty list returns the same empty list.
     */
    @Test
    void testSortEmptyList() {
        List<Integer> empty = Collections.emptyList();
        List<Integer> result = sorter.sort(empty, Comparator.naturalOrder());
        assertSame(empty, result, "Sorting empty list should return the same list");
    }

    /**
     * Tests that sorting a single-element list returns the same list.
     */
    @Test
    void testSortSingleElementList() {
        List<Integer> single = Collections.singletonList(42);
        List<Integer> result = sorter.sort(single, Comparator.naturalOrder());
        assertSame(single, result, "Sorting single-element list should return the same list");
    }

    /**
     * Tests that sorting with a null comparator throws IllegalArgumentException.
     */
    @Test
    void testSortNullComparatorThrows() {
        List<Integer> list = Arrays.asList(3, 1, 2);
        assertThrows(IllegalArgumentException.class, () -> sorter.sort(list, null));
    }

    /**
     * Tests sorting a list in ascending order.
     */
    @Test
    void testSortAscendingOrder() {
        List<Integer> list = Arrays.asList(5, 2, 9, 1, 7);
        List<Integer> sorted = sorter.sort(list, Comparator.naturalOrder());

        assertEquals(Arrays.asList(1, 2, 5, 7, 9), sorted, "List should be sorted in ascending order");
        assertEquals(Arrays.asList(5, 2, 9, 1, 7), list, "Original list must remain unchanged");
    }

    /**
     * Tests sorting a list in descending order.
     */
    @Test
    void testSortDescendingOrder() {
        List<Integer> list = Arrays.asList(3, 8, 1, 6);
        List<Integer> sorted = sorter.sort(list, Comparator.reverseOrder());

        assertEquals(Arrays.asList(8, 6, 3, 1), sorted, "List should be sorted in descending order");
        assertEquals(Arrays.asList(3, 8, 1, 6), list, "Original list must remain unchanged");
    }

    /**
     * Tests sorting a list with duplicate elements.
     */
    @Test
    void testSortWithDuplicates() {
        List<Integer> list = Arrays.asList(4, 2, 4, 1, 2);
        List<Integer> sorted = sorter.sort(list, Comparator.naturalOrder());

        assertEquals(Arrays.asList(1, 2, 2, 4, 4), sorted, "List should be sorted with duplicates preserved");
    }

    /**
     * Tests sorting a list of integers using a custom comparator (descending order).
     */
    @Test
    void testSortDescending() {
        List<Integer> list = Arrays.asList(5, 3, 1, 4);
        List<Integer> sorted = sorter.sort(list, Comparator.reverseOrder());

        assertEquals(Arrays.asList(5, 4, 3, 1), sorted, "List should be sorted in descending order");
    }
}

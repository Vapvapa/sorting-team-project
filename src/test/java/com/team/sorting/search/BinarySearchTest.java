package com.team.sorting.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link BinarySearch}.
 */
class BinarySearchTest {

    /**
     * Binary search instance for {@link Integer} elements.
     */
    private final BinarySearch<Integer> intSearch = new BinarySearch<>();

    /**
     * Binary search instance for {@link String} elements.
     */
    private final BinarySearch<String> stringSearch = new BinarySearch<>();

    /**
     * Tests searching for an element that exists in the list.
     */
    @Test
    void testElementFound() {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        int index = intSearch.search(list, 5);
        assertEquals(2, index, "Should return the correct index of the element");
    }

    /**
     * Tests searching for an element that does not exist in the list.
     */
    @Test
    void testElementNotFound() {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        int index = intSearch.search(list, 4);
        assertEquals(-1, index, "Should return -1 when element is not found");
    }

    /**
     * Tests searching in an empty list.
     */
    @Test
    void testEmptyList() {
        List<Integer> list = Collections.emptyList();
        int index = intSearch.search(list, 1);
        assertEquals(-1, index, "Should return -1 for empty list");
    }

    /**
     * Tests searching in a list with a single element.
     */
    @Test
    void testSingleElementList() {
        List<Integer> list = Collections.singletonList(10);
        assertEquals(0, intSearch.search(list, 10), "Should find the single element");
        assertEquals(-1, intSearch.search(list, 5), "Should return -1 if single element does not match");
    }

    /**
     * Tests searching for the first and last elements in the list.
     */
    @Test
    void testFirstAndLastElements() {
        List<Integer> list = Arrays.asList(2, 4, 6, 8, 10);
        assertEquals(0, intSearch.search(list, 2), "Should find the first element");
        assertEquals(4, intSearch.search(list, 10), "Should find the last element");
    }

    /**
     * Tests searching in a list with duplicate elements.
     */
    @Test
    void testDuplicates() {
        List<Integer> list = Arrays.asList(1, 3, 3, 3, 5, 7);
        int index = intSearch.search(list, 3);
        assertTrue(index >= 1 && index <= 3, "Should return the index of one of the duplicate elements");
    }

    /**
     * Tests searching in a list of strings.
     */
    @Test
    void testStringList() {
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
        assertEquals(1, stringSearch.search(list, "banana"), "Should find the string element");
        assertEquals(-1, stringSearch.search(list, "fig"), "Should return -1 for missing string");
    }
}

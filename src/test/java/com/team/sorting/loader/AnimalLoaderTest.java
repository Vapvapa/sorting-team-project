package com.team.sorting.loader;

import com.team.sorting.model.Animal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AnimalLoader} class.
 * These tests verify the correctness of parsing logic and file loading functionality.
 */
class AnimalLoaderTest {

    /**
     * Tests that the loader correctly reads a valid file and returns the expected number of animals.
     * The test file contains 3 valid animal entries.
     */
    @Test
    void testLoadValidFile() {
        EntityLoader<Animal> loader = new AnimalLoader();
        List<Animal> animals = loader.load("com/team/sorting/loader/testAnimalLoader.txt");
        assertEquals(3, animals.size(), "The file should contain exactly 3 valid animals.");
    }

    /**
     * Tests that parsing a line with an incorrect format (e.g., wrong number of fields)
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testParseLineThrowsOnInvalidFormat() {
        AnimalLoader loader = new AnimalLoader();
        String invalidLine = "DOG,BROWN"; // Only two fields instead of four
        assertThrows(IllegalArgumentException.class, () -> loader.parseLine(invalidLine),
                "Parsing a line with incorrect format should throw IllegalArgumentException.");
    }

    /**
     * Tests that parsing a line with unknown enum values or an invalid boolean value
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testParseLineThrowsOnUnknownEnumOrBoolean() {
        AnimalLoader loader = new AnimalLoader();
        String invalidLine = "CAT, BLUE, SHORT, YES"; // 'YES' is invalid for boolean
        assertThrows(IllegalArgumentException.class, () -> loader.parseLine(invalidLine),
                "Parsing a line with an unknown enum or invalid boolean should throw IllegalArgumentException.");
    }
}

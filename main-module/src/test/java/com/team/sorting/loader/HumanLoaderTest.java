package com.team.sorting.loader;

import com.team.sorting.model.Human;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link HumanLoader}.
 * Tests loading of humans from files and parsing individual lines.
 */
class HumanLoaderTest {

    /**
     * Tests that a valid file is loaded correctly.
     * The test file contains 3 valid human entries.
     */
    @Test
    void testLoadValidFile() {
        EntityLoader<Human> loader = new HumanLoader();
        List<Human> humans = loader.load("com/team/sorting/loader/testHumanLoader.txt");
        assertEquals(3, humans.size(), "The loader should parse all valid human entries from the file.");
    }

    /**
     * Tests that parsing a line with an incorrect number of fields throws IllegalArgumentException.
     * Example: only 2 fields instead of 3.
     */
    @Test
    void testParseLineThrowsOnInvalidFormat() {
        HumanLoader loader = new HumanLoader();
        String invalidLine = "MALE,25"; // Only two fields instead of three
        assertThrows(IllegalArgumentException.class, () -> loader.parseLine(invalidLine),
                "Parsing a line with the wrong number of fields should throw IllegalArgumentException.");
    }

    /**
     * Tests that parsing a line with an invalid age format throws NumberFormatException.
     * Example: the age field should be an integer, but it's not.
     */
    @Test
    void testParseLineThrowsOnInvalidAge() {
        HumanLoader loader = new HumanLoader();
        String invalidLine = "MALE,abc,Smith"; // 'abc' is not a valid integer
        assertThrows(NumberFormatException.class, () -> loader.parseLine(invalidLine),
                "Parsing a line with a non-integer age should throw NumberFormatException.");
    }
}

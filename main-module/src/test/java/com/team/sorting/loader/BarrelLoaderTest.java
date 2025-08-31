package com.team.sorting.loader;

import com.team.sorting.model.Barrel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link BarrelLoader}.
 * Tests loading of barrels from files and parsing individual lines.
 */
class BarrelLoaderTest {

    /**
     * Tests that a valid file is loaded correctly.
     * The test file contains 3 valid barrel entries.
     */
    @Test
    void testLoadValidFile() {
        EntityLoader<Barrel> loader = new BarrelLoader();
        List<Barrel> barrels = loader.load("com/team/sorting/loader/testBarrelLoader.txt");
        assertEquals(3, barrels.size(), "The loader should parse all valid barrel entries from the file.");
    }

    /**
     * Tests that parsing a line with an incorrect number of fields throws IllegalArgumentException.
     * Example: only 2 fields instead of 3.
     */
    @Test
    void testParseLineThrowsOnInvalidFormat() {
        BarrelLoader loader = new BarrelLoader();
        String invalidLine = "200,OIL"; // Only two fields instead of three
        assertThrows(IllegalArgumentException.class, () -> loader.parseLine(invalidLine),
                "Parsing a line with the wrong number of fields should throw IllegalArgumentException.");
    }

    /**
     * Tests that parsing a line with an invalid number format throws NumberFormatException.
     * Example: first field should be an integer (volume), but it's not.
     */
    @Test
    void testParseLineThrowsOnWrongNumberFormat() {
        BarrelLoader loader = new BarrelLoader();
        String invalidLine = "abc,OIL,WOOD"; // 'abc' is not a valid integer
        assertThrows(NumberFormatException.class, () -> loader.parseLine(invalidLine),
                "Parsing a line with a non-integer volume should throw NumberFormatException.");
    }
}

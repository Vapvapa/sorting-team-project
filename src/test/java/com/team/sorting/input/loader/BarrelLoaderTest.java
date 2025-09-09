package com.team.sorting.input.loader;

import com.team.sorting.model.Barrel;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link BarrelLoader}.
 * Tests loading of barrels from files, including handling of invalid lines.
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
     * Tests that lines with an incorrect number of fields are skipped.
     */
    @Test
    void testLoadSkipsInvalidFormat() throws Exception {
        Path tempFile = Files.createTempFile("invalid-format-barrel", ".txt");
        Files.writeString(tempFile,
                "200,OIL,WOOD\n" +     // valid
                "300,OIL\n" +          // invalid (only 2 fields)
                "150,WATER,METAL\n"    // valid
        );

        EntityLoader<Barrel> loader = new BarrelLoader();
        List<Barrel> barrels = loader.load(tempFile.toString());

        assertEquals(2, barrels.size(), "Only valid lines should be loaded when some lines have wrong field count.");
        Files.deleteIfExists(tempFile);
    }

    /**
     * Tests that lines with invalid number formats are skipped.
     */
    @Test
    void testLoadSkipsWrongNumberFormat() throws Exception {
        Path tempFile = Files.createTempFile("invalid-number-barrel", ".txt");
        Files.writeString(tempFile,
                "200,OIL,WOOD\n" +    // valid
                "abc,OIL,WOOD\n" +    // invalid (volume is not a number)
                "150,WATER,METAL\n"   // valid
        );

        EntityLoader<Barrel> loader = new BarrelLoader();
        List<Barrel> barrels = loader.load(tempFile.toString());

        assertEquals(2, barrels.size(), "Lines with invalid number format should be skipped.");
        Files.deleteIfExists(tempFile);
    }
}

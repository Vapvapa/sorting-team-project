package com.team.sorting.loader;

import com.team.sorting.input.loader.EntityLoader;
import com.team.sorting.input.loader.HumanLoader;
import com.team.sorting.model.Human;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link HumanLoader}.
 * Tests loading of humans from files, including handling of invalid lines.
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
     * Tests that lines with an incorrect number of fields are skipped.
     */
    @Test
    void testLoadSkipsInvalidFormat() throws Exception {
        Path tempFile = Files.createTempFile("invalid-format-human", ".txt");
        Files.writeString(tempFile,
                "MALE,25,Smith\n" +     // valid
                "FEMALE,30\n" +         // invalid (only 2 fields)
                "FEMALE,40,Johnson\n"   // valid
        );

        EntityLoader<Human> loader = new HumanLoader();
        List<Human> humans = loader.load(tempFile.toString());

        assertEquals(2, humans.size(), "Only valid lines should be loaded when some lines have wrong field count.");
        Files.deleteIfExists(tempFile);
    }

    /**
     * Tests that lines with invalid number formats (age not an integer) are skipped.
     */
    @Test
    void testLoadSkipsInvalidAge() throws Exception {
        Path tempFile = Files.createTempFile("invalid-age-human", ".txt");
        Files.writeString(tempFile,
                "MALE,25,Smith\n" +       // valid
                "MALE,abc,Johnson\n" +    // invalid (age is not a number)
                "FEMALE,40,White\n"       // valid
        );

        EntityLoader<Human> loader = new HumanLoader();
        List<Human> humans = loader.load(tempFile.toString());

        assertEquals(2, humans.size(), "Lines with invalid age should be skipped.");
        Files.deleteIfExists(tempFile);
    }
}

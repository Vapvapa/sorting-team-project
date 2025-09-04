package com.team.sorting.input.loader;

import com.team.sorting.model.Animal;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AnimalLoader} class.
 * These tests verify file loading and parsing behavior.
 */
class AnimalLoaderTest {

    /**
     * Tests that the loader correctly reads a valid file and returns the expected number of animals.
     */
    @Test
    void testLoadValidFile() {
        EntityLoader<Animal> loader = new AnimalLoader();
        List<Animal> animals = loader.load("com/team/sorting/loader/testAnimalLoader.txt");
        assertEquals(3, animals.size(), "The file should contain exactly 3 valid animals.");
    }

    /**
     * Tests that when the file contains an invalid line (wrong number of fields),
     * the loader skips it and successfully loads the valid ones.
     */
    @Test
    void testLoadSkipsInvalidFormat() throws Exception {
        Path tempFile = Files.createTempFile("invalid-format", ".txt");
        Files.writeString(tempFile,
                "DOG,BROWN,SHORT,true\n" + // valid
                "DOG,BROWN\n" +            // invalid (only 2 fields)
                "CAT,BLUE,LONG,false\n"    // valid
        );

        EntityLoader<Animal> loader = new AnimalLoader();
        List<Animal> animals = loader.load(tempFile.toString());

        assertEquals(2, animals.size(), "Only valid lines should be loaded.");
        Files.deleteIfExists(tempFile);
    }

    /**
     * Tests that when the file contains an invalid enum or boolean value,
     * the loader skips that line and loads the valid ones.
     */
    @Test
    void testLoadSkipsUnknownEnumOrBoolean() throws Exception {
        Path tempFile = Files.createTempFile("invalid-values", ".txt");
        Files.writeString(tempFile,
                "DOG,BROWN,SHORT,true\n" + // valid
                "CAT,BLUE,SHORT,YES\n" +   // invalid (YES instead of true/false)
                "HORSE,RED,LONG,false\n"   // valid
        );

        EntityLoader<Animal> loader = new AnimalLoader();
        List<Animal> animals = loader.load(tempFile.toString());

        assertEquals(2, animals.size(), "Lines with invalid enum or boolean should be skipped.");
        Files.deleteIfExists(tempFile);
    }
}

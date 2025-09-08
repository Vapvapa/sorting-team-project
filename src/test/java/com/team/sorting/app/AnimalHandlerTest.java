package com.team.sorting.app;

import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.model.Animal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link AnimalHandler}.
 */
class AnimalHandlerTest {

    private static AnimalHandler handler;

    @BeforeAll
    static void setup() {
        handler = new AnimalHandler();
    }

    /**
     * Test loading animals from file using inputChoice = 1.
     */
    @Test
    void testLoadEntitiesFromFile() {
        List<Animal> animals = handler.loadEntities(1, 0, new Scanner(System.in));
        assertNotNull(animals, "Loaded animals should not be null");
        assertFalse(animals.isEmpty(), "File should contain at least one animal");
        assertNotNull(animals.get(0), "First animal should not be null");
    }

    /**
     * Test generating animals using inputChoice = 2.
     */
    @Test
    void testLoadEntitiesGenerate() {
        int count = 5;
        List<Animal> animals = handler.loadEntities(2, count, new Scanner(System.in));
        assertNotNull(animals);
        assertEquals(count, animals.size(), "Should generate requested number of animals");
    }

    /**
     * Test manual input using inputChoice = 3.
     */
    @Test
    void testLoadEntitiesManualInput() {
        String input = "DOG BROWN SHORT true\nCAT BLUE LONG false\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputterFactory.getLoader(InputterFactory.EntityType.ANIMAL)
                .input(2); // Checking that the call does not crash
    }

    /**
     * Test that invalid input choice throws exception.
     */
    @Test
    void testLoadEntitiesInvalidChoice() {
        assertThrows(IllegalArgumentException.class,
                () -> handler.loadEntities(99, 0, new Scanner(System.in)));
    }

    /**
     * Test searchAndPrint with valid input.
     */
    @Test
    void testSearchAndPrint() {
        List<Animal> animals = List.of(
                new Animal.Builder().species(Animal.Species.DOG).eyeColor(Animal.EyeColor.BROWN)
                        .fur(Animal.Fur.SHORT).eatsBun(true).build(),
                new Animal.Builder().species(Animal.Species.CAT).eyeColor(Animal.EyeColor.BLUE)
                        .fur(Animal.Fur.LONG).eatsBun(false).build()
        );

        String simulatedInput = "DOG BROWN SHORT true\nDOG\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.searchAndPrint(animals, scanner);

        String output = outContent.toString();
        assertTrue(output.contains("Original Animals:"));
        assertTrue(output.contains("Sorted Animals:"));
        assertTrue(output.contains("Found at index"));
    }

    /**
     * Test searchAndPrint with invalid search input (wrong format).
     */
    @Test
    void testSearchAndPrintInvalidInputFormat() {
        List<Animal> animals = List.of(
                new Animal.Builder().species(Animal.Species.DOG).eyeColor(Animal.EyeColor.BROWN)
                        .fur(Animal.Fur.SHORT).eatsBun(true).build()
        );

        String simulatedInput = "DOG BROWN\n"; // only 2 fields
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.searchAndPrint(animals, scanner);

        String output = outContent.toString();
        assertTrue(output.contains("Invalid format"));
    }
}

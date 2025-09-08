package com.team.sorting.app;

import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.model.Human;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link HumanHandler}.
 */
class HumanHandlerTest {

    private static HumanHandler handler;

    @BeforeAll
    static void setup() {
        handler = new HumanHandler();
    }

    /**
     * Test loading humans from file using inputChoice = 1.
     */
    @Test
    void testLoadEntitiesFromFile() {
        List<Human> humans = handler.loadEntities(1, 0, new Scanner(System.in));
        assertNotNull(humans, "Loaded humans should not be null");
        assertFalse(humans.isEmpty(), "File should contain at least one human");
        assertNotNull(humans.get(0), "First human should not be null");
    }

    /**
     * Test generating humans using inputChoice = 2.
     */
    @Test
    void testLoadEntitiesGenerate() {
        int count = 5;
        List<Human> humans = handler.loadEntities(2, count, new Scanner(System.in));
        assertNotNull(humans);
        assertEquals(count, humans.size(), "Should generate requested number of humans");
    }

    /**
     * Test manual input using inputChoice = 3.
     */
    @Test
    void testLoadEntitiesManualInput() {
        String input = "MALE 25 Smith\nFEMALE 30 Johnson\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputterFactory.getLoader(InputterFactory.EntityType.HUMAN)
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
        List<Human> humans = List.of(
                new Human.Builder().gender(Human.Gender.MALE).age(25).lastName("Smith").build(),
                new Human.Builder().gender(Human.Gender.FEMALE).age(30).lastName("Johnson").build()
        );

        String simulatedInput = "MALE 25 Smith\n25\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.searchAndPrint(humans, scanner);

        String output = outContent.toString();
        assertTrue(output.contains("Original Humans:"));
        assertTrue(output.contains("Sorted Humans:"));
        assertTrue(output.contains("Found at index"));
    }

    /**
     * Test searchAndPrint with invalid search input (wrong format).
     */
    @Test
    void testSearchAndPrintInvalidInputFormat() {
        List<Human> humans = List.of(
                new Human.Builder().gender(Human.Gender.MALE).age(25).lastName("Smith").build()
        );

        String simulatedInput = "MALE Smith\n"; // only 2 fields instead of 3
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.searchAndPrint(humans, scanner);

        String output = outContent.toString();
        assertTrue(output.contains("Invalid format"));
    }
}

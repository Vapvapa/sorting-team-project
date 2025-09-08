package com.team.sorting.app;

import com.team.sorting.input.inputter.InputterFactory;
import com.team.sorting.model.Barrel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link BarrelHandler}.
 */
class BarrelHandlerTest {

    private static BarrelHandler handler;

    @BeforeAll
    static void setup() {
        handler = new BarrelHandler();
    }

    /**
     * Test loading barrels from file using inputChoice = 1.
     */
    @Test
    void testLoadEntitiesFromFile() {
        List<Barrel> barrels = handler.loadEntities(1, 0, new Scanner(System.in));
        assertNotNull(barrels, "Loaded barrels should not be null");
        assertFalse(barrels.isEmpty(), "File should contain at least one barrel");
        assertNotNull(barrels.get(0), "First barrel should not be null");
    }

    /**
     * Test generating barrels using inputChoice = 2.
     */
    @Test
    void testLoadEntitiesGenerate() {
        int count = 5;
        List<Barrel> barrels = handler.loadEntities(2, count, new Scanner(System.in));
        assertNotNull(barrels);
        assertEquals(count, barrels.size(), "Should generate requested number of barrels");
    }

    /**
     * Test manual input using inputChoice = 3.
     */
    @Test
    void testLoadEntitiesManualInput() {
        String input = "50 WATER WOOD\n100 OIL METAL\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputterFactory.getLoader(InputterFactory.EntityType.BARREL)
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
        List<Barrel> barrels = List.of(
                new Barrel.Builder().volume(50).material(Barrel.Material.WOOD)
                        .storedMaterial(Barrel.StoredMaterial.WATER).build(),
                new Barrel.Builder().volume(100).material(Barrel.Material.METAL)
                        .storedMaterial(Barrel.StoredMaterial.OIL).build()
        );

        String simulatedInput = "50 WOOD WATER\n50\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.searchAndPrint(barrels, scanner);

        String output = outContent.toString();
        assertTrue(output.contains("Original Barrels:"));
        assertTrue(output.contains("Sorted Barrels:"));
        assertTrue(output.contains("Found at index"));
    }

    /**
     * Test searchAndPrint with invalid search input (wrong format).
     */
    @Test
    void testSearchAndPrintInvalidInputFormat() {
        List<Barrel> barrels = List.of(
                new Barrel.Builder().volume(50).material(Barrel.Material.WOOD)
                        .storedMaterial(Barrel.StoredMaterial.WATER).build()
        );

        String simulatedInput = "50 WOOD\n"; // only 2 fields instead of 3
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.searchAndPrint(barrels, scanner);

        String output = outContent.toString();
        assertTrue(output.contains("Invalid format"));
    }
}

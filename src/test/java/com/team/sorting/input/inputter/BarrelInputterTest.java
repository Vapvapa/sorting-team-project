package com.team.sorting.input.inputter;

import com.team.sorting.model.Barrel;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BarrelInputter} class.
 * These tests verify that input from the console is correctly parsed into Barrel objects.
 */
class BarrelInputterTest {

    /**
     * Tests that valid input is correctly parsed into a Barrel object.
     */
    @Test
    void testReadOneValidInput() {
        String input = "200 OIL WOOD";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BarrelInputter inputter = new BarrelInputter();
        Barrel barrel = inputter.readOne(scanner);

        assertEquals(200, barrel.getVolume());
        assertEquals(Barrel.StoredMaterial.OIL, barrel.getStoredMaterial());
        assertEquals(Barrel.Material.WOOD, barrel.getMaterial());
    }

    /**
     * Tests that input with minimum volume is correctly parsed.
     */
    @Test
    void testReadOneMinimumVolume() {
        String input = "50 WATER METAL";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BarrelInputter inputter = new BarrelInputter();
        Barrel barrel = inputter.readOne(scanner);

        assertEquals(50, barrel.getVolume());
        assertEquals(Barrel.StoredMaterial.WATER, barrel.getStoredMaterial());
        assertEquals(Barrel.Material.METAL, barrel.getMaterial());
    }

    /**
     * Tests that input with maximum volume is correctly parsed.
     */
    @Test
    void testReadOneMaximumVolume() {
        String input = "1000 WINE PLASTIC";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BarrelInputter inputter = new BarrelInputter();
        Barrel barrel = inputter.readOne(scanner);

        assertEquals(1000, barrel.getVolume());
        assertEquals(Barrel.StoredMaterial.WINE, barrel.getStoredMaterial());
        assertEquals(Barrel.Material.PLASTIC, barrel.getMaterial());
    }

    /**
     * Tests that non-integer volume input throws NumberFormatException.
     */
    @Test
    void testReadOneInvalidVolumeThrows() {
        String input = "INVALID OIL WOOD";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BarrelInputter inputter = new BarrelInputter();

        assertThrows(NumberFormatException.class, () -> inputter.readOne(scanner));
    }

    /**
     * Tests that an invalid enum input for stored material throws IllegalArgumentException.
     */
    @Test
    void testReadOneInvalidStoredMaterialThrows() {
        String input = "200 INVALID WOOD";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BarrelInputter inputter = new BarrelInputter();

        assertThrows(IllegalArgumentException.class, () -> inputter.readOne(scanner));
    }

    /**
     * Tests that an invalid enum input for material throws IllegalArgumentException.
     */
    @Test
    void testReadOneInvalidMaterialThrows() {
        String input = "200 OIL INVALID";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BarrelInputter inputter = new BarrelInputter();

        assertThrows(IllegalArgumentException.class, () -> inputter.readOne(scanner));
    }
}

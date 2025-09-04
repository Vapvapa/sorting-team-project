package com.team.sorting.input.inputter;

import com.team.sorting.model.Animal;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AnimalInputter} class.
 * These tests verify that input from the console is correctly parsed into Animal objects.
 */
class AnimalInputterTest {

    /**
     * Tests that valid input is correctly parsed into an Animal object.
     */
    @Test
    void testReadOneValidInput() {
        String input = "DOG BROWN SHORT true";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AnimalInputter inputter = new AnimalInputter();
        Animal animal = inputter.readOne(scanner);

        assertEquals(Animal.Species.DOG, animal.getSpecies());
        assertEquals(Animal.EyeColor.BROWN, animal.getEyeColor());
        assertEquals(Animal.Fur.SHORT, animal.getFur());
        assertTrue(animal.getEatsBun());
    }

    /**
     * Tests that input with "false" boolean value is correctly parsed.
     */
    @Test
    void testReadOneFalseBoolean() {
        String input = "CAT BLUE LONG false";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AnimalInputter inputter = new AnimalInputter();
        Animal animal = inputter.readOne(scanner);

        assertEquals(Animal.Species.CAT, animal.getSpecies());
        assertEquals(Animal.EyeColor.BLUE, animal.getEyeColor());
        assertEquals(Animal.Fur.LONG, animal.getFur());
        assertFalse(animal.getEatsBun());
    }

    /**
     * Tests that an invalid boolean input throws IllegalArgumentException.
     */
    @Test
    void testReadOneInvalidBooleanThrows() {
        String input = "DOG GREEN CURLY yes";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AnimalInputter inputter = new AnimalInputter();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> inputter.readOne(scanner));
        assertTrue(exception.getMessage().contains("Invalid boolean value"));
    }

    /**
     * Tests that an invalid enum input for species throws IllegalArgumentException.
     */
    @Test
    void testReadOneInvalidSpeciesThrows() {
        String input = "INVALID GREEN CURLY true";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AnimalInputter inputter = new AnimalInputter();

        assertThrows(IllegalArgumentException.class, () -> inputter.readOne(scanner));
    }

    /**
     * Tests that an invalid enum input for eye color throws IllegalArgumentException.
     */
    @Test
    void testReadOneInvalidEyeColorThrows() {
        String input = "DOG INVALID SHORT true";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AnimalInputter inputter = new AnimalInputter();

        assertThrows(IllegalArgumentException.class, () -> inputter.readOne(scanner));
    }

    /**
     * Tests that an invalid enum input for fur type throws IllegalArgumentException.
     */
    @Test
    void testReadOneInvalidFurThrows() {
        String input = "DOG GREEN INVALID true";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AnimalInputter inputter = new AnimalInputter();

        assertThrows(IllegalArgumentException.class, () -> inputter.readOne(scanner));
    }
}

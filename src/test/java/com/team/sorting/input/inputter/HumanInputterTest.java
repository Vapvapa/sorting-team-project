package com.team.sorting.input.inputter;

import com.team.sorting.model.Human;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HumanInputter} class.
 * These tests verify that input from the console is correctly parsed into Human objects.
 */
class HumanInputterTest {

    /**
     * Tests that valid input is correctly parsed into a Human object.
     */
    @Test
    void testReadOneValidInput() {
        String input = "MALE 25 Smith";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        HumanInputter inputter = new HumanInputter();
        Human human = inputter.readOne(scanner);

        assertEquals(Human.Gender.MALE, human.getGender());
        assertEquals(25, human.getAge());
        assertEquals("Smith", human.getLastName());
    }

    /**
     * Tests that input with FEMALE gender is correctly parsed.
     */
    @Test
    void testReadOneFemaleGender() {
        String input = "FEMALE 30 Johnson";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        HumanInputter inputter = new HumanInputter();
        Human human = inputter.readOne(scanner);

        assertEquals(Human.Gender.FEMALE, human.getGender());
        assertEquals(30, human.getAge());
        assertEquals("Johnson", human.getLastName());
    }

    /**
     * Tests that non-integer age input throws NumberFormatException.
     */
    @Test
    void testReadOneInvalidAgeThrows() {
        String input = "MALE INVALID Smith";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        HumanInputter inputter = new HumanInputter();

        assertThrows(NumberFormatException.class, () -> inputter.readOne(scanner));
    }

    /**
     * Tests that an invalid enum input for gender throws IllegalArgumentException.
     */
    @Test
    void testReadOneInvalidGenderThrows() {
        String input = "INVALID 25 Smith";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        HumanInputter inputter = new HumanInputter();

        assertThrows(IllegalArgumentException.class, () -> inputter.readOne(scanner));
    }

    /**
     * Tests that last name with spaces is correctly parsed as a single token.
     */
    @Test
    void testReadOneLastNameWithSpaces() {
        // Scanner reads only first token, so multi-word last name must be input as one token
        String input = "MALE 40 \"Van-Der-Smith\"";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        HumanInputter inputter = new HumanInputter();
        Human human = inputter.readOne(scanner);

        assertEquals(Human.Gender.MALE, human.getGender());
        assertEquals(40, human.getAge());
        assertEquals("\"Van-Der-Smith\"", human.getLastName());
    }
}

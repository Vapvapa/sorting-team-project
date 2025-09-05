package com.team.sorting.input.generator;

import com.team.sorting.model.Human;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HumanGenerator} class.
 * These tests verify that generated humans have valid random values and the correct count.
 */
class HumanGeneratorTest {

    /**
     * Tests that the generator produces the exact number of humans requested.
     */
    @Test
    void testGeneratesCorrectCount() {
        HumanGenerator generator = new HumanGenerator();
        int count = 10;
        List<Human> humans = generator.generate(count);

        assertEquals(count, humans.size(), "Generator should return exactly " + count + " humans.");
    }

    /**
     * Tests that generated humans contain only valid enum values, ages in 18–80, and valid last names.
     */
    @Test
    void testGeneratedHumansContainValidValues() {
        HumanGenerator generator = new HumanGenerator();
        List<Human> humans = generator.generate(50);

        String[] validLastNames = {"Black", "Brown", "Gray", "Green", "White"};

        for (Human human : humans) {
            assertNotNull(human.getGender(), "Gender must not be null.");
            assertTrue(EnumSet.allOf(Human.Gender.class).contains(human.getGender()));

            assertTrue(human.getAge() >= 18 && human.getAge() <= 80, "Age must be between 18 and 80.");

            assertNotNull(human.getLastName(), "Last name must not be null.");
            assertTrue(List.of(validLastNames).contains(human.getLastName()), "Last name must be one of the predefined set.");
        }
    }

    /**
     * Tests that generating zero humans returns an empty list.
     */
    @Test
    void testGenerateZeroHumans() {
        HumanGenerator generator = new HumanGenerator();
        List<Human> humans = generator.generate(0);

        assertNotNull(humans, "Result list must not be null.");
        assertTrue(humans.isEmpty(), "Generating 0 humans should return an empty list.");
    }

    /**
     * Tests that generated humans have varied ages across the allowed range.
     */
    @Test
    void testGeneratedHumansHaveVariedAges() {
        HumanGenerator generator = new HumanGenerator();
        List<Human> humans = generator.generate(100);

        boolean hasMin = humans.stream().anyMatch(h -> h.getAge() <= 20);
        boolean hasMax = humans.stream().anyMatch(h -> h.getAge() >= 78);

        assertTrue(hasMin && hasMax, "Generated humans should include ages across the range 18–80.");
    }
}

package com.team.sorting.input.generator;

import com.team.sorting.model.Animal;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AnimalGenerator} class.
 * These tests verify that generated animals have valid random values and the correct count.
 */
class AnimalGeneratorTest {

    /**
     * Tests that the generator produces the exact number of animals requested.
     */
    @Test
    void testGeneratesCorrectCount() {
        AnimalGenerator generator = new AnimalGenerator();
        int count = 10;
        List<Animal> animals = generator.generate(count);

        assertEquals(count, animals.size(), "Generator should return exactly " + count + " animals.");
    }

    /**
     * Tests that generated animals contain only valid enum values for all fields.
     */
    @Test
    void testGeneratedAnimalsContainValidValues() {
        AnimalGenerator generator = new AnimalGenerator();
        List<Animal> animals = generator.generate(50);

        for (Animal animal : animals) {
            assertNotNull(animal.getSpecies(), "Species must not be null.");
            assertTrue(EnumSet.allOf(Animal.Species.class).contains(animal.getSpecies()));

            assertNotNull(animal.getEyeColor(), "Eye color must not be null.");
            assertTrue(EnumSet.allOf(Animal.EyeColor.class).contains(animal.getEyeColor()));

            assertNotNull(animal.getFur(), "Fur type must not be null.");
            assertTrue(EnumSet.allOf(Animal.Fur.class).contains(animal.getFur()));
        }
    }

    /**
     * Tests that the bun-eating preference is distributed across true/false values
     * (not always the same) when generating multiple animals.
     */
    @Test
    void testGeneratedAnimalsHaveVariedBunPreference() {
        AnimalGenerator generator = new AnimalGenerator();
        List<Animal> animals = generator.generate(100);

        boolean hasTrue = animals.stream().anyMatch(Animal::getEatsBun);
        boolean hasFalse = animals.stream().anyMatch(a -> !a.getEatsBun());

        assertTrue(hasTrue && hasFalse,
                "Generated animals should include both bun eaters and non-bun eaters.");
    }

    /**
     * Tests that generating zero animals returns an empty list.
     */
    @Test
    void testGenerateZeroAnimals() {
        AnimalGenerator generator = new AnimalGenerator();
        List<Animal> animals = generator.generate(0);

        assertNotNull(animals, "Result list must not be null.");
        assertTrue(animals.isEmpty(), "Generating 0 animals should return an empty list.");
    }
}

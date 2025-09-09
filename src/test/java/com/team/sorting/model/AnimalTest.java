package com.team.sorting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Animal} class.
 */
class AnimalTest {

    /**
     * Tests that the builder correctly creates an {@link Animal} with given properties.
     */
    @Test
    void testBuilderCreatesAnimal() {
        Animal animal = new Animal.Builder()
                .species(Animal.Species.CAT)
                .eyeColor(Animal.EyeColor.GREEN)
                .fur(Animal.Fur.LONG)
                .eatsBun(true)
                .build();

        assertEquals(Animal.Species.CAT, animal.getSpecies());
        assertEquals(Animal.EyeColor.GREEN, animal.getEyeColor());
        assertEquals(Animal.Fur.LONG, animal.getFur());
        assertTrue(animal.getEatsBun());
    }

    /**
     * Tests that setters and getters work as expected.
     */
    @Test
    void testSettersAndGetters() {
        Animal animal = new Animal.Builder().build();

        animal.setSpecies(Animal.Species.DOG);
        animal.setEyeColor(Animal.EyeColor.BROWN);
        animal.setFur(Animal.Fur.SHORT);
        animal.setEatsBun(false);

        assertEquals(Animal.Species.DOG, animal.getSpecies());
        assertEquals(Animal.EyeColor.BROWN, animal.getEyeColor());
        assertEquals(Animal.Fur.SHORT, animal.getFur());
        assertFalse(animal.getEatsBun());
    }

    /**
     * Tests the {@link Animal#compareTo(Animal)} method to ensure correct ordering.
     */
    @Test
    void testCompareToOrdering() {
        Animal cat = new Animal.Builder()
                .species(Animal.Species.CAT)
                .eyeColor(Animal.EyeColor.BLUE)
                .fur(Animal.Fur.LONG)
                .eatsBun(false)
                .build();

        Animal dog = new Animal.Builder()
                .species(Animal.Species.DOG)
                .eyeColor(Animal.EyeColor.BROWN)
                .fur(Animal.Fur.SHORT)
                .eatsBun(true)
                .build();

        Animal catCopy = new Animal.Builder()
                .species(Animal.Species.CAT)
                .eyeColor(Animal.EyeColor.BLUE)
                .fur(Animal.Fur.LONG)
                .eatsBun(false)
                .build();

        assertTrue(cat.compareTo(dog) < 0);
        assertTrue(dog.compareTo(cat) > 0);
        assertEquals(0, cat.compareTo(catCopy));
    }

    /**
     * Tests the {@link Animal#compareTo(Animal)} method when some fields differ.
     */
    @Test
    void testCompareToDifferentFields() {
        Animal a1 = new Animal.Builder()
                .species(Animal.Species.CAT)
                .eyeColor(Animal.EyeColor.BLUE)
                .fur(Animal.Fur.CURLY)
                .eatsBun(false)
                .build();

        Animal a2 = new Animal.Builder()
                .species(Animal.Species.CAT)
                .eyeColor(Animal.EyeColor.BLUE)
                .fur(Animal.Fur.CURLY)
                .eatsBun(true)
                .build();

        assertTrue(a1.compareTo(a2) < 0);
        assertTrue(a2.compareTo(a1) > 0);
    }

    /**
     * Tests that {@link Animal#toString()} contains all required fields.
     */
    @Test
    void testToStringContainsAllFields() {
        Animal animal = new Animal.Builder()
                .species(Animal.Species.RABBIT)
                .eyeColor(Animal.EyeColor.YELLOW)
                .fur(Animal.Fur.NONE)
                .eatsBun(true)
                .build();

        String str = animal.toString();
        assertTrue(str.contains("RABBIT"));
        assertTrue(str.contains("eyes YELLOW"));
        assertTrue(str.contains("fur NONE"));
        assertTrue(str.contains("likes buns"));
        assertTrue(str.contains("ready for life!"));
    }
}

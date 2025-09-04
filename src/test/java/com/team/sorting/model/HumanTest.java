package com.team.sorting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Human} class.
 */
class HumanTest {

    /**
     * Tests that the builder correctly creates a {@link Human} with given properties.
     */
    @Test
    void testBuilderCreatesHuman() {
        Human human = new Human.Builder()
                .gender(Human.Gender.MALE)
                .age(25)
                .lastName("Ivanov")
                .build();

        assertEquals(Human.Gender.MALE, human.getGender());
        assertEquals(25, human.getAge());
        assertEquals("Ivanov", human.getLastName());
    }

    /**
     * Tests that setters and getters work as expected.
     */
    @Test
    void testSettersAndGetters() {
        Human human = new Human.Builder().build();

        human.setGender(Human.Gender.FEMALE);
        human.setAge(30);
        human.setLastName("Petrova");

        assertEquals(Human.Gender.FEMALE, human.getGender());
        assertEquals(30, human.getAge());
        assertEquals("Petrova", human.getLastName());
    }

    /**
     * Tests the {@link Human#compareTo(Human)} method to ensure correct ordering.
     */
    @Test
    void testCompareToOrdering() {
        Human h1 = new Human.Builder()
                .gender(Human.Gender.MALE)
                .age(20)
                .lastName("Ivanov")
                .build();

        Human h2 = new Human.Builder()
                .gender(Human.Gender.FEMALE)
                .age(22)
                .lastName("Petrova")
                .build();

        Human h1Copy = new Human.Builder()
                .gender(Human.Gender.MALE)
                .age(20)
                .lastName("Ivanov")
                .build();

        assertTrue(h1.compareTo(h2) < 0);   // "Ivanov" < "Petrova"
        assertTrue(h2.compareTo(h1) > 0);
        assertEquals(0, h1.compareTo(h1Copy));
    }

    /**
     * Tests the {@link Human#compareTo(Human)} method when some fields differ.
     */
    @Test
    void testCompareToDifferentFields() {
        Human a1 = new Human.Builder()
                .gender(Human.Gender.MALE)
                .age(25)
                .lastName("Sidorov")
                .build();

        Human a2 = new Human.Builder()
                .gender(Human.Gender.MALE)
                .age(30)
                .lastName("Sidorov")
                .build();

        assertTrue(a1.compareTo(a2) < 0);  // younger < older
        assertTrue(a2.compareTo(a1) > 0);
    }

    /**
     * Tests that {@link Human#compareTo(Human)} handles nulls correctly.
     */
    @Test
    void testCompareToWithNull() {
        Human h = new Human.Builder()
                .gender(Human.Gender.MALE)
                .age(40)
                .lastName("Sergeev")
                .build();

        assertTrue(h.compareTo(null) > 0);
    }

    /**
     * Tests that {@link Human#toString()} contains all required fields.
     */
    @Test
    void testToStringContainsAllFields() {
        Human human = new Human.Builder()
                .gender(Human.Gender.FEMALE)
                .age(27)
                .lastName("Smirnova")
                .build();

        String str = human.toString();
        assertTrue(str.contains("FEMALE"));
        assertTrue(str.contains("возраст 27"));
        assertTrue(str.contains("фамилия Smirnova"));
    }
}

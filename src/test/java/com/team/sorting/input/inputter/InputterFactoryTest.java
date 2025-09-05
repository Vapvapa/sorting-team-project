package com.team.sorting.input.inputter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link InputterFactory} class.
 * Verifies that the factory returns the correct inputter instances and maintains singleton behavior.
 */
class InputterFactoryTest {

    /**
     * Tests that the inputter returned for ANIMAL is not null and is an instance of AnimalInputter.
     */
    @Test
    void testGetInputterForAnimal() {
        EntityInputter<?> inputter = InputterFactory.getLoader(InputterFactory.EntityType.ANIMAL);
        assertNotNull(inputter, "Inputter for ANIMAL should not be null");
        assertTrue(inputter instanceof AnimalInputter, "Inputter for ANIMAL should be an instance of AnimalInputter");
    }

    /**
     * Tests that the inputter returned for BARREL is not null and is an instance of BarrelInputter.
     */
    @Test
    void testGetInputterForBarrel() {
        EntityInputter<?> inputter = InputterFactory.getLoader(InputterFactory.EntityType.BARREL);
        assertNotNull(inputter, "Inputter for BARREL should not be null");
        assertTrue(inputter instanceof BarrelInputter, "Inputter for BARREL should be an instance of BarrelInputter");
    }

    /**
     * Tests that the inputter returned for HUMAN is not null and is an instance of HumanInputter.
     */
    @Test
    void testGetInputterForHuman() {
        EntityInputter<?> inputter = InputterFactory.getLoader(InputterFactory.EntityType.HUMAN);
        assertNotNull(inputter, "Inputter for HUMAN should not be null");
        assertTrue(inputter instanceof HumanInputter, "Inputter for HUMAN should be an instance of HumanInputter");
    }

    /**
     * Tests that passing null as an entity type returns null.
     */
    @Test
    void testGetInputterForInvalidTypeReturnsNull() {
        InputterFactory.EntityType invalidType = null;
        EntityInputter<?> inputter = InputterFactory.getLoader(invalidType);
        assertNull(inputter, "Inputter for null type should be null");
    }

    /**
     * Tests that inputters returned for the same entity type are the same instance (singleton behavior).
     */
    @Test
    void testSingletonBehaviorOfInputters() {
        EntityInputter<?> firstAnimalInputter = InputterFactory.getLoader(InputterFactory.EntityType.ANIMAL);
        EntityInputter<?> secondAnimalInputter = InputterFactory.getLoader(InputterFactory.EntityType.ANIMAL);

        EntityInputter<?> firstBarrelInputter = InputterFactory.getLoader(InputterFactory.EntityType.BARREL);
        EntityInputter<?> secondBarrelInputter = InputterFactory.getLoader(InputterFactory.EntityType.BARREL);

        EntityInputter<?> firstHumanInputter = InputterFactory.getLoader(InputterFactory.EntityType.HUMAN);
        EntityInputter<?> secondHumanInputter = InputterFactory.getLoader(InputterFactory.EntityType.HUMAN);

        assertSame(firstAnimalInputter, secondAnimalInputter, "AnimalInputter should be singleton");
        assertSame(firstBarrelInputter, secondBarrelInputter, "BarrelInputter should be singleton");
        assertSame(firstHumanInputter, secondHumanInputter, "HumanInputter should be singleton");
    }
}

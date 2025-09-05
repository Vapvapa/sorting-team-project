package com.team.sorting.input.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link GeneratorFactory} class.
 * Verifies that the factory returns the correct generator instances and maintains singleton behavior.
 */
class GeneratorFactoryTest {

    /**
     * Tests that the generator returned for ANIMAL is not null and is an instance of AnimalGenerator.
     */
    @Test
    void testGetGeneratorForAnimal() {
        EntityGenerator<?> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.ANIMAL);
        assertNotNull(generator, "Generator for ANIMAL should not be null");
        assertTrue(generator instanceof AnimalGenerator, "Generator for ANIMAL should be an instance of AnimalGenerator");
    }

    /**
     * Tests that the generator returned for BARREL is not null and is an instance of BarrelGenerator.
     */
    @Test
    void testGetGeneratorForBarrel() {
        EntityGenerator<?> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.BARREL);
        assertNotNull(generator, "Generator for BARREL should not be null");
        assertTrue(generator instanceof BarrelGenerator, "Generator for BARREL should be an instance of BarrelGenerator");
    }

    /**
     * Tests that the generator returned for HUMAN is not null and is an instance of HumanGenerator.
     */
    @Test
    void testGetGeneratorForHuman() {
        EntityGenerator<?> generator = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.HUMAN);
        assertNotNull(generator, "Generator for HUMAN should not be null");
        assertTrue(generator instanceof HumanGenerator, "Generator for HUMAN should be an instance of HumanGenerator");
    }

    /**
     * Tests that passing null as an entity type returns null.
     */
    @Test
    void testGetGeneratorForInvalidTypeReturnsNull() {
        GeneratorFactory.EntityType invalidType = null;
        EntityGenerator<?> generator = GeneratorFactory.getGenerator(invalidType);
        assertNull(generator, "Generator for null type should be null");
    }

    /**
     * Tests that generators returned for the same entity type are the same instance (singleton behavior).
     */
    @Test
    void testSingletonBehaviorOfGenerators() {
        EntityGenerator<?> firstAnimalGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.ANIMAL);
        EntityGenerator<?> secondAnimalGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.ANIMAL);

        EntityGenerator<?> firstBarrelGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.BARREL);
        EntityGenerator<?> secondBarrelGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.BARREL);

        EntityGenerator<?> firstHumanGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.HUMAN);
        EntityGenerator<?> secondHumanGen = GeneratorFactory.getGenerator(GeneratorFactory.EntityType.HUMAN);

        assertSame(firstAnimalGen, secondAnimalGen, "AnimalGenerator should be singleton");
        assertSame(firstBarrelGen, secondBarrelGen, "BarrelGenerator should be singleton");
        assertSame(firstHumanGen, secondHumanGen, "HumanGenerator should be singleton");
    }
}

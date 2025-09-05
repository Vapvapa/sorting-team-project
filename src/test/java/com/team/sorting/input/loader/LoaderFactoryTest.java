package com.team.sorting.input.loader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link LoaderFactory} class.
 * Verifies that the factory returns the correct loader instances and maintains singleton behavior.
 */
class LoaderFactoryTest {

    /**
     * Tests that the loader returned for ANIMAL is not null and is an instance of AnimalLoader.
     */
    @Test
    void testGetLoaderForAnimal() {
        EntityLoader<?> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.ANIMAL);
        assertNotNull(loader, "Loader for ANIMAL should not be null");
        assertTrue(loader instanceof AnimalLoader, "Loader for ANIMAL should be an instance of AnimalLoader");
    }

    /**
     * Tests that the loader returned for BARREL is not null and is an instance of BarrelLoader.
     */
    @Test
    void testGetLoaderForBarrel() {
        EntityLoader<?> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.BARREL);
        assertNotNull(loader, "Loader for BARREL should not be null");
        assertTrue(loader instanceof BarrelLoader, "Loader for BARREL should be an instance of BarrelLoader");
    }

    /**
     * Tests that the loader returned for HUMAN is not null and is an instance of HumanLoader.
     */
    @Test
    void testGetLoaderForHuman() {
        EntityLoader<?> loader = LoaderFactory.getLoader(LoaderFactory.EntityType.HUMAN);
        assertNotNull(loader, "Loader for HUMAN should not be null");
        assertTrue(loader instanceof HumanLoader, "Loader for HUMAN should be an instance of HumanLoader");
    }

    /**
     * Tests that passing null as an entity type returns null.
     */
    @Test
    void testGetLoaderForInvalidTypeReturnsNull() {
        LoaderFactory.EntityType invalidType = null;
        EntityLoader<?> loader = LoaderFactory.getLoader(invalidType);
        assertNull(loader, "Loader for null type should be null");
    }

    /**
     * Tests that loaders returned for the same entity type are the same instance (singleton behavior).
     */
    @Test
    void testSingletonBehaviorOfLoaders() {
        EntityLoader<?> firstAnimalLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.ANIMAL);
        EntityLoader<?> secondAnimalLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.ANIMAL);

        EntityLoader<?> firstBarrelLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.BARREL);
        EntityLoader<?> secondBarrelLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.BARREL);

        EntityLoader<?> firstHumanLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.HUMAN);
        EntityLoader<?> secondHumanLoader = LoaderFactory.getLoader(LoaderFactory.EntityType.HUMAN);

        assertSame(firstAnimalLoader, secondAnimalLoader, "AnimalLoader should be singleton");
        assertSame(firstBarrelLoader, secondBarrelLoader, "BarrelLoader should be singleton");
        assertSame(firstHumanLoader, secondHumanLoader, "HumanLoader should be singleton");
    }
}

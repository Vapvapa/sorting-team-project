package com.team.sorting.input.generator;

import java.util.EnumMap;
import java.util.Map;

/**
 * A factory class for creating and providing entity generators.
 */
public class GeneratorFactory {

    /**
     * Enumeration of supported entity types.
     */
    public enum EntityType {
        ANIMAL, BARREL, HUMAN
    }

    /**
     * A map storing generators for each entity type.
     */
    private static final Map<EntityType, EntityGenerator<?>> GENERATORS = new EnumMap<>(EntityType.class);

    // Static initialization of generators
    static {
        GENERATORS.put(EntityType.ANIMAL, new AnimalGenerator());
        GENERATORS.put(EntityType.BARREL, new BarrelGenerator());
        GENERATORS.put(EntityType.HUMAN, new HumanGenerator());
    }

    /**
     * Retrieves the generator associated with the specified entity type.
     *
     * @param type The type of entity for which to get a generator.
     * @param <T> The type of entity.
     * @return The corresponding EntityGenerator instance.
     */
    @SuppressWarnings("unchecked")
    public static <T> EntityGenerator<T> getGenerator(EntityType type) {
        return (EntityGenerator<T>) GENERATORS.get(type);
    }
}

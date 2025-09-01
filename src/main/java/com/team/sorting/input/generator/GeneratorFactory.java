package com.team.sorting.input.generator;

import java.util.EnumMap;
import java.util.Map;

public class GeneratorFactory {

    public enum EntityType {
        ANIMAL, BARREL, HUMAN
    }

    private static final Map<EntityType, EntityGenerator<?>> GENERATORS = new EnumMap<>(EntityType.class);

    static {
        GENERATORS.put(EntityType.ANIMAL, new AnimalGenerator());
        GENERATORS.put(EntityType.BARREL, new BarrelGenerator());
        GENERATORS.put(EntityType.HUMAN, new HumanGenerator());
    }

    @SuppressWarnings("unchecked")
    public static <T> EntityGenerator<T> getGenerator(EntityType type) {
        return (EntityGenerator<T>) GENERATORS.get(type);
    }
}

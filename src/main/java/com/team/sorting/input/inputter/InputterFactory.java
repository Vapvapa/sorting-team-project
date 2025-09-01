package com.team.sorting.input.inputter;

import java.util.EnumMap;
import java.util.Map;

public class InputterFactory {

    public enum EntityType {
        ANIMAL, BARREL, HUMAN
    }

    private static final Map<EntityType, EntityInputter<?>> LOADERS = new EnumMap<>(EntityType.class);

    static {
        LOADERS.put(EntityType.ANIMAL, new AnimalInputter());
        LOADERS.put(EntityType.BARREL, new BarrelInputter());
        LOADERS.put(EntityType.HUMAN, new HumanInputter());
    }

    @SuppressWarnings("unchecked")
    public static <T> EntityInputter<T> getLoader(EntityType type) {
        return (EntityInputter<T>) LOADERS.get(type);
    }
}



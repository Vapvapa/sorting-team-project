package com.team.sorting.loader;

import java.util.EnumMap;
import java.util.Map;

/**
 * A factory class to provide the correct {@link EntityLoader} based on the specified {@link EntityType}.
 * It uses a pre-initialized map to ensure efficient retrieval of loaders.
 */
public class LoaderFactory {

    /**
     * An enum representing the supported entity types for loading.
     */
    public enum EntityType {
        ANIMAL, BARREL, HUMAN
    }

    /**
     * A map to store and quickly retrieve the corresponding loader for each entity type.
     */
    private static final Map<EntityType, EntityLoader<?>> LOADERS = new EnumMap<>(EntityType.class);

    static {
        LOADERS.put(EntityType.ANIMAL, new AnimalLoader());
        LOADERS.put(EntityType.BARREL, new BarrelLoader());
        LOADERS.put(EntityType.HUMAN, new HumanLoader());
    }

    /**
     * Retrieves the appropriate EntityLoader for the given entity type.
     *
     * @param type The type of entity for which to get the loader.
     * @param <T> The generic type of the entity.
     * @return The corresponding EntityLoader instance.
     */
    @SuppressWarnings("unchecked")
    public static <T> EntityLoader<T> getLoader(EntityType type) {
        return (EntityLoader<T>) LOADERS.get(type);
    }
}

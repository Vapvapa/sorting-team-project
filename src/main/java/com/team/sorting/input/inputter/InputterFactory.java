package com.team.sorting.input.inputter;

import java.util.EnumMap;
import java.util.Map;

/**
 * A factory class for creating and providing entity inputters.
 * Maintains a mapping between entity types and their corresponding inputters.
 * <p>
 * <b>Thread safety:</b> the static map {@link #LOADERS} is initialized once and is safe for
 * concurrent read access. However, the inputter instances themselves (e.g., {@link AnimalInputter},
 * {@link BarrelInputter}, {@link HumanInputter}) are <b>not fully thread-safe</b> for parallel
 * console input. To prevent conflicts during simultaneous input from multiple threads, it is
 * necessary to synchronize or use a lock (e.g., {@link java.util.concurrent.locks.ReentrantLock})
 * when calling their input methods.
 */
public class InputterFactory {

    /**
     * Enumeration of supported entity types.
     */
    public enum EntityType {
        ANIMAL, BARREL, HUMAN
    }

    /**
     * A map storing inputters for each entity type.
     */
    private static final Map<EntityType, EntityInputter<?>> LOADERS = new EnumMap<>(EntityType.class);

    // Static initialization of inputters
    static {
        LOADERS.put(EntityType.ANIMAL, new AnimalInputter());
        LOADERS.put(EntityType.BARREL, new BarrelInputter());
        LOADERS.put(EntityType.HUMAN, new HumanInputter());
    }

    /**
     * Retrieves the inputter associated with the specified entity type.
     *
     * @param type The type of entity for which to get an inputter.
     * @param <T> The type of entity.
     * @return The corresponding EntityInputter instance.
     */
    @SuppressWarnings("unchecked")
    public static <T> EntityInputter<T> getLoader(EntityType type) {
        return (EntityInputter<T>) LOADERS.get(type);
    }
}

package com.team.sorting.app;

import java.util.EnumMap;
import java.util.Map;

/**
 * Strategy class for selecting the appropriate {@link EntityHandler}
 * implementation based on the type of entity.
 * <p>
 * Supports handlers for animals, barrels, and humans.
 */
public class HandlerStrategy {

    /**
     * Enumeration of supported handler types.
     */
    public enum HandlerType {
        ANIMAL,   // Handler for Animal entities
        BARREL,   // Handler for Barrel entities
        HUMAN     // Handler for Human entities
    }

    /**
     * Map storing the handler instances for each handler type.
     * Uses {@link EnumMap} for efficient storage.
     */
    private static final Map<HandlerType, EntityHandler<?>> HANDLERS = new EnumMap<>(HandlerType.class);

    // Static block to initialize handler instances
    static {
        HANDLERS.put(HandlerType.ANIMAL, new AnimalHandler());
        HANDLERS.put(HandlerType.BARREL, new BarrelHandler());
        HANDLERS.put(HandlerType.HUMAN, new HumanHandler());
    }

    /**
     * Returns the {@link EntityHandler} corresponding to the specified {@link HandlerType}.
     *
     * @param type the type of handler to retrieve
     * @param <T> the type of entity handled
     * @return the handler instance for the specified type
     * @throws NullPointerException if {@code type} is null or no handler exists for it
     */
    @SuppressWarnings("unchecked")
    public static <T> EntityHandler<T> getHandler(HandlerType type) {
        return (EntityHandler<T>) HANDLERS.get(type);
    }
}

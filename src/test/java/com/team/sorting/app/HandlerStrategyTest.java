package com.team.sorting.app;

import com.team.sorting.app.HandlerStrategy.HandlerType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link HandlerStrategy}.
 */
class HandlerStrategyTest {

    /**
     * Tests that the handler returned for ANIMAL is not null and is an instance of AnimalHandler.
     */
    @Test
    void testGetHandlerForAnimal() {
        EntityHandler<?> handler = HandlerStrategy.getHandler(HandlerType.ANIMAL);
        assertNotNull(handler, "Handler for ANIMAL should not be null");
        assertTrue(handler instanceof AnimalHandler, "Handler for ANIMAL should be an instance of AnimalHandler");
    }

    /**
     * Tests that the handler returned for BARREL is not null and is an instance of BarrelHandler.
     */
    @Test
    void testGetHandlerForBarrel() {
        EntityHandler<?> handler = HandlerStrategy.getHandler(HandlerType.BARREL);
        assertNotNull(handler, "Handler for BARREL should not be null");
        assertTrue(handler instanceof BarrelHandler, "Handler for BARREL should be an instance of BarrelHandler");
    }

    /**
     * Tests that the handler returned for HUMAN is not null and is an instance of HumanHandler.
     */
    @Test
    void testGetHandlerForHuman() {
        EntityHandler<?> handler = HandlerStrategy.getHandler(HandlerType.HUMAN);
        assertNotNull(handler, "Handler for HUMAN should not be null");
        assertTrue(handler instanceof HumanHandler, "Handler for HUMAN should be an instance of HumanHandler");
    }

    /**
     * Tests that handlers returned for the same type are always the same instance (singleton behavior).
     */
    @Test
    void testSingletonBehaviorOfHandlers() {
        EntityHandler<?> firstAnimalHandler = HandlerStrategy.getHandler(HandlerType.ANIMAL);
        EntityHandler<?> secondAnimalHandler = HandlerStrategy.getHandler(HandlerType.ANIMAL);

        EntityHandler<?> firstBarrelHandler = HandlerStrategy.getHandler(HandlerType.BARREL);
        EntityHandler<?> secondBarrelHandler = HandlerStrategy.getHandler(HandlerType.BARREL);

        EntityHandler<?> firstHumanHandler = HandlerStrategy.getHandler(HandlerType.HUMAN);
        EntityHandler<?> secondHumanHandler = HandlerStrategy.getHandler(HandlerType.HUMAN);

        assertSame(firstAnimalHandler, secondAnimalHandler, "AnimalHandler should be singleton");
        assertSame(firstBarrelHandler, secondBarrelHandler, "BarrelHandler should be singleton");
        assertSame(firstHumanHandler, secondHumanHandler, "HumanHandler should be singleton");
    }

    /**
     * Tests that requesting a handler with null type returns null.
     */
    @Test
    void testGetHandlerWithNullType() {
        EntityHandler<?> handler = HandlerStrategy.getHandler(null);
        assertNull(handler, "Handler for null type should be null");
    }
}

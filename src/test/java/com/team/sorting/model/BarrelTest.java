package com.team.sorting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Barrel} class.
 */
class BarrelTest {

    /**
     * Tests that the builder correctly creates a {@link Barrel} with given properties.
     */
    @Test
    void testBuilderCreatesBarrel() {
        Barrel barrel = new Barrel.Builder()
                .volume(50)
                .storedMaterial(Barrel.StoredMaterial.WINE)
                .material(Barrel.Material.WOOD)
                .build();

        assertEquals(50, barrel.getVolume());
        assertEquals(Barrel.StoredMaterial.WINE, barrel.getStoredMaterial());
        assertEquals(Barrel.Material.WOOD, barrel.getMaterial());
    }

    /**
     * Tests that setters and getters work as expected.
     */
    @Test
    void testSettersAndGetters() {
        Barrel barrel = new Barrel.Builder().build();

        barrel.setVolume(100);
        barrel.setStoredMaterial(Barrel.StoredMaterial.BEER);
        barrel.setMaterial(Barrel.Material.METAL);

        assertEquals(100, barrel.getVolume());
        assertEquals(Barrel.StoredMaterial.BEER, barrel.getStoredMaterial());
        assertEquals(Barrel.Material.METAL, barrel.getMaterial());
    }

    /**
     * Tests the {@link Barrel#compareTo(Barrel)} method to ensure correct ordering.
     */
    @Test
    void testCompareToOrdering() {
        Barrel b1 = new Barrel.Builder()
                .volume(10)
                .storedMaterial(Barrel.StoredMaterial.WATER)
                .material(Barrel.Material.PLASTIC)
                .build();

        Barrel b2 = new Barrel.Builder()
                .volume(20)
                .storedMaterial(Barrel.StoredMaterial.WATER)
                .material(Barrel.Material.PLASTIC)
                .build();

        Barrel b1Copy = new Barrel.Builder()
                .volume(10)
                .storedMaterial(Barrel.StoredMaterial.WATER)
                .material(Barrel.Material.PLASTIC)
                .build();

        assertTrue(b1.compareTo(b2) < 0);
        assertTrue(b2.compareTo(b1) > 0);
        assertEquals(0, b1.compareTo(b1Copy));
    }

    /**
     * Tests the {@link Barrel#compareTo(Barrel)} method when some fields differ.
     */
    @Test
    void testCompareToDifferentFields() {
        Barrel b1 = new Barrel.Builder()
                .volume(30)
                .storedMaterial(Barrel.StoredMaterial.HONEY)
                .material(Barrel.Material.WOOD)
                .build();

        Barrel b2 = new Barrel.Builder()
                .volume(30)
                .storedMaterial(Barrel.StoredMaterial.HONEY)
                .material(Barrel.Material.METAL)
                .build();

        assertNotEquals(0, b1.compareTo(b2));
    }

    /**
     * Tests that {@link Barrel#toString()} contains all required fields.
     */
    @Test
    void testToStringContainsAllFields() {
        Barrel barrel = new Barrel.Builder()
                .volume(200)
                .storedMaterial(Barrel.StoredMaterial.OIL)
                .material(Barrel.Material.METAL)
                .build();

        String str = barrel.toString();
        assertTrue(str.contains("объём 200"));
        assertTrue(str.contains("содержит OIL"));
        assertTrue(str.contains("материал изготовителя METAL"));
    }
}

package com.team.sorting.input.generator;

import com.team.sorting.model.Barrel;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BarrelGenerator} class.
 * These tests verify that generated barrels have valid random values and the correct count.
 */
class BarrelGeneratorTest {

    /**
     * Tests that the generator produces the exact number of barrels requested.
     */
    @Test
    void testGeneratesCorrectCount() {
        BarrelGenerator generator = new BarrelGenerator();
        int count = 10;
        List<Barrel> barrels = generator.generate(count);

        assertEquals(count, barrels.size(), "Generator should return exactly " + count + " barrels.");
    }

    /**
     * Tests that generated barrels contain only valid enum values and volume in range 50–1000.
     */
    @Test
    void testGeneratedBarrelsContainValidValues() {
        BarrelGenerator generator = new BarrelGenerator();
        List<Barrel> barrels = generator.generate(50);

        for (Barrel barrel : barrels) {
            assertTrue(barrel.getVolume() >= 50 && barrel.getVolume() <= 1000,
                    "Volume must be between 50 and 1000 liters.");

            assertNotNull(barrel.getStoredMaterial(), "Stored material must not be null.");
            assertTrue(EnumSet.allOf(Barrel.StoredMaterial.class).contains(barrel.getStoredMaterial()));

            assertNotNull(barrel.getMaterial(), "Construction material must not be null.");
            assertTrue(EnumSet.allOf(Barrel.Material.class).contains(barrel.getMaterial()));
        }
    }

    /**
     * Tests that generating zero barrels returns an empty list.
     */
    @Test
    void testGenerateZeroBarrels() {
        BarrelGenerator generator = new BarrelGenerator();
        List<Barrel> barrels = generator.generate(0);

        assertNotNull(barrels, "Result list must not be null.");
        assertTrue(barrels.isEmpty(), "Generating 0 barrels should return an empty list.");
    }

    /**
     * Tests that generator produces varied volumes within the allowed range.
     * Ensures it is not always the same value.
     */
    @Test
    void testGeneratedBarrelsHaveVariedVolumes() {
        BarrelGenerator generator = new BarrelGenerator();
        List<Barrel> barrels = generator.generate(100);

        boolean hasMin = barrels.stream().anyMatch(b -> b.getVolume() <= 100);
        boolean hasMax = barrels.stream().anyMatch(b -> b.getVolume() >= 900);

        assertTrue(hasMin && hasMax,
                "Generated barrels should include varied volumes across the range.");
    }
}

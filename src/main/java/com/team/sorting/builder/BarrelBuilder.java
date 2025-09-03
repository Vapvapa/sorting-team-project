package com.team.sorting.builder;

import com.team.sorting.model.Barrel;

/**
 * A builder class for creating Barrel objects using the builder pattern.
 * <p>
 * This class provides a fluent interface for setting barrel properties
 * and constructing Barrel objects step by step.
 */
public class BarrelBuilder {

    /**
     * The barrel instance being built.
     */
    private final Barrel barrel;

    /**
     * Constructs a new BarrelBuilder with the specified barrel instance.
     *
     * @param barrel The barrel instance to build upon.
     */
    public BarrelBuilder(Barrel barrel) {
        this.barrel = barrel;
    }

    /**
     * Sets the volume of the barrel.
     *
     * @param volume The volume to set for the barrel in liters.
     * @return This builder instance for method chaining.
     */
    public BarrelBuilder volume(int volume) {
        barrel.setVolume(volume);
        return this;
    }

    /**
     * Sets the material stored inside the barrel.
     *
     * @param storedMaterial The material to store in the barrel.
     * @return This builder instance for method chaining.
     */
    public BarrelBuilder storedMaterial(Barrel.StoredMaterial storedMaterial) {
        barrel.setStoredMaterial(storedMaterial);
        return this;
    }

    /**
     * Sets the material used to construct the barrel.
     *
     * @param material The construction material to set for the barrel.
     * @return This builder instance for method chaining.
     */
    public BarrelBuilder material(Barrel.Material material) {
        barrel.setMaterial(material);
        return this;
    }

    /**
     * Builds and returns the configured barrel instance.
     *
     * @return The built barrel instance.
     */
    public Barrel build() {
        return barrel;
    }
}

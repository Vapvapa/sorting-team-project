package com.team.sorting.builder;

import com.team.sorting.model.Barrel;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Builder for creating Barrel objects.
 */
public class BarrelBuilder {

    /**
     * The barrel instance being built.
     */
    private final Barrel barrel;

    /**
     * Lock to ensure thread-safe access to builder methods.
     */
    private final ReentrantLock lock = new ReentrantLock();

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
        lock.lock();
        try {
            barrel.setVolume(volume);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the material stored inside the barrel.
     *
     * @param storedMaterial The material to store in the barrel.
     * @return This builder instance for method chaining.
     */
    public BarrelBuilder storedMaterial(Barrel.StoredMaterial storedMaterial) {
        lock.lock();
        try {
            barrel.setStoredMaterial(storedMaterial);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the material used to construct the barrel.
     *
     * @param material The construction material to set for the barrel.
     * @return This builder instance for method chaining.
     */
    public BarrelBuilder material(Barrel.Material material) {
        lock.lock();
        try {
            barrel.setMaterial(material);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Builds and returns the configured barrel instance.
     *
     * @return The built barrel instance.
     */
    public Barrel build() {
        lock.lock();
        try {
            return barrel;
        } finally {
            lock.unlock();
        }
    }
}

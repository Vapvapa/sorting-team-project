package com.team.sorting.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Barrel with volume, stored material, and construction material.
 */
public class Barrel {

    /**
     * Lock to ensure thread-safe access to fields.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Enumeration of materials that can be stored in barrels.
     */
    public enum StoredMaterial {
        WATER, OIL, WINE, BEER, HONEY
    }

    /**
     * Enumeration of materials used to construct barrels.
     */
    public enum Material {
        WOOD, METAL, PLASTIC
    }

    /**
     * The volume of the barrel in liters.
     */
    private int volume;

    /**
     * The material stored inside the barrel.
     */
    private StoredMaterial storedMaterial;

    /**
     * The material used to construct the barrel.
     */
    private Material material;

    /**
     * Gets the volume of the barrel.
     *
     * @return The volume of the barrel in liters.
     */
    public int getVolume() {
        lock.lock();
        try {
            return volume;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the material stored inside the barrel.
     *
     * @return The stored material in the barrel.
     */
    public StoredMaterial getStoredMaterial() {
        lock.lock();
        try {
            return storedMaterial;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the material used to construct the barrel.
     *
     * @return The construction material of the barrel.
     */
    public Material getMaterial() {
        lock.lock();
        try {
            return material;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the volume of the barrel.
     *
     * @param volume The volume to set for the barrel in liters.
     */
    public void setVolume(int volume) {
        lock.lock();
        try {
            this.volume = volume;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the material stored inside the barrel.
     *
     * @param storedMaterial The material to store in the barrel.
     */
    public void setStoredMaterial(StoredMaterial storedMaterial) {
        lock.lock();
        try {
            this.storedMaterial = storedMaterial;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the material used to construct the barrel.
     *
     * @param material The construction material to set for the barrel.
     */
    public void setMaterial(Material material) {
        lock.lock();
        try {
            this.material = material;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns a string representation of the barrel in Russian.
     * The format includes volume, stored material, and construction material.
     *
     * @return A string representation of the barrel.
     */
    @Override
    public String toString() {
        lock.lock();
        try {
            return "Бочка: " +
                    "объём " + volume + "л, " +
                    (storedMaterial != null ? "содержит " + storedMaterial + ", " : "") +
                    (material != null ? "материал изготовителя " + material : "");
        } finally {
            lock.unlock();
        }
    }
}

package com.team.sorting.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Barrel with volume, stored material, and construction material.
 * Implements {@link Comparable} to allow natural ordering by
 * volume → storedMaterial → material.
 */
public class Barrel implements Comparable<Barrel> {

    /**
     * Lock to ensure thread-safe access to fields.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Enumeration of materials that can be stored in barrels.
     */
    public enum StoredMaterial {
        BEER, HONEY, OIL, WATER, WINE
    }

    /**
     * Enumeration of materials used to construct barrels.
     */
    public enum Material {
        METAL, PLASTIC, WOOD
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
     * Private constructor used by the {@link Builder} to create a Barrel instance.
     *
     * @param builder The Builder containing the configuration for this Barrel.
     */
    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.material = builder.material;
    }

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
     * Defines natural ordering:
     * <ul>
     *   <li>First by volume (ascending)</li>
     *   <li>Then by stored material</li>
     *   <li>Then by barrel material</li>
     * </ul>
     *
     * @param other the other barrel to compare with
     * @return negative if this is less, positive if greater, zero if equal
     */
    @Override
    public int compareTo(Barrel other) {
        if (other == null) return 1;

        int cmp = Integer.compare(this.volume, other.volume);
        if (cmp != 0) return cmp;

        cmp = this.storedMaterial.compareTo(other.storedMaterial);
        if (cmp != 0) return cmp;

        return this.material.compareTo(other.material);
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

    /**
     * Thread-safe Builder Inner Class for creating Barrel instances.
     */
    public static class Builder {

        /**
         * Lock to ensure thread-safe access to builder methods.
         */
        private final ReentrantLock lock = new ReentrantLock();

        /**
         * The volume to be set in the Barrel being built.
         */
        private int volume;

        /**
         * The stored material to be set in the Barrel being built.
         */
        private StoredMaterial storedMaterial;

        /**
         * The construction material to be set in the Barrel being built.
         */
        private Material material;

        /**
         * Sets the volume of the barrel.
         *
         * @param volume The volume to set in liters.
         * @return This builder instance for method chaining.
         */
        public Builder volume(int volume) {
            lock.lock();
            try {
                this.volume = volume;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Sets the stored material of the barrel.
         *
         * @param storedMaterial The material to store.
         * @return This builder instance for method chaining.
         */
        public Builder storedMaterial(StoredMaterial storedMaterial) {
            lock.lock();
            try {
                this.storedMaterial = storedMaterial;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Sets the construction material of the barrel.
         *
         * @param material The construction material to set.
         * @return This builder instance for method chaining.
         */
        public Builder material(Material material) {
            lock.lock();
            try {
                this.material = material;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Builds a new Barrel instance with the configured fields.
         *
         * @return A new Barrel object.
         */
        public Barrel build() {
            lock.lock();
            try {
                return new Barrel(this);
            } finally {
                lock.unlock();
            }
        }
    }
}

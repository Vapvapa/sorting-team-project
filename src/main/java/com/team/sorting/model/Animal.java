package com.team.sorting.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents an animal with species, eye color, fur type, and food preferences.
 * Implements {@link Comparable} to allow natural ordering by
 * species → eyeColor → fur.
 */
public class Animal implements Comparable<Animal> {

    /**
     * Lock to ensure thread-safe access to fields.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Enumeration of animal species supported by the system.
     */
    public enum Species {
        CAT, DOG, HORSE, RABBIT
    }

    /**
     * Enumeration of possible eye colors for animals.
     */
    public enum EyeColor {
        BLUE, BROWN, GREEN, YELLOW
    }

    /**
     * Enumeration of fur types that animals can have.
     */
    public enum Fur {
        SHORT, LONG, CURLY, NONE
    }

    /**
     * The species of the animal.
     */
    private Species species;

    /**
     * The eye color of the animal.
     */
    private EyeColor eyeColor;

    /**
     * The type of fur the animal has.
     */
    private Fur fur;

    /**
     * Indicates whether the animal eats buns (bread).
     */
    private boolean eatsBun;

    /**
     * Private constructor used by the {@link Builder} to create an Animal instance.
     *
     * @param builder The Builder containing the configuration for this Animal.
     */
    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.fur = builder.fur;
        this.eatsBun = builder.eatsBun;
    }

    /**
     * Gets the species of the animal.
     *
     * @return The species of the animal.
     */
    public Species getSpecies() {
        lock.lock();
        try {
            return species;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the eye color of the animal.
     *
     * @return The eye color of the animal.
     */
    public EyeColor getEyeColor() {
        lock.lock();
        try {
            return eyeColor;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the fur type of the animal.
     *
     * @return The fur type of the animal.
     */
    public Fur getFur() {
        lock.lock();
        try {
            return fur;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Checks if the animal eats buns.
     *
     * @return true if the animal eats buns, false otherwise.
     */
    public boolean getEatsBun() {
        lock.lock();
        try {
            return eatsBun;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the species of the animal.
     *
     * @param species The species to set for the animal.
     */
    public void setSpecies(Species species) {
        lock.lock();
        try {
            this.species = species;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the eye color of the animal.
     *
     * @param eyeColor The eye color to set for the animal.
     */
    public void setEyeColor(EyeColor eyeColor) {
        lock.lock();
        try {
            this.eyeColor = eyeColor;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the fur type of the animal.
     *
     * @param fur The fur type to set for the animal.
     */
    public void setFur(Fur fur) {
        lock.lock();
        try {
            this.fur = fur;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets whether the animal eats buns.
     *
     * @param eatsBun true if the animal eats buns, false otherwise.
     */
    public void setEatsBun(boolean eatsBun) {
        lock.lock();
        try {
            this.eatsBun = eatsBun;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Compares two nullable objects safely.
     * <p>
     * Null is considered less than any non-null value.
     *
     * @param <T> the type of objects to compare
     * @param o1  first object
     * @param o2  second object
     * @return negative if o1 < o2, 0 if equal, positive if o1 > o2
     */
    public static <T extends Comparable<T>> int compareNullable(T o1, T o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return -1;
        if (o2 == null) return 1;
        return o1.compareTo(o2);
    }

    /**
     * Defines natural ordering: species → eyeColor → fur → eatsBun.
     *
     * @param other the other animal to compare with
     * @return negative if this < other, 0 if equal, positive if this > other
     */
    @Override
    public int compareTo(Animal other) {
        if (other == null) return 1;

        int cmp = compareNullable(this.species, other.species);
        if (cmp != 0) return cmp;

        cmp = compareNullable(this.eyeColor, other.eyeColor);
        if (cmp != 0) return cmp;

        cmp = compareNullable(this.fur, other.fur);
        if (cmp != 0) return cmp;

        return Boolean.compare(this.eatsBun, other.eatsBun);
    }

    /**
     * Returns a string representation of the animal in Russian.
     * The format includes all the animal's characteristics in a readable format.
     *
     * @return A string representation of the animal.
     */
    @Override
    public String toString() {
        lock.lock();
        try {
            return "Animal: " +
                    (species != null ? species + ", " : "") +
                    (eyeColor != null ? "eyes " + eyeColor + ", " : "") +
                    (fur != null ? "fur " + fur + ", " : "") +
                    (eatsBun ? "likes buns, " : "does not like buns, ") +
                    "ready for life!";
        } finally {
            lock.unlock();
        }
    }

    /**
     * Thread-safe Builder Inner Class for creating Animal instances.
     */
    public static class Builder {

        /**
         * Lock to ensure thread-safe access to builder methods.
         */
        private final ReentrantLock lock = new ReentrantLock();

        /**
         * The species to be set in the Animal being built.
         */
        private Species species;

        /**
         * The eye color to be set in the Animal being built.
         */
        private EyeColor eyeColor;

        /**
         * The fur type to be set in the Animal being built.
         */
        private Fur fur;

        /**
         * Whether the Animal eats buns to be set in the Animal being built.
         */
        private boolean eatsBun;

        /**
         * Sets the species of the animal.
         *
         * @param species The species to set.
         * @return This builder instance for method chaining.
         */
        public Builder species(Species species) {
            lock.lock();
            try {
                this.species = species;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Sets the eye color of the animal.
         *
         * @param eyeColor The eye color to set.
         * @return This builder instance for method chaining.
         */
        public Builder eyeColor(EyeColor eyeColor) {
            lock.lock();
            try {
                this.eyeColor = eyeColor;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Sets the fur type of the animal.
         *
         * @param fur The fur type to set.
         * @return This builder instance for method chaining.
         */
        public Builder fur(Fur fur) {
            lock.lock();
            try {
                this.fur = fur;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Sets whether the animal eats buns.
         *
         * @param eatsBun true if the animal eats buns, false otherwise.
         * @return This builder instance for method chaining.
         */
        public Builder eatsBun(boolean eatsBun) {
            lock.lock();
            try {
                this.eatsBun = eatsBun;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Builds a new Animal instance with the configured fields.
         *
         * @return A new Animal object.
         */
        public Animal build() {
            lock.lock();
            try {
                return new Animal(this);
            } finally {
                lock.unlock();
            }
        }
    }
}

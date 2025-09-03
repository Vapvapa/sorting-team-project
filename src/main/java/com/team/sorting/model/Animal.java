package com.team.sorting.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents an animal with species, eye color, fur type, and food preferences.
 */
public class Animal {

    /**
     * Lock to ensure thread-safe access to fields.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Enumeration of animal species supported by the system.
     */
    public enum Species {
        DOG, CAT, HORSE, RABBIT
    }

    /**
     * Enumeration of possible eye colors for animals.
     */
    public enum EyeColor {
        BROWN, BLUE, GREEN, YELLOW
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
     * Creates a new instance of {@code Animal} with default values.
     */
    public Animal() {
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
    public boolean isEatsBun() {
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
     * Returns a string representation of the animal in Russian.
     * The format includes all the animal's characteristics in a readable format.
     *
     * @return A string representation of the animal.
     */
    @Override
    public String toString() {
        lock.lock();
        try {
            return "Животное: " +
                    (species != null ? species + ", " : "") +
                    (eyeColor != null ? "глаза " + eyeColor + ", " : "") +
                    (fur != null ? "шерсть " + fur + ", " : "") +
                    (eatsBun ? "любит булку, " : "не любит булку, ") +
                    "готово к жизни!";
        } finally {
            lock.unlock();
        }
    }
}

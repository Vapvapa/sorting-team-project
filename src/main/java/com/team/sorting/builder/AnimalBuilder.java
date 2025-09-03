package com.team.sorting.builder;

import com.team.sorting.model.Animal;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Builder for creating Animal objects.
 */
public class AnimalBuilder {

    /**
     * The animal instance being built.
     */
    private final Animal animal;

    /**
     * Lock to ensure thread-safe access to builder methods.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Constructs a new AnimalBuilder with the specified animal instance.
     *
     * @param animal The animal instance to build upon.
     */
    public AnimalBuilder(Animal animal) {
        this.animal = animal;
    }

    /**
     * Sets the species of the animal.
     *
     * @param species The species to set for the animal.
     * @return This builder instance for method chaining.
     */
    public AnimalBuilder species(Animal.Species species) {
        lock.lock();
        try {
            animal.setSpecies(species);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the eye color of the animal.
     *
     * @param eyeColor The eye color to set for the animal.
     * @return This builder instance for method chaining.
     */
    public AnimalBuilder eyeColor(Animal.EyeColor eyeColor) {
        lock.lock();
        try {
            animal.setEyeColor(eyeColor);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the fur type of the animal.
     *
     * @param fur The fur type to set for the animal.
     * @return This builder instance for method chaining.
     */
    public AnimalBuilder fur(Animal.Fur fur) {
        lock.lock();
        try {
            animal.setFur(fur);
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
    public AnimalBuilder eatsBun(boolean eatsBun) {
        lock.lock();
        try {
            animal.setEatsBun(eatsBun);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Builds and returns the configured animal instance.
     *
     * @return The built animal instance.
     */
    public Animal build() {
        lock.lock();
        try {
            return animal;
        } finally {
            lock.unlock();
        }
    }
}

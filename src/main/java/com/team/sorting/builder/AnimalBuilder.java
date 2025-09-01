package com.team.sorting.builder;

import com.team.sorting.model.Animal;

/**
 * A builder class for creating Animal objects using the builder pattern.
 * 
 * This class provides a fluent interface for setting animal properties
 * and constructing Animal objects step by step.
 */
public class AnimalBuilder {

    /**
     * The animal instance being built.
     */
    private final Animal animal;

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
        animal.setSpecies(species);
        return this;
    }

    /**
     * Sets the eye color of the animal.
     *
     * @param eyeColor The eye color to set for the animal.
     * @return This builder instance for method chaining.
     */
    public AnimalBuilder eyeColor(Animal.EyeColor eyeColor) {
        animal.setEyeColor(eyeColor);
        return this;
    }

    /**
     * Sets the fur type of the animal.
     *
     * @param fur The fur type to set for the animal.
     * @return This builder instance for method chaining.
     */
    public AnimalBuilder fur(Animal.Fur fur) {
        animal.setFur(fur);
        return this;
    }

    /**
     * Sets whether the animal eats buns.
     *
     * @param eatsBun true if the animal eats buns, false otherwise.
     * @return This builder instance for method chaining.
     */
    public AnimalBuilder eatsBun(boolean eatsBun) {
        animal.setEatsBun(eatsBun);
        return this;
    }

    /**
     * Builds and returns the configured animal instance.
     *
     * @return The built animal instance.
     */
    public Animal build() {
        return animal;
    }
}

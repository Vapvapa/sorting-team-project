package com.team.sorting.model;

/**
 * Represents an animal entity with various characteristics including species,
 * eye color, fur type, and food preferences.
 * 
 * This class provides a model for storing and managing animal data with
 * appropriate getters and setters for all properties.
 */
public class Animal {

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
     * Gets the species of the animal.
     *
     * @return The species of the animal.
     */
    public Species getSpecies() {
        return species;
    }

    /**
     * Gets the eye color of the animal.
     *
     * @return The eye color of the animal.
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Gets the fur type of the animal.
     *
     * @return The fur type of the animal.
     */
    public Fur getFur() {
        return fur;
    }

    /**
     * Checks if the animal eats buns.
     *
     * @return true if the animal eats buns, false otherwise.
     */
    public boolean isEatsBun() {
        return eatsBun;
    }

    /**
     * Sets the species of the animal.
     *
     * @param species The species to set for the animal.
     */
    public void setSpecies(Species species) {
        this.species = species;
    }

    /**
     * Sets the eye color of the animal.
     *
     * @param eyeColor The eye color to set for the animal.
     */
    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Sets the fur type of the animal.
     *
     * @param fur The fur type to set for the animal.
     */
    public void setFur(Fur fur) {
        this.fur = fur;
    }

    /**
     * Sets whether the animal eats buns.
     *
     * @param eatsBun true if the animal eats buns, false otherwise.
     */
    public void setEatsBun(boolean eatsBun) {
        this.eatsBun = eatsBun;
    }

    /**
     * Returns a string representation of the animal in Russian.
     * The format includes all the animal's characteristics in a readable format.
     *
     * @return A string representation of the animal.
     */
    @Override
    public String toString() {
        return "Животное: " +
                (species != null ? species + ", " : "") +
                (eyeColor != null ? "глаза " + eyeColor + ", " : "") +
                (fur != null ? "шерсть " + fur + ", " : "") +
                (eatsBun ? "любит булку, " : "не любит булку, ") +
                "готово к жизни!";
    }
}

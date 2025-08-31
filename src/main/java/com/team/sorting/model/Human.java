package com.team.sorting.model;

/**
 * Represents a human entity with gender, age, and last name.
 * 
 * This class provides a model for storing and managing human data with
 * appropriate getters and setters for all properties.
 */
public class Human {

    /**
     * Enumeration of gender types for humans.
     */
    public enum Gender {
        MALE, FEMALE
    }

    /**
     * The gender of the human.
     */
    private Gender gender;
    
    /**
     * The age of the human in years.
     */
    private int age;
    
    /**
     * The last name of the human.
     */
    private String lastName;

    /**
     * Gets the gender of the human.
     *
     * @return The gender of the human.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the age of the human.
     *
     * @return The age of the human in years.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the last name of the human.
     *
     * @return The last name of the human.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the gender of the human.
     *
     * @param gender The gender to set for the human.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Sets the age of the human.
     *
     * @param age The age to set for the human in years.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the last name of the human.
     *
     * @param lastName The last name to set for the human.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns a string representation of the human in Russian.
     * The format includes gender, age, and last name.
     *
     * @return A string representation of the human.
     */
    @Override
    public String toString() {
        return "Человек: " +
                (gender != null ? gender + ", " : "") +
                "возраст " + age + ", " +
                (lastName != null ? "фамилия " + lastName : "");
    }
}

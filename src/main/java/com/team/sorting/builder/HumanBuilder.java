package com.team.sorting.builder;

import com.team.sorting.model.Human;

/**
 * A builder class for creating Human objects using the builder pattern.
 * 
 * This class provides a fluent interface for setting human properties
 * and constructing Human objects step by step.
 */
public class HumanBuilder {

    /**
     * The human instance being built.
     */
    private final Human human;

    /**
     * Constructs a new HumanBuilder with the specified human instance.
     *
     * @param human The human instance to build upon.
     */
    public HumanBuilder(Human human) {
        this.human = human;
    }

    /**
     * Sets the gender of the human.
     *
     * @param gender The gender to set for the human.
     * @return This builder instance for method chaining.
     */
    public HumanBuilder gender(Human.Gender gender) {
        human.setGender(gender);
        return this;
    }

    /**
     * Sets the age of the human.
     *
     * @param age The age to set for the human in years.
     * @return This builder instance for method chaining.
     */
    public HumanBuilder age(int age) {
        human.setAge(age);
        return this;
    }

    /**
     * Sets the last name of the human.
     *
     * @param lastName The last name to set for the human.
     * @return This builder instance for method chaining.
     */
    public HumanBuilder lastName(String lastName) {
        human.setLastName(lastName);
        return this;
    }

    /**
     * Builds and returns the configured human instance.
     *
     * @return The built human instance.
     */
    public Human build() {
        return human;
    }
}

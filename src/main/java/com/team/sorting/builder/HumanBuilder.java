package com.team.sorting.builder;

import com.team.sorting.model.Human;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Builder for creating Human objects.
 */
public class HumanBuilder {

    /**
     * The human instance being built.
     */
    private final Human human;

    /**
     * Lock to ensure thread-safe access to builder methods.
     */
    private final ReentrantLock lock = new ReentrantLock();

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
        lock.lock();
        try {
            human.setGender(gender);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the age of the human.
     *
     * @param age The age to set for the human in years.
     * @return This builder instance for method chaining.
     */
    public HumanBuilder age(int age) {
        lock.lock();
        try {
            human.setAge(age);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the last name of the human.
     *
     * @param lastName The last name to set for the human.
     * @return This builder instance for method chaining.
     */
    public HumanBuilder lastName(String lastName) {
        lock.lock();
        try {
            human.setLastName(lastName);
            return this;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Builds and returns the configured human instance.
     *
     * @return The built human instance.
     */
    public Human build() {
        lock.lock();
        try {
            return human;
        } finally {
            lock.unlock();
        }
    }
}

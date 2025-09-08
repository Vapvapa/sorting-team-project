package com.team.sorting.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Human with gender, age, and last name.
 * Implements {@link Comparable} to allow natural ordering by
 * lastName → gender → age.
 */
public class Human implements Comparable<Human> {

    /**
     * Lock to ensure thread-safe access to fields.
     */
    private final ReentrantLock lock = new ReentrantLock();

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
     * Private constructor used by the {@link Builder} to create a Human instance.
     *
     * @param builder The Builder containing the configuration for this Human.
     */
    private Human(Builder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.lastName = builder.lastName;
    }

    /**
     * Gets the gender of the human.
     *
     * @return The gender of the human.
     */
    public Gender getGender() {
        lock.lock();
        try {
            return gender;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the age of the human.
     *
     * @return The age of the human in years.
     */
    public int getAge() {
        lock.lock();
        try {
            return age;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the last name of the human.
     *
     * @return The last name of the human.
     */
    public String getLastName() {
        lock.lock();
        try {
            return lastName;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the gender of the human.
     *
     * @param gender The gender to set for the human.
     */
    public void setGender(Gender gender) {
        lock.lock();
        try {
            this.gender = gender;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the age of the human.
     *
     * @param age The age to set for the human in years.
     */
    public void setAge(int age) {
        lock.lock();
        try {
            this.age = age;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the last name of the human.
     *
     * @param lastName The last name to set for the human.
     */
    public void setLastName(String lastName) {
        lock.lock();
        try {
            this.lastName = lastName;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Defines natural ordering:
     * <ul>
     *   <li>First by last name (alphabetical)</li>
     *   <li>Then by gender</li>
     *   <li>Then by age (ascending)</li>
     * </ul>
     *
     * @param other the other human to compare with
     * @return negative if this is less, positive if greater, zero if equal
     */
    @Override
    public int compareTo(Human other) {
        if (other == null) return 1;

        if (this.lastName != null && other.lastName != null) {
            int cmp = this.lastName.compareTo(other.lastName);
            if (cmp != 0) return cmp;
        } else if (this.lastName != null) {
            return 1;
        } else if (other.lastName != null) {
            return -1;
        }

        int cmp = this.gender.compareTo(other.gender);
        if (cmp != 0) return cmp;

        return Integer.compare(this.age, other.age);
    }

    /**
     * Returns a string representation of the human in Russian.
     * The format includes gender, age, and last name.
     *
     * @return A string representation of the human.
     */
    @Override
    public String toString() {
        lock.lock();
        try {
            return "Human: " +
                    (gender != null ? gender + ", " : "") +
                    "age " + age + ", " +
                    (lastName != null ? "last name " + lastName : "");
        } finally {
            lock.unlock();
        }
    }

    /**
     * Thread-safe Builder Inner Class for creating Human instances.
     */
    public static class Builder {

        /**
         * Lock to ensure thread-safe access to builder methods.
         */
        private final ReentrantLock lock = new ReentrantLock();

        /**
         * The gender to be set in the Human being built.
         */
        private Gender gender;

        /**
         * The age to be set in the Human being built.
         */
        private int age;

        /**
         * The last name to be set in the Human being built.
         */
        private String lastName;


        /**
         * Sets the gender of the human.
         *
         * @param gender The gender to set.
         * @return This builder instance for method chaining.
         */
        public Builder gender(Gender gender) {
            lock.lock();
            try {
                this.gender = gender;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Sets the age of the human.
         *
         * @param age The age to set in years.
         * @return This builder instance for method chaining.
         */
        public Builder age(int age) {
            lock.lock();
            try {
                this.age = age;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Sets the last name of the human.
         *
         * @param lastName The last name to set.
         * @return This builder instance for method chaining.
         */
        public Builder lastName(String lastName) {
            lock.lock();
            try {
                this.lastName = lastName;
                return this;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Builds a new Human instance with the configured fields.
         *
         * @return A new Human object.
         */
        public Human build() {
            lock.lock();
            try {
                return new Human(this);
            } finally {
                lock.unlock();
            }
        }
    }
}

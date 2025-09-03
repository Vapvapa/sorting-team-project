package com.team.sorting.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Human with gender, age, and last name.
 */
public class Human {

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
     * Returns a string representation of the human in Russian.
     * The format includes gender, age, and last name.
     *
     * @return A string representation of the human.
     */
    @Override
    public String toString() {
        lock.lock();
        try {
            return "Человек: " +
                    (gender != null ? gender + ", " : "") +
                    "возраст " + age + ", " +
                    (lastName != null ? "фамилия " + lastName : "");
        } finally {
            lock.unlock();
        }
    }
}

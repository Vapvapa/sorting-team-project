package com.team.sorting.model;

public class Human {

    public enum Gender {
        MALE, FEMALE
    }

    private Gender gender;
    private int age;
    private String lastName;

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Человек: " +
                (gender != null ? gender + ", " : "") +
                "возраст " + age + ", " +
                (lastName != null ? "фамилия " + lastName : "");
    }
}

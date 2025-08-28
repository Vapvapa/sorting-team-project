package com.team.sorting.builder;

import com.team.sorting.model.Human;

public class HumanBuilder {

    private final Human human;

    public HumanBuilder(Human human) {
        this.human = human;
    }

    public HumanBuilder genderMale() {
        human.setGender(Human.Gender.MALE);
        return this;
    }

    public HumanBuilder genderFemale() {
        human.setGender(Human.Gender.FEMALE);
        return this;
    }

    public HumanBuilder genderOther() {
        human.setGender(Human.Gender.OTHER);
        return this;
    }

    public HumanBuilder age(int age) {
        human.setAge(age);
        return this;
    }

    public HumanBuilder lastName(String lastName) {
        human.setLastName(lastName);
        return this;
    }

    public Human build() {
        return human;
    }
}

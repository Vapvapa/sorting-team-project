package com.team.sorting.builder;

import com.team.sorting.model.Animal;

public class AnimalBuilder {

    private final Animal animal;

    public AnimalBuilder(Animal animal) {
        this.animal = animal;
    }

    public AnimalBuilder species(Animal.Species species) {
        animal.setSpecies(species);
        return this;
    }

    public AnimalBuilder eyeColor(Animal.EyeColor eyeColor) {
        animal.setEyeColor(eyeColor);
        return this;
    }

    public AnimalBuilder fur(Animal.Fur fur) {
        animal.setFur(fur);
        return this;
    }

    public AnimalBuilder eatsBun(boolean eatsBun) {
        animal.setEatsBun(eatsBun);
        return this;
    }

    public Animal build() {
        return animal;
    }
}

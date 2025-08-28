package com.team.sorting.builder;

import com.team.sorting.model.Animal;

public class AnimalBuilder {

    private final Animal animal;

    public AnimalBuilder(Animal animal) {
        this.animal = animal;
    }

    public AnimalBuilder speciesDog() {
        animal.setSpecies(Animal.Species.DOG);
        return this;
    }

    public AnimalBuilder speciesCat() {
        animal.setSpecies(Animal.Species.CAT);
        return this;
    }

    public AnimalBuilder speciesHorse() {
        animal.setSpecies(Animal.Species.HORSE);
        return this;
    }

    public AnimalBuilder speciesRabbit() {
        animal.setSpecies(Animal.Species.RABBIT);
        return this;
    }

    public AnimalBuilder eyeBrown() {
        animal.setEyeColor(Animal.EyeColor.BROWN);
        return this;
    }

    public AnimalBuilder eyeBlue() {
        animal.setEyeColor(Animal.EyeColor.BLUE);
        return this;
    }

    public AnimalBuilder eyeGreen() {
        animal.setEyeColor(Animal.EyeColor.GREEN);
        return this;
    }

    public AnimalBuilder eyeYellow() {
        animal.setEyeColor(Animal.EyeColor.YELLOW);
        return this;
    }

    public AnimalBuilder furShort() {
        animal.setFur(Animal.Fur.SHORT);
        return this;
    }

    public AnimalBuilder furLong() {
        animal.setFur(Animal.Fur.LONG);
        return this;
    }

    public AnimalBuilder furCurly() {
        animal.setFur(Animal.Fur.CURLY);
        return this;
    }

    public AnimalBuilder furNone() {
        animal.setFur(Animal.Fur.NONE);
        return this;
    }

    public AnimalBuilder eatsBun() {
        animal.setEatsBun(true);
        return this;
    }

    public AnimalBuilder doesNotEatBun() {
        animal.setEatsBun(false);
        return this;
    }

    public Animal build() {
        return animal;
    }
}

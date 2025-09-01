package com.team.sorting.input.inputter;

import com.team.sorting.builder.AnimalBuilder;
import com.team.sorting.model.Animal;

import java.util.Scanner;

public class AnimalInputter extends AbstractInputter<Animal> {

    @Override
    protected Animal readOne(Scanner scanner) {
        System.out.println("Enter Animal as: species eyeColor fur eatsBun (e.g., DOG BROWN SHORT true)");

        String speciesStr = scanner.next();
        String eyeColorStr = scanner.next();
        String furStr = scanner.next();
        String eatsBunStr = scanner.next();

        Animal.Species species = Animal.Species.valueOf(speciesStr.trim().toUpperCase());
        Animal.EyeColor eyeColor = Animal.EyeColor.valueOf(eyeColorStr.trim().toUpperCase());
        Animal.Fur fur = Animal.Fur.valueOf(furStr.trim().toUpperCase());
        boolean eatsBun = parseBooleanStrict(eatsBunStr);

        return new AnimalBuilder(new Animal())
                .species(species)
                .eyeColor(eyeColor)
                .fur(fur)
                .eatsBun(eatsBun)
                .build();
    }

    private boolean parseBooleanStrict(String value) {
        String normalized = value.trim().toLowerCase();
        if ("true".equals(normalized)) return true;
        if ("false".equals(normalized)) return false;
        throw new IllegalArgumentException("Invalid boolean value: " + value);
    }
}



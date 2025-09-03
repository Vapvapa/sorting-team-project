package com.team.sorting.input.inputter;

import com.team.sorting.builder.AnimalBuilder;
import com.team.sorting.model.Animal;

import java.util.Scanner;

/**
 * An inputter class responsible for reading Animal objects from the console.
 * Users are prompted to enter species, eye color, fur type, and bun-eating preference.
 */
public class AnimalInputter extends AbstractInputter<Animal> {

    /**
     * Reads a single Animal object from the console.
     * The expected format is: species, eyeColor, fur, eatsBun
     * Example: DOG, BROWN, SHORT, true
     *
     * @param scanner The Scanner object used for console input.
     * @return A new Animal object based on user input.
     * @throws IllegalArgumentException if input values are invalid.
     */
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

    /**
     * Strictly parses a boolean value from a string.
     *
     * @param value The string to parse.
     * @return true or false if valid.
     * @throws IllegalArgumentException if the string is not "true" or "false".
     */
    private boolean parseBooleanStrict(String value) {
        String normalized = value.trim().toLowerCase();
        if ("true".equals(normalized)) return true;
        if ("false".equals(normalized)) return false;
        throw new IllegalArgumentException("Invalid boolean value: " + value);
    }
}

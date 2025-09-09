package com.team.sorting.input.loader;

import com.team.sorting.model.Human;


/**
 * A loader class responsible for reading and parsing Human objects from a file.
 * The file format is expected to be:
 * [gender], [age], [last_name]
 * <p>
 * Example:
 * MALE, 25, SMITH
 * FEMALE, 30, JOHNSON
 */
public class HumanLoader extends AbstractLoader<Human> {

    /**
     * Parses a single line of text into a Human object.
     * The line is expected to contain three fields: gender, age, and last name.
     * It supports both Cyrillic and Latin characters for the last name.
     *
     * @param line The string line to be parsed.
     * @return A new Human object.
     * @throws IllegalArgumentException if the line format is incorrect (e.g., incorrect number of fields).
     */
    @Override
    protected Human parseLine(String line) {
        String[] tokens = line.split("[^A-Za-z0-9]+");
        if (tokens.length != 3) throw new IllegalArgumentException("Incorrect number of fields");

        Human.Gender gender = Human.Gender.valueOf(tokens[0].trim().toUpperCase());
        int age = Integer.parseInt(tokens[1].trim());
        String lastName = tokens[2].trim();

        return new Human.Builder()
                .gender(gender)
                .age(age)
                .lastName(lastName)
                .build();
    }
}

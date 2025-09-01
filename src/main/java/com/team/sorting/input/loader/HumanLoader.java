package com.team.sorting.input.loader;

import com.team.sorting.builder.HumanBuilder;
import com.team.sorting.model.Human;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * Reads a file from the specified resource path and converts its contents
     * into a list of Human objects.
     * Invalid lines in the file are skipped and logged to standard error.
     *
     * @param resourcePath The path to the file containing human data.
     * @return A list of valid Human objects parsed from the file.
     * @throws RuntimeException if an I/O error occurs while reading the file.
     */
    @Override
    public List<Human> load(String resourcePath) {
        List<Human> humans = new ArrayList<>();

        try (BufferedReader br = getBufferedReader(resourcePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Human human = parseLine(line);
                    if (human != null) humans.add(human);
                } catch (Exception e) {
                    System.err.println("Skipping an invalid line: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + resourcePath, e);
        }

        return humans;
    }

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

        return new HumanBuilder(new Human())
                .gender(gender)
                .age(age)
                .lastName(lastName)
                .build();
    }
}

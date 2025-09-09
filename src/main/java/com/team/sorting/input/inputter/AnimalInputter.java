package com.team.sorting.input.inputter;

import com.team.sorting.model.Animal;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * An inputter class responsible for reading {@link Animal} objects from the console.
 * Users are prompted to enter species, eye color, fur type, and bun-eating preference.
 * <p>
 * <b>Thread safety:</b> this class is <b>not fully thread-safe</b> for concurrent console input
 * because {@link System#in} is shared across all threads. The {@link ReentrantLock consoleLock}
 * ensures that only one thread at a time reads from the console, preventing input corruption.
 * However, calling {@link #readOne(Scanner)} from multiple threads simultaneously may still
 * block other threads until the current input is completed. The creation of Animal objects
 * themselves is thread-safe, as each thread creates its own object.
 */
public class AnimalInputter extends AbstractInputter<Animal> {

    /**
     * Lock to ensure thread-safe console input.
     */
    private static final ReentrantLock consoleLock = new ReentrantLock();

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
        consoleLock.lock();
        try {
            System.out.println("Enter Animal as: species eyeColor fur eatsBun (e.g., DOG BROWN SHORT true)");

            List<String> tokens = Stream.generate(scanner::next)
                    .limit(4)
                    .map(String::trim)
                    .toList();

            String speciesStr = tokens.get(0);
            String eyeColorStr = tokens.get(1);
            String furStr = tokens.get(2);
            String eatsBunStr = tokens.get(3);

            Animal.Species species = Animal.Species.valueOf(speciesStr.trim().toUpperCase());
            Animal.EyeColor eyeColor = Animal.EyeColor.valueOf(eyeColorStr.trim().toUpperCase());
            Animal.Fur fur = Animal.Fur.valueOf(furStr.trim().toUpperCase());
            boolean eatsBun = parseBooleanStrict(eatsBunStr);

            return new Animal.Builder()
                    .species(species)
                    .eyeColor(eyeColor)
                    .fur(fur)
                    .eatsBun(eatsBun)
                    .build();
        } finally {
            consoleLock.unlock();
        }
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

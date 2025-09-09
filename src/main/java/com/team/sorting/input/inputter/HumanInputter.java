package com.team.sorting.input.inputter;

import com.team.sorting.model.Human;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * An inputter class responsible for reading {@link Human} objects from the console.
 * Users are prompted to enter gender, age, and last name.
 * <p>
 * <b>Thread safety:</b> this class is <b>not fully thread-safe</b> for concurrent console input
 * because {@link System#in} is shared across all threads. The {@link ReentrantLock consoleLock}
 * ensures that only one thread at a time reads from the console, preventing input corruption.
 * However, calling {@link #readOne(Scanner)} from multiple threads simultaneously may still
 * block other threads until the current input is completed. The creation of Human objects
 * themselves is thread-safe, as each thread creates its own object.
 */
public class HumanInputter extends AbstractInputter<Human> {

    /**
     * Lock to ensure thread-safe console input.
     */
    private static final ReentrantLock consoleLock = new ReentrantLock();

    /**
     * Reads a single {@link Human} object from the console.
     * The expected format is: gender, age, lastName
     * Example: MALE 25 Smith
     *
     * @param scanner The {@link Scanner} object used for console input.
     * @return A new Human object based on user input.
     * @throws IllegalArgumentException if input values are invalid.
     */
    @Override
    protected Human readOne(Scanner scanner) {
        consoleLock.lock();
        try {
            System.out.println("Enter Human as: gender age lastName (e.g., MALE 25 Smith)");

            List<String> tokens = Stream.generate(scanner::next)
                    .limit(3)
                    .map(String::trim)
                    .toList();

            String genderStr = tokens.get(0);
            int age = Integer.parseInt(tokens.get(1));
            String lastName = tokens.get(2);

            Human.Gender gender = Human.Gender.valueOf(genderStr.trim().toUpperCase());

            return new Human.Builder()
                    .gender(gender)
                    .age(age)
                    .lastName(lastName)
                    .build();
        } finally {
            consoleLock.unlock();
        }
    }
}

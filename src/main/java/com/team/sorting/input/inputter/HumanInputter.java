package com.team.sorting.input.inputter;

import com.team.sorting.builder.HumanBuilder;
import com.team.sorting.model.Human;

import java.util.Scanner;

/**
 * An inputter class responsible for reading Human objects from the console.
 * Users are prompted to enter gender, age, and last name.
 */
public class HumanInputter extends AbstractInputter<Human> {

    /**
     * Reads a single Human object from the console.
     * The expected format is: gender, age, lastName
     * Example: MALE, 25, Smith
     *
     * @param scanner The Scanner object used for console input.
     * @return A new Human object based on user input.
     * @throws IllegalArgumentException if input values are invalid.
     */
    @Override
    protected Human readOne(Scanner scanner) {
        System.out.println("Enter Human as: gender age lastName (e.g., MALE 25 Smith)");

        String genderStr = scanner.next();
        int age = Integer.parseInt(scanner.next());
        String lastName = scanner.next();

        Human.Gender gender = Human.Gender.valueOf(genderStr.trim().toUpperCase());

        return new HumanBuilder(new Human())
                .gender(gender)
                .age(age)
                .lastName(lastName)
                .build();
    }
}

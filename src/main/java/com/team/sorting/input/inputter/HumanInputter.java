package com.team.sorting.input.inputter;

import com.team.sorting.builder.HumanBuilder;
import com.team.sorting.model.Human;

import java.util.Scanner;

public class HumanInputter extends AbstractInputter<Human> {

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



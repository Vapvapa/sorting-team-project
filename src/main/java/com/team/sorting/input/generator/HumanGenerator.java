package com.team.sorting.input.generator;

import com.team.sorting.model.Human;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A generator class responsible for creating random Human objects.
 * Each Human has a randomly selected gender, age (18–80), and last name.
 */
public class HumanGenerator implements EntityGenerator<Human> {

    /**
     * Predefined set of last names used for generating random humans.
     */
    private final String[] lastNames = {"Black", "Brown", "Gray", "Green", "White"};

    /**
     * Generates a list of random Human objects.
     *
     * @param count The number of Human objects to generate.
     * @return A list of randomly generated Human objects.
     */
    @Override
    public List<Human> generate(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> {
                    Human.Gender gender = getRandomEnum(Human.Gender.class);
                    int age = 18 + ThreadLocalRandom.current().nextInt(63); // 18–80
                    String lastName = lastNames[ThreadLocalRandom.current().nextInt(lastNames.length)];

                    return new Human.Builder()
                            .gender(gender)
                            .age(age)
                            .lastName(lastName)
                            .build();
                })
                .toList();
    }

    /**
     * Selects a random enum value from the given enum class.
     *
     * @param clazz The enum class to select from.
     * @param <E> The enum type.
     * @return A randomly selected enum value.
     */
    private <E extends Enum<?>> E getRandomEnum(Class<E> clazz) {
        E[] values = clazz.getEnumConstants();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}

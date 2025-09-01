package com.team.sorting.generator;

import com.team.sorting.builder.HumanBuilder;
import com.team.sorting.model.Human;

import java.util.List;
import java.util.Random;

public class HumanGenerator implements EntityGenerator<Human> {

    private final Random random = new Random();
    private final String[] lastNames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Морозов", "Васильев"};

    @Override
    public List<Human> generate(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> {
                    Human.Gender gender = getRandomEnum(Human.Gender.class);
                    int age = 18 + random.nextInt(63);
                    String lastName = lastNames[random.nextInt(lastNames.length)];

                    return new HumanBuilder(new Human())
                            .gender(gender)
                            .age(age)
                            .lastName(lastName)
                            .build();
                })
                .toList();
    }

    private <E extends Enum<?>> E getRandomEnum(Class<E> clazz) {
        E[] values = clazz.getEnumConstants();
        return values[random.nextInt(values.length)];
    }
}

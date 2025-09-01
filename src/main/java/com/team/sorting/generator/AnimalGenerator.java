package com.team.sorting.generator;

import com.team.sorting.builder.AnimalBuilder;
import com.team.sorting.model.Animal;

import java.util.List;
import java.util.Random;

public class AnimalGenerator implements EntityGenerator<Animal> {

    private final Random random = new Random();

    @Override
    public List<Animal> generate(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> {
                    Animal.Species species = getRandomEnum(Animal.Species.class);
                    Animal.EyeColor eyeColor = getRandomEnum(Animal.EyeColor.class);
                    Animal.Fur fur = getRandomEnum(Animal.Fur.class);
                    boolean eatsBun = random.nextBoolean();

                    return new AnimalBuilder(new Animal())
                            .species(species)
                            .eyeColor(eyeColor)
                            .fur(fur)
                            .eatsBun(eatsBun)
                            .build();
                })
                .toList();
    }

    private <E extends Enum<?>> E getRandomEnum(Class<E> clazz) {
        E[] values = clazz.getEnumConstants();
        return values[random.nextInt(values.length)];
    }
}

package com.team.sorting.input.generator;

import com.team.sorting.model.Animal;

import java.util.List;
import java.util.Random;

/**
 * A generator class responsible for creating random Animal objects.
 * Each Animal has a randomly selected species, eye color, fur type, and bun-eating preference.
 */
public class AnimalGenerator implements EntityGenerator<Animal> {

    /**
     * Random instance used for generating random values.
     */
    private final Random random = new Random();

    /**
     * Generates a list of random Animal objects.
     *
     * @param count The number of Animal objects to generate.
     * @return A list of randomly generated Animal objects.
     */
    @Override
    public List<Animal> generate(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> {
                    Animal.Species species = getRandomEnum(Animal.Species.class);
                    Animal.EyeColor eyeColor = getRandomEnum(Animal.EyeColor.class);
                    Animal.Fur fur = getRandomEnum(Animal.Fur.class);
                    boolean eatsBun = random.nextBoolean();

                    return new Animal.Builder()
                            .species(species)
                            .eyeColor(eyeColor)
                            .fur(fur)
                            .eatsBun(eatsBun)
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
        return values[random.nextInt(values.length)];
    }
}

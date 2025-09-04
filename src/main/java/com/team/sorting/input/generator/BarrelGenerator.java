package com.team.sorting.input.generator;

import com.team.sorting.model.Barrel;

import java.util.List;
import java.util.Random;

/**
 * A generator class responsible for creating random Barrel objects.
 * Each Barrel has a randomly selected volume (50–1000 liters), stored material, and construction material.
 */
public class BarrelGenerator implements EntityGenerator<Barrel> {

    /**
     * Random instance used for generating random values.
     */
    private final Random random = new Random();

    /**
     * Generates a list of random Barrel objects.
     *
     * @param count The number of Barrel objects to generate.
     * @return A list of randomly generated Barrel objects.
     */
    @Override
    public List<Barrel> generate(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> {
                    int volume = 50 + random.nextInt(951);
                    Barrel.StoredMaterial storedMaterial = getRandomEnum(Barrel.StoredMaterial.class);
                    Barrel.Material material = getRandomEnum(Barrel.Material.class);

                    return new Barrel.Builder()
                            .volume(volume)
                            .storedMaterial(storedMaterial)
                            .material(material)
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

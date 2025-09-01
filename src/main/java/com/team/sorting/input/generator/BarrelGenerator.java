package com.team.sorting.input.generator;

import com.team.sorting.builder.BarrelBuilder;
import com.team.sorting.model.Barrel;

import java.util.List;
import java.util.Random;

public class BarrelGenerator implements EntityGenerator<Barrel> {

    private final Random random = new Random();

    @Override
    public List<Barrel> generate(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> {
                    int volume = 50 + random.nextInt(951);
                    Barrel.StoredMaterial storedMaterial = getRandomEnum(Barrel.StoredMaterial.class);
                    Barrel.Material material = getRandomEnum(Barrel.Material.class);

                    return new BarrelBuilder(new Barrel())
                            .volume(volume)
                            .storedMaterial(storedMaterial)
                            .material(material)
                            .build();
                })
                .toList();
    }

    private <E extends Enum<?>> E getRandomEnum(Class<E> clazz) {
        E[] values = clazz.getEnumConstants();
        return values[random.nextInt(values.length)];
    }
}

package com.team.sorting.input.inputter;

import com.team.sorting.builder.BarrelBuilder;
import com.team.sorting.model.Barrel;

import java.util.Scanner;

public class BarrelInputter extends AbstractInputter<Barrel> {

    @Override
    protected Barrel readOne(Scanner scanner) {
        System.out.println("Enter Barrel as: volume storedMaterial material (e.g., 200 OIL WOOD)");

        int volume = Integer.parseInt(scanner.next());
        String storedMaterialStr = scanner.next();
        String materialStr = scanner.next();

        Barrel.StoredMaterial storedMaterial = Barrel.StoredMaterial.valueOf(storedMaterialStr.trim().toUpperCase());
        Barrel.Material material = Barrel.Material.valueOf(materialStr.trim().toUpperCase());

        return new BarrelBuilder(new Barrel())
                .volume(volume)
                .storedMaterial(storedMaterial)
                .material(material)
                .build();
    }
}



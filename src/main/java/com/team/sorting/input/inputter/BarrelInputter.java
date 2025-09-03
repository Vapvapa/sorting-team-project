package com.team.sorting.input.inputter;

import com.team.sorting.builder.BarrelBuilder;
import com.team.sorting.model.Barrel;

import java.util.Scanner;

/**
 * An inputter class responsible for reading Barrel objects from the console.
 * Users are prompted to enter volume, stored material, and construction material.
 */
public class BarrelInputter extends AbstractInputter<Barrel> {

    /**
     * Reads a single Barrel object from the console.
     * The expected format is: volume, storedMaterial, material
     * Example: 200, OIL, WOOD
     *
     * @param scanner The Scanner object used for console input.
     * @return A new Barrel object based on user input.
     * @throws IllegalArgumentException if input values are invalid.
     */
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

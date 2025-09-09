package com.team.sorting.input.inputter;

import com.team.sorting.model.Barrel;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * An inputter class responsible for reading {@link Barrel} objects from the console.
 * Users are prompted to enter volume, stored material, and construction material.
 * <p>
 * <b>Thread safety:</b> this class is <b>not fully thread-safe</b> for concurrent console input
 * because {@link System#in} is shared across all threads. The {@link ReentrantLock consoleLock}
 * ensures that only one thread at a time reads from the console, preventing input corruption.
 * However, calling {@link #readOne(Scanner)} from multiple threads simultaneously may still
 * block other threads until the current input is completed. The creation of Barrel objects
 * themselves is thread-safe, as each thread creates its own object.
 */
public class BarrelInputter extends AbstractInputter<Barrel> {

    /**
     * Lock to ensure thread-safe console input.
     */
    private static final ReentrantLock consoleLock = new ReentrantLock();

    /**
     * Reads a single {@link Barrel} object from the console.
     * The expected format is: volume, storedMaterial, material
     * Example: 200 OIL WOOD
     *
     * @param scanner The {@link Scanner} object used for console input.
     * @return A new Barrel object based on user input.
     * @throws IllegalArgumentException if input values are invalid.
     */
    @Override
    protected Barrel readOne(Scanner scanner) {
        consoleLock.lock();
        try {
            System.out.println("Enter Barrel as: volume storedMaterial material (e.g., 200 OIL WOOD)");

            List<String> tokens = Stream.generate(scanner::next)
                    .limit(3)
                    .map(String::trim)
                    .toList();

            int volume = Integer.parseInt(tokens.get(0));
            String storedMaterialStr = tokens.get(1);
            String materialStr = tokens.get(2);

            Barrel.StoredMaterial storedMaterial = Barrel.StoredMaterial.valueOf(storedMaterialStr.trim().toUpperCase());
            Barrel.Material material = Barrel.Material.valueOf(materialStr.trim().toUpperCase());

            return new Barrel.Builder()
                    .volume(volume)
                    .storedMaterial(storedMaterial)
                    .material(material)
                    .build();
        } finally {
            consoleLock.unlock();
        }
    }
}

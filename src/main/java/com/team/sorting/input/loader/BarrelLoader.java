package com.team.sorting.input.loader;

import com.team.sorting.model.Barrel;


/**
 * A loader class responsible for reading and parsing Barrel objects from a file.
 * The file format is expected to be:
 * [volume], [stored_material], [material]
 * <p>
 * Example:
 * 200, OIL, WOOD
 * 750, WINE, METAL
 */
public class BarrelLoader extends AbstractLoader<Barrel> {

    /**
     * Parses a single line of text into a Barrel object.
     * The line is expected to contain three fields separated by non-alphanumeric characters.
     *
     * @param line The string line to be parsed.
     * @return A new Barrel object.
     * @throws IllegalArgumentException if the line format is incorrect (e.g., incorrect number of fields).
     */
    @Override
    protected Barrel parseLine(String line) {
        String[] tokens = line.split("[^A-Za-z0-9]+");
        if (tokens.length != 3) throw new IllegalArgumentException("Incorrect number of fields");

        int volume = Integer.parseInt(tokens[0].trim());
        Barrel.StoredMaterial storedMaterial = Barrel.StoredMaterial.valueOf(tokens[1].trim().toUpperCase());
        Barrel.Material material = Barrel.Material.valueOf(tokens[2].trim().toUpperCase());

        return new Barrel.Builder()
                .volume(volume)
                .storedMaterial(storedMaterial)
                .material(material)
                .build();
    }
}

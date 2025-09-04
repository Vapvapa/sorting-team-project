package com.team.sorting.input.loader;

import com.team.sorting.model.Barrel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * Reads a file from the specified resource path and converts its contents
     * into a list of Barrel objects.
     * Invalid lines in the file are skipped and logged to standard error.
     *
     * @param resourcePath The path to the file containing barrel data.
     * @return A list of valid Barrel objects parsed from the file.
     * @throws RuntimeException if an I/O error occurs while reading the file.
     */
    @Override
    public List<Barrel> load(String resourcePath) {
        List<Barrel> barrels = new ArrayList<>();

        try (BufferedReader br = getBufferedReader(resourcePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Barrel barrel = parseLine(line);
                    if (barrel != null) barrels.add(barrel);
                } catch (Exception e) {
                    System.err.println("Skipping an invalid line: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + resourcePath, e);
        }

        return barrels;
    }

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

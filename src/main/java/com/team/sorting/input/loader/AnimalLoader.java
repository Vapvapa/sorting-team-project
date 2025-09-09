package com.team.sorting.input.loader;

import com.team.sorting.model.Animal;


/**
 * A loader class responsible for reading and parsing Animal objects from a file.
 * The file format is expected to be:
 * [species], [eye_color], [fur], [eats_bun]
 * <p>
 * Example:
 * DOG, BROWN, SHORT, true
 * CAT, GREEN, LONG, false
 */
public class AnimalLoader extends AbstractLoader<Animal> {

    /**
     * Parses a single line of text into an Animal object.
     * The line is expected to contain four fields separated by non-alphanumeric characters.
     *
     * @param line The string line to be parsed.
     * @return A new Animal object.
     * @throws IllegalArgumentException if the line format is incorrect or contains invalid values.
     */
    @Override
    protected Animal parseLine(String line) {
        String[] tokens = line.split("[^A-Za-z0-9]+");
        if (tokens.length != 4) throw new IllegalArgumentException("Incorrect number of fields");

        Animal.Species species = parseEnum(tokens[0], Animal.Species.class);
        Animal.EyeColor eyeColor = parseEnum(tokens[1], Animal.EyeColor.class);
        Animal.Fur fur = parseEnum(tokens[2], Animal.Fur.class);
        boolean eatsBun = parseBooleanStrict(tokens[3]);

        return new Animal.Builder()
                .species(species)
                .eyeColor(eyeColor)
                .fur(fur)
                .eatsBun(eatsBun)
                .build();
    }

    /**
     * Parses and validates an enum value from a string.
     *
     * @param value The string value to parse.
     * @param enumCls The enum class type.
     * @param <T> The enum type.
     * @return The parsed enum value.
     * @throws IllegalArgumentException if the value does not match any enum constant.
     */
    private <T extends Enum<T>> T parseEnum(String value, Class<T> enumCls) {
        try {
            return Enum.valueOf(enumCls, value.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid enum value for " + enumCls.getSimpleName() + ": " + value);
        }
    }

    /**
     * Strictly parses a boolean value.
     *
     * @param value The string value to parse.
     * @return true or false if valid.
     * @throws IllegalArgumentException if the value is not "true" or "false".
     */
    private boolean parseBooleanStrict(String value) {
        String normalized = value.trim().toLowerCase();
        if ("true".equals(normalized)) return true;
        if ("false".equals(normalized)) return false;
        throw new IllegalArgumentException("Invalid boolean value: " + value);
    }
}

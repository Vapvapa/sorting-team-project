package com.team.sorting.writeToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Utility class for writing data to files.
 */
public class FileRecorder {

    /**
     * Appends all elements of a collection to the specified file.
     * Each element is written on a new line using its {@code toString()} representation.
     * After writing all elements, an extra blank line is added.
     *
     * @param collection the collection of elements to write
     * @param filename   the path of the file to write to
     * @param <T>        the type of elements in the collection
     */
    public static <T> void writeCollectionToFile(Collection<T> collection, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (T item : collection) {
                writer.write(item.toString());
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file '" + filename + "': " + e.getMessage());
        }
    }

    /**
     * Appends a single value to the specified file.
     * The value is written on a new line using its {@code toString()} representation.
     *
     * @param value    the value to write
     * @param filename the path of the file to write to
     * @param <T>      the type of the value
     */
    public static <T> void writeValueToFile(T value, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(value.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file '" + filename + "': " + e.getMessage());
        }
    }

    /**
     * Clears the content of the specified file.
     *
     * @param filename the path of the file to clear
     */
    public static void clearFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            writer.write("");
        } catch (IOException e) {
            System.err.println("Error clearing file '" + filename + "': " + e.getMessage());
        }
    }
}

package com.team.sorting.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class FileRecorder {
    public static <T> void writeCollectionToFile(Collection<T> collection, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (T item : collection) {
                writer.write(item.toString());
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static <T> void writeValueToFile(T value, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(value.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void clearFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            writer.write("");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
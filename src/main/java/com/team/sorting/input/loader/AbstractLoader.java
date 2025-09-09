package com.team.sorting.input.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Base class for entity loaders. It removes code duplication for file reading.
 * <p>
 * <b>Thread safety:</b> this class is <b>not fully thread-safe</b> for concurrent use on the same file or
 * shared BufferedReader instances. Each call to {@link #getBufferedReader(String)}
 * returns a new BufferedReader, so separate threads should use their own reader
 * instances. Additionally, the thread-safety of {@link #parseLine(String)} depends
 * on its concrete implementation and any shared mutable state it may access.
 */
public abstract class AbstractLoader<T> implements EntityLoader<T> {

    /**
     * Loads entities from the given resource path using a stream-based pipeline.
     * Invalid lines are skipped with an error message to stderr.
     *
     * @param resourcePath The path to the input resource or file.
     * @return List of parsed entities.
     */
    @Override
    public List<T> load(String resourcePath) {
        try (BufferedReader bufferedReader = getBufferedReader(resourcePath)) {
            return bufferedReader.lines()
                    .map(line -> {
                        try {
                            return parseLine(line);
                        } catch (Exception e) {
                            System.err.println("Skipping an invalid line: " + line);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + resourcePath, e);
        }
    }

    /**
     * Parses a line for a specific entity.
     * @param line The string line to be parsed.
     * @return A new entity object.
     * @throws IllegalArgumentException if the line format is incorrect.
     */
    protected abstract T parseLine(String line) throws IllegalArgumentException;

    /**
     * Retrieves a BufferedReader for a file, checking both classpath resources and the file system.
     *
     * @param resourcePath The path to the file.
     * @return A BufferedReader for the specified file.
     * @throws IOException              if an I/O error occurs.
     * @throws IllegalArgumentException if the file is not found.
     */
    protected BufferedReader getBufferedReader(String resourcePath) throws IOException {
        // 1. Try to load from classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream != null) {
            return new BufferedReader(new InputStreamReader(inputStream));
        }

        // 2. If not found in resources, try as a regular path
        Path filePath = Path.of(resourcePath);
        if (Files.exists(filePath)) {
            return Files.newBufferedReader(filePath);
        }

        throw new IllegalArgumentException("File not found in resources or on path: " + resourcePath);
    }
}

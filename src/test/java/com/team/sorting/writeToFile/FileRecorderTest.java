package com.team.sorting.writeToFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link FileRecorder} utility class.
 */
class FileRecorderTest {

    @TempDir
    Path tempDir;

    /**
     * Tests that writing a collection appends all elements to the file, followed by an additional empty string.
     */
    @Test
    void testWriteCollectionToFile() throws IOException {
        Path file = tempDir.resolve("collection.txt");

        FileRecorder.writeCollectionToFile(List.of("A", "B", "C"), file.toString());

        List<String> lines = Files.readAllLines(file);
        assertEquals(List.of("A", "B", "C", ""), lines,
                "File should contain all values followed by a blank line");
    }

    /**
     * Tests that writing individual values appends them on separate lines in the file.
     */
    @Test
    void testWriteValueToFile() throws IOException {
        Path file = tempDir.resolve("value.txt");

        FileRecorder.writeValueToFile("Hello", file.toString());
        FileRecorder.writeValueToFile("World", file.toString());

        List<String> lines = Files.readAllLines(file);
        assertEquals(List.of("Hello", "World"), lines,
                "File should contain both written values on separate lines");
    }

    /**
     * Tests that clearing a file removes all its content.
     */
    @Test
    void testClearFile() throws IOException {
        Path file = tempDir.resolve("clear.txt");

        FileRecorder.writeValueToFile("Data", file.toString());
        assertTrue(Files.size(file) > 0, "File should not be empty after writing");

        FileRecorder.clearFile(file.toString());
        String content = Files.readString(file);
        assertTrue(content.isEmpty(), "File should be empty after clearing");
    }

    /**
     * Tests that writing an empty collection results in only a blank line being written to the file.
     */
    @Test
    void testWriteEmptyCollection() throws IOException {
        Path file = tempDir.resolve("empty.txt");

        FileRecorder.writeCollectionToFile(List.of(), file.toString());

        List<String> lines = Files.readAllLines(file);
        assertEquals(List.of(""), lines,
                "File should contain only a blank line for an empty collection");
    }
}

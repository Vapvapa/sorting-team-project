package com.team.sorting.input.inputter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Base class for entity inputters. It handles repeated input logic from the console.
 * <p>
 * <b>Thread safety:</b> this class is <b>not fully thread-safe</b> for concurrent console input
 * because {@link System#in} is shared across all threads. Adding the {@link ReentrantLock consoleLock} ensures
 * that only one thread at a time reads from the console, preventing input corruption.
 * However, multiple threads calling {@link #input(int)} will still block each other
 * until the current input is completed.
 * <p>
 * The creation of entity objects themselves is thread-safe, as each thread creates its own object.
 *
 * @param <T> The type of entity to input.
 */
public abstract class AbstractInputter<T> implements EntityInputter<T> {

    /**
     * Lock to ensure thread-safe console input.
     */
    private static final ReentrantLock consoleLock = new ReentrantLock();

    /**
     * Inputs multiple entities from the console.
     *
     * @param count The number of entities to input.
     * @return A list of successfully read entities.
     */
    @Override
    public List<T> input(int count) {
        List<T> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < count; i++) {
            while (true) {
                try {
                    consoleLock.lock(); // Lock console access to prevent concurrent input issues
                    T entity = readOne(scanner);
                    if (entity != null) result.add(entity);
                    break;
                } catch (Exception ex) {
                    System.err.println("Invalid input. Try again: " + ex.getMessage());
                    scanner.nextLine();
                } finally {
                    consoleLock.unlock();
                }
            }
        }
        return result;
    }

    /**
     * Reads a single entity from the provided {@link Scanner}.
     * Subclasses must implement this method to define how an entity is read from the console.
     *
     * @param scanner The Scanner instance to read input from.
     * @return A newly created entity object based on user input.
     */
    protected abstract T readOne(Scanner scanner);
}

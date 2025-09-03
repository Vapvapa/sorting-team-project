package com.team.sorting.input.inputter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Base class for entity inputters. It handles repeated input logic from the console.
 */
public abstract class AbstractInputter<T> implements EntityInputter<T> {

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
                    T entity = readOne(scanner);
                    if (entity != null) result.add(entity);
                    break;
                } catch (Exception ex) {
                    System.err.println("Invalid input. Try again: " + ex.getMessage());
                    scanner.nextLine();
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

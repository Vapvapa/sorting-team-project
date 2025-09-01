package com.team.sorting.input.inputter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractInputter<T> implements EntityInputter<T> {

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

    protected abstract T readOne(Scanner scanner);
}



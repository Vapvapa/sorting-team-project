package com.team.sorting.app;

import com.team.sorting.writeToFile.FileRecorder;
import java.util.List;
import java.util.Scanner;

/**
 * Main application entry point for managing entities (Animal, Barrel, Human).
 * Uses {@link HandlerStrategy} to obtain the correct {@link EntityHandler} implementation
 * for the selected entity type.
 */
public class Main {

    /**
     * The main method that runs the console-based application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Clear output file at the start of the application
        FileRecorder.clearFile("output.txt");

        while (true) {
            System.out.println("Choose entity type:");
            System.out.println("0. Exit");
            System.out.println("1. Animal");
            System.out.println("2. Barrel");
            System.out.println("3. Human");
            System.out.print("Your choice: ");

            String entityChoiceStr = scanner.nextLine().trim();
            int entityChoice;
            try {
                entityChoice = Integer.parseInt(entityChoiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice: " + entityChoiceStr);
                continue;
            }

            if (entityChoice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            HandlerStrategy.HandlerType entityType;
            switch (entityChoice) {
                case 1 -> entityType = HandlerStrategy.HandlerType.ANIMAL;
                case 2 -> entityType = HandlerStrategy.HandlerType.BARREL;
                case 3 -> entityType = HandlerStrategy.HandlerType.HUMAN;
                default -> {
                    System.out.println("Invalid choice: " + entityChoice);
                    continue;
                }
            }

            EntityHandler<Object> handler = HandlerStrategy.getHandler(entityType);
            if (handler == null) {
                System.out.println("Invalid choice: " + entityChoice);
                continue;
            }

            System.out.println("\nChoose input method:");
            System.out.println("1. Load from file");
            System.out.println("2. Generate automatically");
            System.out.println("3. Manual input");
            System.out.print("Your choice: ");

            String inputChoiceStr = scanner.nextLine().trim();
            int inputChoice;
            try {
                inputChoice = Integer.parseInt(inputChoiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input method.");
                continue;
            }

            System.out.print("Enter number of entities to load/generate/input: ");
            String sizeStr = scanner.nextLine().trim();
            int size;
            try {
                size = Integer.parseInt(sizeStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid size: " + sizeStr);
                continue;
            }
            if (size <= 0) {
                System.out.println("Invalid size: must be positive.");
                continue;
            }

            List<Object> entities;
            try {
                entities = handler.loadEntities(inputChoice, size, scanner);
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input method.");
                continue;
            }

            handler.searchAndPrint(entities, scanner);

            System.out.println();
        }

        scanner.close();
    }
}

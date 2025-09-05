package com.team.sorting.search;

import com.team.sorting.model.Animal;
import com.team.sorting.model.Barrel;
import com.team.sorting.model.Human;

import java.util.List;

/**
 * Utility class for searching elements in different collections.
 * <p>
 * Additional Task 4:
 * Implement a multithreaded method that counts the number of occurrences
 * of a given element in collections and prints the result to the console.
 */
public class FindElementsInCollection {

    /**
     * Counts the number of occurrences of a given search element
     * in {@link Animal}, {@link Barrel}, and {@link Human} collections.
     * Each collection is scanned in a separate thread.
     *
     * @param animals          the list of {@link Animal} objects
     * @param searchInAnimals  the search value for animals
     * @param barrels          the list of {@link Barrel} objects
     * @param searchInBarrels  the search value for barrels
     * @param humans           the list of {@link Human} objects
     * @param searchInHumans   the search value for humans
     */
    public static void countersOfElements(List<Animal> animals, String searchInAnimals,
                                          List<Barrel> barrels, String searchInBarrels,
                                          List<Human> humans, String searchInHumans) {

        // Thread for scanning animals
        Thread scanAnimals = new Thread(new Runnable() {
            int animalsCounter = 0;

            @Override
            public void run() {
                for (Animal animal : animals) {
                    if (animal.getEyeColor().toString().equals(searchInAnimals) ||
                            animal.getFur().toString().equals(searchInAnimals) ||
                            animal.getSpecies().toString().equals(searchInAnimals) ||
                            animal.getEatsBun() == Boolean.parseBoolean(searchInAnimals)) {
                        animalsCounter++;
                    }
                }
                System.out.println("Animals with " + searchInAnimals + " : " + animalsCounter);
            }
        });

        // Thread for scanning barrels
        Thread scanBarrels = new Thread(new Runnable() {
            int barrelsCounter = 0;

            @Override
            public void run() {
                for (Barrel barrel : barrels) {
                    if (barrel.getMaterial().toString().equals(searchInBarrels) ||
                            barrel.getStoredMaterial().toString().equals(searchInBarrels) ||
                            Integer.toString(barrel.getVolume()).equals(searchInBarrels)) {
                        barrelsCounter++;
                    }
                }
                System.out.println("Barrels with " + searchInBarrels + " : " + barrelsCounter);
            }
        });

        // Thread for scanning humans
        Thread scanHumans = new Thread(new Runnable() {
            int humansCounter = 0;

            @Override
            public void run() {
                for (Human human : humans) {
                    if (human.getGender().toString().equals(searchInHumans) ||
                            human.getLastName().equals(searchInHumans) ||
                            Integer.toString(human.getAge()).equals(searchInHumans)) {
                        humansCounter++;
                    }
                }
                System.out.println("Humans with " + searchInHumans + " : " + humansCounter);
            }
        });

        // Start all threads
        scanAnimals.start();
        scanBarrels.start();
        scanHumans.start();

        // Wait for all threads to finish
        try {
            scanAnimals.join();
            scanBarrels.join();
            scanHumans.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution was interrupted");
        }
    }
}
